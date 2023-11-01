package src;

import src.app_enums.app_status;
import src.shared_classes.AccountCreation;
import src.shared_classes.Workflow;


/* UI import stuff. */
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

import javafx.scene.input.InputEvent;
import javafx.scene.text.Text;
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
    private TextField valid_address;

    private Button submitReview;

    /* Constructor method that initializes status and ID of application. */
    public Review(int app_ID, app_status status) { /* app_staus! */
        this.application_ID = app_ID; 
        this.application_status = status;
    }
    
    /* Opening the UI. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.reviewStage = primaryStage;

        /* Showing the fxml file to the user. */
        Parent myFXML = FXMLLoader.load(this.getClass().getResource("./MainForm.fxml"));
        reviewScene = new Scene(myFXML, 400, 300);
        reviewStage.setTitle("Review Application Window");
        reviewStage.setScene(reviewScene);
        reviewStage.show();
    }

    /* Accesses workflow given application ID. */
    private Workflow accessWorkflow() { /* workflow! */
        /* Searches thru workflow based on app ID. */
        /* Returns that workflow item. */
        return null;
    }

    /* Accesses accountcreation given some application ID. */
    private AccountCreation accessAccountCreation() { /* accountCreation! */
        /* Grabs some AC object given some application ID. */
        /* Returns that block of AC data. */
        return null;
    }

    /* Validates information. Returns true if info is correct, else false. */
    private boolean validateApplication() { 
        /* Grabs account creation information of an applicant from the UI. */
        /* Validates information. */
        /* If correct, true. Else, false. */

        /* Harvesting user input from the fxml file. */
        valid_dob = (DatePicker)reviewScene.lookup("#field_dob");
        valid_country = (TextField)reviewScene.lookup("#field_coo");
        valid_email = (TextField)reviewScene.lookup("#field_email");
        valid_phone = (TextField)reviewScene.lookup("#field_phoneNumber");
        valid_address = (TextField)reviewScene.lookup(("#field_mailingAddress"));
        /* Button for submitting review to the workflow. */
        submitReview = (Button)reviewScene.lookup("#button_submit");

        /* Used to check whether or given information is valid. */
        boolean is_valid = true;
        int empty_fields[] = {0,0,0,0,0};


        LocalDate curr = LocalDate.now();
        String[] localeCntry = Locale.getISOCountries();
        

        /* Checks bounds for dob, email, phone, and eyeColor. 
         *
         * DOB: 18 < AGE
         * Country: Country exists?
         * Email: Email exists?
         * Phone: Phone exists?
         * Address: Address exists?
         * 
         * submitReview: Button pressed after validations finalized.
         */

        while (is_valid == true) {
            /* Checking to see if someone is 18 at time of review. */
            if (curr.getYear() - valid_dob.getValue().getYear() == 18) {
                /* 283-314 < 0, INVALID */
                if(curr.getDayOfYear() - valid_dob.getValue().getDayOfYear() < 0) { is_valid = false; }
            }
            /* 2023-2006 = 17, INVALID */
            else if (curr.getYear() - valid_dob.getValue().getYear() < 18) { is_valid = false; }

            /* After importing Locale, we can check whether the country we entered is valid. */


            break;
        }

        return is_valid;
    }

    /* If validation returns false, we may 'correct' the application. */
    /* Uses account creation grabbed from accessAC() */
    private void editData() { 
        /* Grabs account creation object. */
        /* Changes some information. */
        return;
    }
    
    /* If validation returns false, we may reject the application. */
    /* Uses workflow grabbed from accessWF() */
    private void rejectApplication() {
        /* Grabs workflow object. */
        /* Changes status of that application to DATA_ENTRY. */
        return;
    }

    /* Shows review screen to reviewer. */
    public void showReviewScreen() {
        /* Does some displaying. */
        return;
    }




}
