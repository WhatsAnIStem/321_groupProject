package src;

import src.app_enums.app_status;
import src.shared_classes.AccountCreation;
import src.shared_classes.Workflow;

/* Credits: Diane Hamilton. */
/* Review class. */
public class Review {
    /* Important: Object == workflow, app status, or accCreation before the merge */

    /* Private class attributes. */
    private app_status application_status = null; /* app_status! */
    private int application_ID = 0x00000000;

    /* Private pointers to workflow and AC. */
    private Workflow revFlow = null; /* workflow! */
    private AccountCreation revAC = null; /* accountCreation! */

    /* Constructor method that initializes status and ID of application. */
    public Review(int app_ID, app_status status) { /* app_staus! */
        this.application_ID = app_ID; 
        this.application_status = status;
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
    /* Uses account creation grabbed from accessAC() */
    private boolean validateApplication() { 
        /* Grabs account creation information of an applicant. */
        /* Validates information. */
        /* If correct, true. Else, false. */
        return false;
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
