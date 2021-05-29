package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.controller.ScreenController;
import org.example.db_access.DatabaseUtils;
import org.example.db_access.UserRepository;
import org.example.domain.User;

import java.sql.SQLException;

public class Main extends Application {
    public static final String DB_NAME = "testdb";
    private static ScreenController screenController;
    public static ScreenController getScreenController(){ return screenController; }

    public static void main(String[] args) throws SQLException {
        //        System.out.println( UserMethods.findUserByLogin("testUser").isPresent() ? "testUser is empty" : "testUser is not present" );

        final String login = "login1";
//        System.out.println( "User methods"+UserMethods.findUserByLogin(login) );

        UserRepository userRepo = new UserRepository();
        User user = new User();
        user.setLogin(login);
        user.setPassword("password1");
        user.setRole("role1");
        user.setUsername("username1");
        user.setAge(25L);

        userRepo.insertEntity(user);
        user = userRepo.findEntityById(login).get();
        System.out.println("Selected "+user);

        user.setPassword("updPassword1");
        userRepo.updateEntityById(login, user);
        user = userRepo.findEntityById(login).get();

        System.out.println("User after insert&update"+user);
        userRepo.deleteEntityById(login);

//        List<User> dbUsers = UserMethods.findAllUsers();
//        for (User dbUser:dbUsers) {
//            System.out.println( "Deleting " + dbUser );
//            UserMethods.deleteUserWhereLogin(dbUser.getLogin());
//        }

//        try {
//            User user = new User();
//            user.setLogin(login);
//            user.setPassword("password1");
//            user.setRole("role1");
//            user.setUsername("username1");
//            UserMethods.insertUser(user);
//
//            System.out.println( "Inserted: " + UserMethods.findUserByLogin(login).orElse(null) );
//
//            user.setPassword("password2");
//            UserMethods.updateUserWhereLogin(user, user.getLogin());
//
//            System.out.println( "Updated: " + UserMethods.findUserByLogin(login).orElse(null) );
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //Test database connectivity
        DatabaseUtils.getInstance();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Font.getFamilies().forEach(System.out::println);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        stage.setTitle("Login form");
        stage.setScene(scene);
        stage.show();

        Main.screenController = new ScreenController(scene);
    }
}
