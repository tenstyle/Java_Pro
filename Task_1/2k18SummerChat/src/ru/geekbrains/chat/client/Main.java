package ru.geekbrains.chat.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("2k18SummerChat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        // primaryStage.setOnCloseRequest(event -> System.out.println("On Close"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
