package org.example.deprecated;

import org.example.db_access.DatabaseUtils;
import org.example.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated
public class UserMethods {
    private static DatabaseUtils db = DatabaseUtils.getInstance();

    private static final String UPDATE_SQL = "UPDATE `users`\n" +
            "SET\n" +
            "`login` = ?,\n" +
            "`password` = ?,\n" +
            "`role` = ?,\n" +
            "`username` = ?\n";

    private static final String INSERT_SQL = "INSERT INTO `users`\n" +
            "(`login`,\n" +
            "`password`,\n" +
            "`role`,\n" +
            "`username`)\n" +
            "VALUES(\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?)\n";


    private static PreparedStatement prepareUserStatement(String sql, User user) throws SQLException {
        PreparedStatement stmt = db.getPreparedStatement(sql);

        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getRole());
        stmt.setString(4, user.getUsername());

        return stmt;
    }
    private static User userFromResultSet(ResultSet results) throws SQLException {
        User user = new User();

        user.setLogin(results.getString("login"));
        user.setPassword(results.getString("password"));
        user.setRole(results.getString("role"));
        user.setUsername(results.getString("username"));

        return user;
    }
    private static List<User> userListFromResultSet(ResultSet results) throws SQLException {
        List<User> users = new ArrayList<>();
        while (results.next())
            users.add(userFromResultSet(results));

        return users;
    }

    public static List<User> findAllUsers() throws SQLException {
        return userListFromResultSet( db.getPreparedStatement("SELECT * FROM users").executeQuery() );
    }
    public static Optional<User> findUserByLogin(String login) throws SQLException {
        return getUsersByLogin(login).stream().findAny();
    }
    public static List<User> getUsersByLogin(String login) throws SQLException {
        PreparedStatement statement = db.getPreparedStatement("SELECT * FROM users WHERE users.login=?");
        statement.setString(1, login);
        return userListFromResultSet( statement.executeQuery() );
    }

    public static void insertUser(User user) throws SQLException {
        prepareUserStatement(INSERT_SQL, user).execute();
    }

    public static void updateUserWhereLogin(User user, String login) throws SQLException {
        PreparedStatement stmt = prepareUserStatement(UPDATE_SQL+" WHERE users.login=?", user);
        stmt.setString(5, login);
        stmt.execute();
    }

    public static void deleteUserWhereLogin(String login) throws SQLException {
        PreparedStatement stmt = db.getPreparedStatement("DELETE FROM users WHERE users.login=?");
        stmt.setString(1, login);
        stmt.execute();
    }
}
