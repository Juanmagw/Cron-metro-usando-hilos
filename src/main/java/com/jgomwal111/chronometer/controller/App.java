package com.jgomwal111.chronometer.controller;

import com.jgomwal111.chronometer.model.dataObject.Chronometer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("chronometerView"), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        /*Chronometer c = new Chronometer();
        c.start();
        do {
            try {
                Thread.sleep(1000);
                System.out.println(c.getTime());
            } catch (InterruptedException e) {
                c.setToastMessage(e + "!!Error¡¡");
            }
        }while(!c.isInterrupted());*/
         
        launch();
    }
}