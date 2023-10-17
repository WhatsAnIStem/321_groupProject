package src.shared_classes;

import src.app_enums.app_eyecolor;

/* Credits: Diane Hamilton. Ti Ervin. */
/* Account creation class that creates an account. */
public class AccountCreation {

    /* This class "calls" the database.
     * Its methods are used to transfer data between the database in which the applicant's data is stored.
     * It acts as a sort of middle-man between the database and the individuals.
     */

    /*
        - application_ID : int
        - alien_number : int
        - name : string
        - dob : date
        - height : int
        - weight : int
        - eye_color : string
        - country_of_origin : string
        - email : string
        - phone_no : int
        - mailing_address : string
        --------------------------------
        + createAccountCreation(string[]) : int 
        + getAccountCreationByID(int) : AccountCreation ************
        + validateAccountCreationFields(string[]) : boolean 
        + finalizeAccountCreation(int) : void
        - saveAccountCreationToDatabase() : void
        - getAccountCreationFromDatabase(int) : void
        - sendEmail(emailType) : void
     */

     /* For tracking application. */
    static int alien_number = 0;
    private int application_ID = 0;

    /* Demographics. */
    String name = ""; /* [First Name] [Last Name] */
    String dob = ""; /* month/day/year */
    int height = 0; /* In inches. */
    int weight = 0; /* In pounds. */
    app_eyecolor eye_color = null; /* <app_eyecolor>: Br/BL/Gr */
    String country_of_origin = "";
    String email = "";
    int phone_no = 0; /* 1234567890 */
    String mailing_address = "";

    /* Private constructor. */
    private AccountCreation(String[] fieldsList) {
        /*
         * All but alien_number
         * 
         * 0: name
         * 1: dob
         * 2: height
         * 3: weight
         * 4: eye_color
         * 5: country_of_origin
         * 6: email
         * 7: phone_no
         * 8: mailing_add
         */

        /* Parcing thru the list. */
        this.name = fieldsList[0];
        this.dob = fieldsList[1];
        this.height = Integer.parseInt(fieldsList[2]);
        this.weight = Integer.parseInt(fieldsList[3]);

        /* Checking eyecolor. */
        if (fieldsList[4].toUpperCase().contentEquals("BROWN")) { this.eye_color = app_eyecolor.BROWN; }
        else if (fieldsList[4].toUpperCase().contentEquals("BLUE")) { this.eye_color = app_eyecolor.BLUE; }
        else if (fieldsList[4].toUpperCase().contentEquals("GREEN")) { this.eye_color = app_eyecolor.GREEN; }
        /* Base case for invalid eyecolor. */
        else { this.eye_color = null; }
        
        /* Continuing the parse. */
        this.country_of_origin = fieldsList[5];
        this.email = fieldsList[6];
        this.phone_no = Integer.parseInt(fieldsList[7]);
        this.mailing_address = fieldsList[8];
    }


    /* Credits: Ti Ervin. */
    public static int createAccountCreation(String[] fieldsList) {
        /* Updates database with new information parced from String[] fieldsList. */
        return 0;
    }

    /* Credits: Diane Hamilton. getAccoutnCreationByID AC obj. */
    public static AccountCreation getAccountCreationByID(int app_ID) {
        /* Asks database for the application ID. */
        /* Creates a new AccountCreation obj - calls constructor. */
        return null;
    }

    /* Credits: Diane Hamilton. validateAccountCreationFields checks if things are blank or invalid. */
    public static boolean validateAccountCreationFields(String[] fieldsList) {
        /* Parces through fieldsList. */
        /* Checks information for correctness. */
        /* Returns true for correct, else false. */
        return false;
    }

    /* Credits: Diane Hamilton. finalizeAccountCreation finalizes the account creation object and creates a new alien_number. */
    public static void finalizeAccountCreation(int app_ID) {
        /* Receives an app_ID. */
        /* Searches databases for app_ID. */
        /* Creates alien_number for that application. */
        /* Finalizes application and calls method that sends out email to applicant saying they've been given an alien number. */
        return;
    }    
    
}
