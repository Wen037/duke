package Chad.Ui;
import java.io.IOException;

import Chad.Chad;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * A GUI for Chad using FXML.
 */
public class Main extends Application {

    private Chad chad = new Chad("./data/chad.txt");

    @Override
    public void start(Stage stage) {
        //showWelcome();
        Font.loadFont(getClass().getResourceAsStream("/fonts/A-rAEx.ttf"), 12); // Load the font at size 12
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChad(chad);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("Main exception..!");
        }
    }
}