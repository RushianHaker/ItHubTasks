package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Main;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button loginBtn;
    @FXML
    private TextField loginInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label loginErrorLabel;

    @FXML
    public void onLoginBtnClick(ActionEvent actionEvent){
        System.out.println("Logging in");
        loginErrorLabel.setText("Logging in..");
        new Thread(()->{
            try {
                Thread.sleep(1500);
                Main.getScreenController().addScreen("admin", FXMLLoader.load(getClass().getResource("/admin.fxml")));
                Main.getScreenController().activate("admin");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
