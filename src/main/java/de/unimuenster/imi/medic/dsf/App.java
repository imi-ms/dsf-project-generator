package de.unimuenster.imi.medic.dsf;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    private static Stage stage;
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return App.stage;
    }

    public static Scene getMainScene() {
        return App.scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        setPrimaryStage(stage);
        setPrimaryScene(scene);
        Parent root = FXMLLoader.load(App.class.getResource("/view/fxml/Main.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private void setPrimaryStage(Stage stage) {
        App.stage = stage;
    }

    private void setPrimaryScene(Scene scene) {
        App.scene = scene;
    }
}