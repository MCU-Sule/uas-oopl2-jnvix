package com.jojo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;
/**
 * @author 1672012
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login-view.fxml"),
                ResourceBundle.getBundle("my_bundle"));
        primaryStage.setTitle("OOP2 UAS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
