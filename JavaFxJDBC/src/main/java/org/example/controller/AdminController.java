package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.Main;
import org.example.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO: List of existing users, update/insert/delete
//ListView https://metanit.com/java/javafx/4.8.php
//TODO: Popups, confirm windows etc.
public class AdminController {
    @FXML
    //TODO: Make sure fx:id can start w/ upper & var with lower
    private Label adminLabel;
    @FXML
    private Button adminLogoutBtn;
    @FXML
    private TextField loginAddField;
    @FXML
    private ScrollPane usersPane;
    @FXML
    private Group usersGroup;
    @FXML
    private AnchorPane userSample;

    private List<User> users = new ArrayList<>();
    private void renderUsers(){
        usersGroup.getChildren().forEach((node)->{
            if(!node.idProperty().get().equals("userSample")) {
                usersGroup.getChildren().removeAll(node);
            }
        });
//        usersGroup.getChildren().add(userSample.)
    }

    @FXML
    public void initialize(){
        System.out.println("Controller initialized");
        adminLabel.setText("Admin Controller, "+new Date());
        User user = new User();
        user.setLogin("Login1");
        user.setUsername("Username1");
        user.setAge(25L);
        user.setRole("Role1");
        user.setPassword("password1");
        adminLogoutBtn.setOnAction((event)->{
            System.out.println("Logging out (Listener)");
            try {
                Main.getScreenController().addScreen("login", FXMLLoader.load(getClass().getResource("/login.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getScreenController().activate("login");
        });


    }

    @FXML
    public void addUserToList(ActionEvent event){
        System.out.println("Adding "+ loginAddField.getText());
//        userTableView.collec
    }
    @FXML
    public void adminLogOut(ActionEvent event){
        System.out.println("Logging out");
    }
}
