package org.example.db_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @param <I>: Generic type for Id. Repository supports following types: String, Long, Double, Integer, Float
 * */
public abstract class AbstractRepository<I, E> {
    private String tableName;
    private String idColumn;
    private Class<E> entityClass;
    private Class<I> idClass;
//    private Function<ResultSet, E> resultSetToEntity;
//    private Function<E, SQLEntityMapper> entityToFieldMap;
    private DatabaseUtils db;

    /**
     * @param idClass supported types: String, Long, Double, Integer, Float
     * */
    public AbstractRepository(String tableName, String idColumn, Class<E> entityClass, Class<I> idClass) {
        this.tableName = tableName;
        this.idColumn = idColumn;
        this.entityClass = entityClass;
        this.idClass = idClass;
//        this.resultSetToEntity = resultSetToEntity;
//        this.entityToFieldMap = entityToFieldMap;
        this.db = DatabaseUtils.getInstance();
    }

    public abstract E resultSetToEntity(ResultSet results) throws SQLException;

    /*SET Format:
    'login': 'login1',
    'password': 'password2',
    'role': 'role1',
    'username': 'username1',
    'age': 22}
    */
    /**
     * @see AbstractRepository#entityToSQLString(QUERY_TYPE, Object) for getting a string
     * */
    protected abstract Map<String, Object> entityToSQLQueryValues(E entity);

    /*VALUES Format:
        "
        (`login`,
        `password`,
        `role`,
        `username`)
        VALUES
        (123,
        CURRENT_DATE(),
        'PENDING',
        300.45)"
        */
    /*SET Format:
    "SET
    `login` = 'login1',
    `password` = 'password2',
    `role` = 'role1',
    `username` = 'username1'"
    */
    public String entityToSQLString(QUERY_TYPE queryValueType, E entity){
        Map<String, Object> objectValues = this.entityToSQLQueryValues(entity);
        String sqlString = queryValueType.name().toUpperCase()+"\n";
        List<Map.Entry<String, Object>> fields = new ArrayList<>( objectValues.entrySet() );
        //objToSQLValue("hello") => "'hello'", objToSQLValue(123) => "123"
        Function<Object, String> objToSQLValue = (obj) -> {
            if(obj instanceof String) return "'"+obj+"'";
            else return obj+"";
        };

        if(queryValueType == QUERY_TYPE.SET) {
            sqlString = fields
                    .stream()
                    .map((entry)->entry.getKey()+" = "+objToSQLValue.apply(entry.getValue()))
                    .collect(Collectors.joining(",\n"));
            sqlString = "SET\n"+sqlString;
        } else if(queryValueType == QUERY_TYPE.VALUES) {
            sqlString = fields
                    .stream()
                    .map((entry)->"`"+entry.getKey()+"`")
                    .collect(Collectors.joining(",\n"));
            sqlString = "("+sqlString+")\nVALUES\n";
            sqlString += "("+fields
                    .stream()
                    .map((entry)->objToSQLValue.apply(entry.getValue()))
                    .collect(Collectors.joining(",\n"))+")";
        }

        return sqlString;
    }

    private void setStatementId(PreparedStatement stmt, int paramI, I id) throws SQLException {
        if(idClass == String.class)
            stmt.setString(paramI, (String) id);
        else if(idClass == Long.class)
            stmt.setLong(paramI, (Long) id);
        else if(idClass == Double.class)
            stmt.setDouble(paramI, (Double) id);
        else if(idClass == Integer.class)
            stmt.setInt(paramI, (Integer) id);
        else if(idClass == Float.class)
            stmt.setFloat(paramI, (Float) id);
        else System.out.println(this.getClass().getName()+": Warning, encountered unrecognized id type "+id.getClass());
    }

    public Optional<E> findEntityById(I id) throws SQLException {
        String query = "SELECT * FROM "+this.tableName+" WHERE "+tableName+"."+idColumn+"=?";
        PreparedStatement stmt = db.getPreparedStatement(query);
        setStatementId(stmt, 1, id);
        System.out.println(query);
        ResultSet results = stmt.executeQuery();
        if(!results.isBeforeFirst()) {
            //There are no results
            return Optional.empty();
        }
        results.next();
        if(!results.isLast()) {
            System.out.println("Warning, findEntityById "+this.entityClass.getName()+" found more than one entity for id "+id);
        }
        return Optional.of(this.resultSetToEntity(results));
    }

    public List<E> findAll() throws SQLException {
        String query = "SELECT * FROM "+this.tableName;
        PreparedStatement stmt = db.getPreparedStatement(query);
        System.out.println(query);
        ResultSet results = stmt.executeQuery();
        List<E> entities = new ArrayList<>();
        while (results.next()) {
            entities.add(this.resultSetToEntity(results));
        }
        return entities;
    }

    public void updateEntityById(I id, E entity) throws SQLException {
        String query = "UPDATE `"+this.tableName+"`\n"+this.entityToSQLString(QUERY_TYPE.SET, entity)+"\nWHERE "+tableName+"."+idColumn+"=?";
        PreparedStatement statement = db.getPreparedStatement(query);
        setStatementId(statement, 1, id);
        System.out.println(query);
        statement.execute();
    }

    public void insertEntity(E entity) throws SQLException {
        String query = "INSERT INTO `"+this.tableName+"`\n"+this.entityToSQLString(QUERY_TYPE.VALUES, entity);
        PreparedStatement statement = db.getPreparedStatement(query);
        System.out.println(query);
        statement.execute();
    }

    public void deleteEntityById(I id) throws SQLException {
        String query = "DELETE FROM `"+this.tableName+"` WHERE "+tableName+"."+idColumn+"=?";
        PreparedStatement statement = db.getPreparedStatement(query);
        setStatementId(statement, 1, id);
        System.out.println(query);
        statement.execute();
    }

    enum QUERY_TYPE {
        /*SET
        `login` = 'login1',
        `password` = 'password2',
        `role` = 'role1',
        `username` = 'username1'*/
        SET,
        /*VALUES
        (123,
        CURRENT_DATE(),
        'PENDING',
        300.45)*/
        VALUES,
    }
}
