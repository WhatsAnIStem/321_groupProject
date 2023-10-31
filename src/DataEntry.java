package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DataEntry extends Application{

    private static DataEntry dataEntry = null;

    private Stage primaryStage;

    private Scene dataEntryScene;
    private Scene confirmationScene;

    private TextField first_name;
    private TextField middle_name;
    private TextField last_name;

    private Button submitButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(this.getClass().getResource("./MainForm.fxml"));
        primaryStage.setTitle("Hello World");
        dataEntryScene = new Scene(root, 400, 300);
        primaryStage.setScene(dataEntryScene);
        primaryStage.show();
        //need to grab all the relevant fields...
        first_name = (TextField)dataEntryScene.lookup("#field_name_first");
        middle_name = (TextField)dataEntryScene.lookup("#field_name_middle");
        last_name = (TextField)dataEntryScene.lookup("#field_name_last");

        submitButton = (Button)dataEntryScene.lookup("#button_submit");
        dataEntry = this;
    }

    public void showDataEntryScreen(){
        
    }



}