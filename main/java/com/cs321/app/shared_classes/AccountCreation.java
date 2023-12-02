package com.cs321.app.shared_classes;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.cs321.app.app_enums.app_eyecolor;

import java.io.File;
import java.io.PrintStream;

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

    /* global fields for keeping track of the amount of applications (we assign the IDs sequentially)*/
    private static int application_IDTracker = getNextAppId();


    /* fields for consistent and easy input field access */
    public static final short FIELD_APPLICATIONID = 0;
    public static final short FIELD_ALIENNUMBER = 1;
    public static final short FIELD_NAME = 2;
    public static final short FIELD_DOB = 3;
    public static final short FIELD_HEIGHT = 4;
    public static final short FIELD_WEIGHT = 5;
    public static final short FIELD_EYECOLOR = 6;
    public static final short FIELD_COUNTRYOFORIGIN= 7;
    public static final short FIELD_EMAIL = 8;
    public static final short FIELD_PHONENO = 9;
    public static final short FIELD_MAILINGADDRESS = 10;
    public static final short FIELD_NUMFIELDS = 11;

    public static final List<String> EYE_COLOR_VALUES = buildEyeColorList();
    private static final String FILEPATH = "./src/main/java/com/cs321/app/shared_classes/AccountCreationData";

    /* Demographics. */
    int alien_number = -1;
    int application_ID = -1;
    String name = ""; /* [First Name] [Last Name] */
    String dob = ""; /* month/day/year */
    String height = ""; /* In inches. */
    String weight = ""; /* In pounds. */
    app_eyecolor eye_color = null; /* <app_eyecolor>: Br/BL/Gr */
    String country_of_origin = "";
    String email = "";
    String phone_no = ""; /* 1234567890 */
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

        /* Parsing thru the list. */
        this.alien_number = Integer.parseInt(fieldsList[FIELD_ALIENNUMBER]);
        this.application_ID = Integer.parseInt(fieldsList[FIELD_APPLICATIONID]);
        this.name = fieldsList[FIELD_NAME];
        this.dob = fieldsList[FIELD_DOB];
        this.height = fieldsList[FIELD_HEIGHT];
        this.weight = fieldsList[FIELD_WEIGHT];
        this.eye_color = parseEyeColor(fieldsList[FIELD_EYECOLOR]);
        this.country_of_origin = fieldsList[FIELD_COUNTRYOFORIGIN];
        this.email = fieldsList[FIELD_EMAIL];
        this.phone_no = fieldsList[FIELD_PHONENO];
        this.mailing_address = fieldsList[FIELD_MAILINGADDRESS];
    }

    /* Credits: Titania Ervin */
    //DONE
    private static LinkedList<String> buildEyeColorList(){
        LinkedList<String> list = new LinkedList<String>();
        list.add("Brown");
        list.add("Blue");
        list.add("Green");
        return list;
    }
    
    //DONE
    public static app_eyecolor parseEyeColor(String eyeColor){
        /* Checking eyecolor. */
        if (eyeColor.toUpperCase().contentEquals("BROWN")) { return app_eyecolor.BROWN; }
        else if (eyeColor.toUpperCase().contentEquals("BLUE")) { return app_eyecolor.BLUE; }
        else if (eyeColor.toUpperCase().contentEquals("GREEN")) { return app_eyecolor.GREEN; }
        /* Base case for invalid eyecolor. */
        return null;
    }

    //DONE
    public static String parseEyeColor(app_eyecolor eyeColor){
        if(eyeColor.equals(app_eyecolor.BROWN)){
            return "brown";
        }
        if(eyeColor.equals(app_eyecolor.BLUE)){
            return "blue";
        }
        if(eyeColor.equals(app_eyecolor.GREEN)){
            return "green";
        }
        return null;
    }


    /* Credits: Ti Ervin. */
    //DONE
    public static int createNewAccountCreation(String[] fieldsList) {
        /* Updates "database" with new information parced from String[] fieldsList. */
        //make a new accountCreation...
        //assign an applicantid
        //save to database...
        fieldsList[FIELD_ALIENNUMBER] = "-1";
        fieldsList[FIELD_APPLICATIONID] = ("" +  application_IDTracker);
        application_IDTracker++;
        AccountCreation ans = new AccountCreation(fieldsList);
        saveAccountCreationToDatabase(ans);
        return ans.application_ID;
    }

    /* Credits: Diane Hamilton, Titania Ervin. getAccoutnCreationByID AC obj. */
    //DONE
    public static AccountCreation getAccountCreationByID(int app_ID) {
        /* Asks database for the application ID. */
        Scanner reader = null;
        File parent = new File(FILEPATH);
        for(File child: parent.listFiles()){
            if(child.getName().equals(app_ID + "")){
                //if we found the child we want, read and build, return
                String[] fieldsList = new String[FIELD_NUMFIELDS];
                try{
                    reader = new Scanner(FILEPATH + "/" + app_ID);
                    fieldsList[FIELD_APPLICATIONID] = reader.nextLine();
                    fieldsList[FIELD_ALIENNUMBER] = reader.nextLine();
                    fieldsList[FIELD_NAME] = reader.nextLine();
                    fieldsList[FIELD_DOB] = reader.nextLine();
                    fieldsList[FIELD_HEIGHT] = reader.nextLine();
                    fieldsList[FIELD_WEIGHT] = reader.nextLine();
                    fieldsList[FIELD_EYECOLOR] = reader.nextLine();
                    fieldsList[FIELD_COUNTRYOFORIGIN] = reader.nextLine();
                    fieldsList[FIELD_EMAIL] = reader.nextLine();
                    fieldsList[FIELD_PHONENO] = reader.nextLine();
                    fieldsList[FIELD_MAILINGADDRESS] = reader.nextLine();
                    AccountCreation ans = new AccountCreation(fieldsList);
                    reader.close();
                    return ans;
                }
                catch(Exception E){
                    if(reader != null){
                        reader.close();
                    }
                    System.err.println(E);
                    return null;
                }
            }
        }
        /* Creates a new AccountCreation obj - calls constructor. */
        return null;
    }

    /* Credits: Diane Hamilton. validateAccountCreationFields checks if things are blank or invalid. */
    public static boolean validateAccountCreationFields(String[] fieldsList) {
        /* Parces through fieldsList. */
        /* Checks information for correctness. */
        /* Returns true for correct, else false. */

        /* Does not accept invalid input for validation. */
        if (fieldsList.length != 9) { return false; }
        for (int i = 0; i < fieldsList.length; i++) {
            /* Loops thru fields list to see if we have empty fields. */
            if (fieldsList[i].length() == 0) { return false; }
            if (fieldsList[i].compareTo(null) == 0) { return false; }
            if (fieldsList[i].isEmpty()) { return false; }
        }

        return true;
    }

    /* Credits: Diane Hamilton, Titania Ervin finalizeAccountCreation finalizes the account creation object and creates a new alien_number. */
    //DONE
    public static void finalizeAccountCreation(int app_ID) {
        /* Receives an app_ID. */
        /* Searches databases for app_ID. */
        /* Creates alien_number for that application. */
        /* Finalizes application and calls method that sends out email to applicant saying they've been given an alien number. */
        AccountCreation child = getAccountCreationByID(app_ID);
        if(child == null){
            return;
        }
        /* Alien_number assignment. */
        int hash = String.valueOf((String.valueOf(52*(app_ID%10+10*15+1-60)%4+2).hashCode()*app_ID)).toUpperCase().hashCode();
        if (hash < 0) { hash *= (-1); }
        child.alien_number = hash;
        saveAccountCreationToDatabase(child);

        /* Changes status to null to remove it from workflow without really removing it so we have it backed up. */
        Workflow.updateWorkflowItem(app_ID, null);

        /* Imagine there is something sending an email here. */
        //INTERFACE FOR EMAIL MODULE (NO NEED TO IMPLEMENT)
    }    

    //saves an accountcreation to the "database", this is the only method that does any writing
    //DONE
    private static boolean saveAccountCreationToDatabase(AccountCreation ac){
        //open file of ac's application id...
        PrintStream out = null;
        try{
            out = new PrintStream(new File(FILEPATH + "/" + ac.application_ID));
            out.println(ac.alien_number);
            out.println(ac.application_ID);
            out.println(ac.name);
            out.println(ac.dob);
            out.println(ac.height);
            out.println(ac.weight);
            out.println(parseEyeColor(ac.eye_color));
            out.println(ac.country_of_origin);
            out.println(ac.email);
            out.println(ac.phone_no);
            out.println(ac.mailing_address);
            out.close();
        }
        catch(Exception E){
            if(out != null){
                out.close();
            }
            return false;
        }
        return true;
    }

    //returns the next appid
    private static int getNextAppId(){
        //scan file, get the highest number... next id is that +1
        int ans = 0;
        try{
            File top = new File(FILEPATH);
            File[] children = top.listFiles();
            for(File child: children){
                ans = Math.max(ans, Integer.parseInt(child.getName()));
            }
            ans++;
        }
        catch(Exception E){
            System.err.println(E);
        }
        return ans;
    }
    
    //for testing only :)
    public static void main(String args[]){}
}
