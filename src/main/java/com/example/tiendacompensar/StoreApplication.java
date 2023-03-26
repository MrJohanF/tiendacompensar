package com.example.tiendacompensar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StoreApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 478);
        stage.setTitle("Tienda Compensar");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/logo.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}