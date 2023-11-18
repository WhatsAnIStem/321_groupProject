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
import src.shared_classes.Workflow;

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
        Parent root = FXMLLoader.load(this.getClass().getResource("./DataEntryForm.fxml"));
        Parent otherRoot = FXMLLoader.load(this.getClass().getResource("./DataEntryDoneScreen.fxml"));
        primaryStage.setTitle("Alien Applicant Application Screen");
        dataEntryScene = new Scene(root, 400, 300);
        confirmationScene = new Scene(otherRoot, 400, 300);
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

        EventHandler submitHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent AE){
                //when clicked, get all the field entrys, validate, etc...
                //if all the fields pass validation...
                try{
                    getFieldsFromScreen();
                    Boolean passed = performBasicValidation();
                    if(passed){
                        //call accountcreation to make a new object
                        System.out.println("basic validation passed!");
                        int aid = AccountCreation.createNewAccountCreation(fields);
                        Workflow.makeNewWorkflowItem(aid);
                        ((Text)(confirmationScene.lookup("#app_id"))).setText(aid + "");
                        primaryStage.setScene(confirmationScene);
                    }
                    //if not...
                    else{
                        //display error
                        System.out.println("basic validation failed!");
                    }
                }
                catch(Exception E){
                    System.out.println(E);
                    System.out.println("basic validation failed!");
                }
            }
        };
        submitButton.setOnAction(submitHandler);
    }

    private void getFieldsFromScreen() throws Exception{
        String first_name_String = first_name.getText().trim();
        String middle_name_String = middle_name.getText().trim();
        String last_name_String = last_name.getText().trim();

        fields[AccountCreation.FIELD_NAME] = first_name_String + ";" + middle_name_String + ";" + last_name_String;
        if((first_name_String.isEmpty() && first_name_String.contains(";")) || (middle_name_String.isEmpty() && middle_name_String.contains(";")) || (last_name_String.isEmpty() &&last_name_String.contains(";"))){
            fields[AccountCreation.FIELD_NAME] = null;
        }
        fields[AccountCreation.FIELD_DOB] = null;
        if (dob.getValue() != null) {
            fields[AccountCreation.FIELD_DOB] = dob.getValue().toString();
        }

        fields[AccountCreation.FIELD_HEIGHT] = height.getText();
        fields[AccountCreation.FIELD_WEIGHT] = weight.getText();
        fields[AccountCreation.FIELD_EYECOLOR] = eye_color.getValue().toLowerCase();
        fields[AccountCreation.FIELD_COUNTRYOFORIGIN] = coo.getText();
        fields[AccountCreation.FIELD_EMAIL] = email.getText();
        fields[AccountCreation.FIELD_PHONENO] = phone_no.getText();
        fields[AccountCreation.FIELD_MAILINGADDRESS] = mailing_address.getText();
    }

    private boolean performBasicValidation() throws Exception{
        String[] splitString = fields[AccountCreation.FIELD_NAME].split(";");
        if(splitString.length != 3){
            return false;
        }
        if(splitString[0].equals("") || splitString[1].equals("") || splitString[2].equals("")){
            return false;
        }
        if(fields[AccountCreation.FIELD_DOB] == null){
            return false;
        }
        if(fields[AccountCreation.FIELD_HEIGHT].equals("")){
            return false;
        }
        if(fields[AccountCreation.FIELD_WEIGHT].equals("")){
            return false;
        }
        if(fields[AccountCreation.FIELD_EYECOLOR].equals("")){
            return false;
        }
        if(fields[AccountCreation.FIELD_COUNTRYOFORIGIN].equals("")){
            return false;
        }
        if(fields[AccountCreation.FIELD_EMAIL].equals("")){
            return false;
        }
        if(fields[AccountCreation.FIELD_PHONENO].equals("")){
            return false;
        }
        if(fields[AccountCreation.FIELD_MAILINGADDRESS].equals("")){
            return false;
        }
        return true;
    }
}