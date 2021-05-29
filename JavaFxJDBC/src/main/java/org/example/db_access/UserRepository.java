package org.example.db_access;

import org.example.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserRepository extends AbstractRepository<String, User> {
    public UserRepository() {
        super("users", "login", User.class, String.class);
    }

    @Override
    public User resultSetToEntity(ResultSet res) throws SQLException {
        User user = new User();
        user.setUsername(res.getString("username"));
        user.setLogin(res.getString("login"));
        user.setRole(res.getString("role"));
        user.setPassword(res.getString("password"));
        user.setAge(res.getLong("age"));
        return user;
    }

    @Override
    protected Map<String, Object> entityToSQLQueryValues(User entity) {
        Map<String, Object> entityFieldsMap = new HashMap<>();
        entityFieldsMap.put("username", entity.getUsername());
        entityFieldsMap.put("role", entity.getRole());
        entityFieldsMap.put("password", entity.getPassword());
        entityFieldsMap.put("login", entity.getLogin());
        entityFieldsMap.put("age", entity.getAge());

        return entityFieldsMap;
    }

    public boolean isUserPasswordCorrect(String username, String password) throws SQLException {
        return this.findAll()
                .stream()
                .anyMatch((u)->u.getUsername().equals(username) && u.getPassword().equals(password));
//        Alternative:
//        for (User user: this.findAll()) {
//            if(!user.getUsername().equals(username) || !user.getPassword().equals(password)) continue;
//            else return true;
//        }
//        return false;
    }
}
