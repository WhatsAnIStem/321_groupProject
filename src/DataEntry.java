package src;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.shared_classes.AccountCreation;

public class DataEntry extends Application{

    private Stage primaryStage;

    private Scene dataEntryScene;
    private Scene confirmationScene;

    private TextField first_name;
    private TextField middle_name;
    private TextField last_name;

    private DatePicker dob;

    private TextField height;
    private TextField weight;

    private ChoiceBox<String> eye_color;

    private TextField coo;

    private TextField email;
    private TextField phone_no;
    private TextField mailing_address;

    private Button submitButton;

    private String[] fields;

    @Override
    public void start(Stage primaryStage) throws Exception{

        fields = new String[AccountCreation.FIELD_NUMFIELDS];
        this.primaryStage = primaryStage;
        //-------------ESSENTIAL CODE HERE---------------
        Parent root = FXMLLoader.load(this.getClass().getResource("./MainForm.fxml"));
        primaryStage.setTitle("Hello World");
        dataEntryScene = new Scene(root, 400, 300);
        primaryStage.setScene(dataEntryScene);
        primaryStage.show();
        //END ESSENTIAL CODE -----------
        //need to grab all the relevant fields...
        first_name = (TextField)dataEntryScene.lookup("#field_name_first");
        middle_name = (TextField)dataEntryScene.lookup("#field_name_middle");
        last_name = (TextField)dataEntryScene.lookup("#field_name_last");

        dob = (DatePicker)dataEntryScene.lookup("#field_dob");

        height = (TextField)dataEntryScene.lookup("#field_height");
        weight = (TextField)dataEntryScene.lookup("#field_weight");

        eye_color = (ChoiceBox<String>)dataEntryScene.lookup("#field_eyeColor");
        eye_color.setItems(FXCollections.observableList(AccountCreation.EYE_COLOR_VALUES));

        coo = (TextField)dataEntryScene.lookup("#field_coo");
        email = (TextField)dataEntryScene.lookup("#field_email");
        phone_no = (TextField)dataEntryScene.lookup("#field_phoneNumber");
        mailing_address = (TextField)dataEntryScene.lookup("#field_mailingAddress");

        submitButton = (Button)dataEntryScene.lookup("#button_submit");

        EventHandler submitHandler = new EventHandler<InputEvent>(){
                public void handle(InputEvent IE){
                    //when clicked, get all the field entrys, validate, etc...
                    getFieldsFromScreen();
                    //if all the fields pass validation...
                    if(performBasicValidation()){

                    }
                    //if not...
                    else{

                    }
                }

        };

    }

    private void getFieldsFromScreen(){
        String first_name_String = first_name.getText().trim();
        String middle_name_String = middle_name.getText().trim();
        String last_name_String = last_name.getText().trim();

        fields[AccountCreation.FIELD_NAME] = first_name_String + " " + middle_name_String + " " + last_name_String;
        fields[AccountCreation.FIELD_DOB] = dob.getValue().toString();
        fields[AccountCreation.FIELD_HEIGHT] = height.getText();
        fields[AccountCreation.FIELD_WEIGHT] = weight.getText();
        fields[AccountCreation.FIELD_EYECOLOR] = eye_color.getValue();
        fields[AccountCreation.FIELD_COUNTRYOFORIGIN] = coo.getText();
        fields[AccountCreation.FIELD_EMAIL] = email.getText();
        fields[AccountCreation.FIELD_PHONENO] = phone_no.getText();
        fields[AccountCreation.FIELD_MAILINGADDRESS] = mailing_address.getText();
    }

    private boolean performBasicValidation(){
        return false;
    }

    public void showDataEntryScreen(){
        
    }



}