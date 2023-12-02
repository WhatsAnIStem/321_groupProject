package com.cs321.app;

import com.cs321.app.app_enums.app_status;
import com.cs321.app.shared_classes.AccountCreation;
import com.cs321.app.shared_classes.Workflow;

import java.util.Arrays;

/* UI import stuff. */
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
// import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
// import javafx.scene.control.CheckBox;

// import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;


/* Credits: Diane Hamilton. */
/* Review class. */
public class Review extends Application {

    /* Private class attributes. */
    private app_status application_status = null; /* app_status! */
    private int application_ID = 0x00000000;

    /* Private pointers to workflow and AC. */
    private Workflow revFlow = null; /* workflow! */
    private AccountCreation revAC = null; /* accountCreation! */

    /* Private UI variables. */
    private Stage reviewStage;
    private Scene reviewScene;

    private DatePicker valid_dob;
    private TextField valid_country;
    private TextField valid_email;
    private TextField valid_phone;

    /*
    * [0] : valid_dob
    * [1] : valid_country
    * [2] : valid_email
    * [3] : valid_phone
     */
    private String[] changedFields;
    private boolean needChange;
    private Button submitReview;

    /* Constructor method that initializes status and ID of application. 
    public Review(int app_ID, app_status status) { // app_staus! 
        this.application_ID = app_ID; 
        this.application_status = status;
    }*/
    
    /* Opening the UI. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.reviewStage = primaryStage;

        /* Showing the fxml file to the user. */
        Parent myFXML = FXMLLoader.load(this.getClass().getResource("./ReviewForm.fxml"));
        reviewScene = new Scene(myFXML, 400, 300);
        reviewStage.setTitle("Review Application Window");
        reviewStage.setScene(reviewScene);
        reviewStage.show();

        EventHandler reviewHandler = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent AE){
                //when clicked, get all the field entrys, validate, etc...
                accessWorkflow();
                accessAccountCreation();
                grabInfo();
                //if all the fields pass validation...
                if(validateApplication()){
                    /* Application is valid. No need to edit data. */
                    if (needChange) {
                        editData(0, changedFields);
                    }
                }
                //if not...
                else{
                    /* Fields fail in some way. */
                    rejectApplication(0);
                }
            }

        };

        ((Button)(reviewScene.lookup("#button_submit"))).setOnAction(reviewHandler);
        submitReview.setOnAction(reviewHandler);
    }

    /* Accesses workflow given application ID. */
    private void accessWorkflow() { /* workflow! */
        /* Searches thru workflow based on app_status. */
        /* Assigns app_id found from workflow. */
        application_ID = Workflow.getNextWorkflowItem(app_status.REVIEW);
    }

    /* Accesses accountcreation given some application ID. */
    private AccountCreation accessAccountCreation() { /* accountCreation! */
        /* Grabs some AC object given some application ID. */
        /* Returns that block of AC data. */
        return AccountCreation.getAccountCreationByID(application_ID);
    }
    /* grabInfo - grabs data from UI. */
    private boolean grabInfo(){
        /* Harvesting user input from the fxml file. */
        valid_dob = (DatePicker)reviewScene.lookup("#field_dob");
        valid_country = (TextField)reviewScene.lookup("#field_coo");
        valid_email = (TextField)reviewScene.lookup("#field_email");
        valid_phone = (TextField)reviewScene.lookup("#field_phoneNumber");
        /* Button for submitting review to the workflow. */
        submitReview = (Button)reviewScene.lookup("#button_submit");

        needChange = false;

        return true;
    }

    /* Validates information. Returns true if info is correct, else false. */
    private boolean validateApplication() { 
        /* Grabs account creation information of an applicant from the UI. */
        /* Validates information. */
        /* If correct, true. Else, false. */

        LocalDate curr = LocalDate.now();
        // String[] localeCntry = Locale.getISOCountries(); need debug
        

        /* Checks bounds for dob, email, phone, and eyeColor. 
         *
         * DOB: 18 < AGE
         * Country: Country exists?
         * Email: Email exists?
         * Phone: Phone exists?
         * 
         * submitReview: Button pressed after validations finalized.
         */

        /* Checks for things being empty or not first. */
        if (valid_dob.toString().isEmpty() || valid_country.toString().isEmpty() || valid_email.toString().isEmpty() || valid_phone.toString().isEmpty() ) { needChange = true; return false; }
        
        /* Checking for valid input. */
        if (valid_dob.toString().length() == 0 || valid_country.toString().length() == 0 || valid_email.toString().length() == 0 || valid_phone.toString().length() == 0) {
            return false;
        }

        /* Checking to see if someone is 18 at time of review. */
        if ((int)curr.getYear() - (int)valid_dob.getValue().getYear() == 18) {
            /* 283-314 < 0, INVALID */
            if((int)curr.getDayOfYear() - (int)valid_dob.getValue().getDayOfYear() < 0) { return false; }
        }
        /* 2023-2006 = 17, INVALID */
        else if ((int)curr.getYear() - (int)valid_dob.getValue().getYear() < 18) { return false; }

        // /* After importing Locale, we can check whether the country we entered is valid. */
        // Arrays.sort(localeCntry);
        // if (Arrays.binarySearch(localeCntry, valid_country.toString()) < 0) { return false; }
        // Need debug
        
        /* If email is in correct format. Looks like this:
            * [string1] @ [email] . [end]
            */

            /* Email: someEmail@gmail.com
            * nameMail = [someEmail, gmail.com]. Length: 2
            * nameMail[1] = "gmail.com"
            * nameMail[1].split(".") = [gmail, com]. Length: 2
            * Valid email!
            */
        String[] nameMail = valid_email.toString().split("@");
        if (nameMail.length > 2) { return false; }
        if (nameMail[1].split(".").length > 2) { return false; }

        /* If phone is in correct format. Looks like this: 
            * [1234567890]
            */
        if (valid_phone.toString().length() < 4 || valid_phone.toString().length() > 13) { return false; }

        return true;
    }

    /* If validation returns false, we may 'correct' the application. */
    /* Uses account creation grabbed from accessAC() */
    private void editData(int application_ID, String[] fields) { 
        /* Grabs account creation object. */
        /* Changes some information. */

        /* fields[5]:
         * [0] : valid_dob
         * [1] : valid_country
         * [2] : valid_email
         * [3] : valid_phone
         */
        if (needChange) {
            changedFields = new String[]{fields[0],fields[1],fields[2],fields[3]};
        }

        /* Accesses Account Creation given an applicant's applicant ID. */
        AccountCreation.getAccountCreationByID(application_ID).validateAccountCreationFields(changedFields);
        return;
    }
    
    /* If validation returns false, we may reject the application. */
    /* Uses workflow grabbed from accessWF() */
    private void rejectApplication(int application_ID) {
        /* Grabs workflow object. */
        /* Changes status of that application to DATA_ENTRY. */

        /* This method sends the application back to the workflow and changes the status back to DE based on ID. */
        Workflow.updateWorkflowItem(application_ID, app_status.DATA_ENTRY);

        return;
    }

    /* Shows review screen to reviewer. */
    public void showReviewScreen() {
        /* Does some displaying. */
        /* Done from ReviewController. */
        return;
    }
}
