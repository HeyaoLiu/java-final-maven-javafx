package org.openjfx;

import java.awt.Desktop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openjfx.core.MsIsConstant.*;

/**
 * JavaFX App
 */
public class App extends Application {

    static Desktop desktop = Desktop.getDesktop();
    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(ComponentEnum.ImageUpload));
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    static void setRoot(ComponentEnum componentEnum) throws IOException {
        scene.setRoot(loadFXML(componentEnum));
    }


    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Choose Pictures");
//        fileChooser.setInitialDirectory(
//                new File(System.getProperty("user.home"))
//        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    static List<File> openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);

        configureFileChooser(fileChooser);
        return fileChooser.showOpenMultipleDialog(stage);

    }

    private static Parent loadFXML(ComponentEnum componentEnum) throws IOException {
        String componentName = componentEnum.toString();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(componentName + "/" + componentName + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}