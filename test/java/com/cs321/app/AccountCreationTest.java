package com.cs321.app;

import com.cs321.app.app_enums.app_eyecolor;
import com.cs321.app.shared_classes.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.*;


/* Credits: Diane Hamilton. */
public class AccountCreationTest {

    private AccountCreation ac_TEST = null;
    private AccountCreation ac_TEST_2 = null;
    private AccountCreation ac_TEST_3 = null;

    private String[] fieldsList = 
        {
            "-1",
            "-2",
            "Chippy Mcdonald",
            "Feb/2/1996",
            "165",
            "165",
            "green",
            "Ireland",
            "chippy_m334@email.com",
            "1234567890",
            "123 chippy ln, Ireland 22552"
        };

    private String[] fieldsList_TEST = 
        {
            "-1",
            "-3",
            "Horizon Hall",
            "Jan/21/2023",
            "100002",
            "100002",
            "blue",
            "France",
            "horizon4456_hall@email.com",
            "0987654321",
            "987 horizon road, France 25522"
        };

    private String[] fieldsList_TEST_3 = 
        {
            "-1",
            "-4",
            "Tom Lee",
            "May/23/1998",
            "151512",
            "151512",
            "brown",
            "China",
            "tom_lee223@email.com",
            "3216549870",
            "654 lee highway, China 22185"
        };



    /* Initializes account creation object so we can successfully test. */
    private void initializeTest() {
        /* Initializes tests. */

        ac_TEST = null;
        ac_TEST_2 = null;
        ac_TEST_3 = null;


        /*
        ac_TEST.name = fieldsList[0];
        ac_TEST.dob = fieldsList[1];
        ac_TEST.height = Integer.parseInt(fieldsList[2]);
        ac_TEST.weight = Integer.parseInt(fieldsList[3]);
        ac_TEST.eye_color = app_eyecolor.GREEN;
        ac_TEST.country_of_origin = fieldsList[5];
        ac_TEST.email = fieldsList[6];
        ac_TEST.phone_no = Integer.parseInt(fieldsList[7]);
        ac_TEST.mailing_address = fieldsList[8];


        ac_TEST_2.name = fieldsList_TEST[0];
        ac_TEST_2.dob = fieldsList_TEST[1];
        ac_TEST_2.height = Integer.parseInt(fieldsList_TEST[2]);
        ac_TEST_2.weight = Integer.parseInt(fieldsList_TEST[3]);
        ac_TEST_2.eye_color = app_eyecolor.BLUE;
        ac_TEST_2.country_of_origin = fieldsList_TEST[5];
        ac_TEST_2.email = fieldsList_TEST[6];
        ac_TEST_2.phone_no = Integer.parseInt(fieldsList_TEST[7]);
        ac_TEST_2.mailing_address = fieldsList_TEST[8];


        ac_TEST_3.name = fieldsList_TEST_3[0];
        ac_TEST_3.dob = fieldsList_TEST_3[1];
        ac_TEST_3.height = Integer.parseInt(fieldsList_TEST_3[2]);
        ac_TEST_3.weight = Integer.parseInt(fieldsList_TEST_3[3]);
        ac_TEST_3.eye_color = app_eyecolor.BROWN;
        ac_TEST_3.country_of_origin = fieldsList_TEST_3[5];
        ac_TEST_3.email = fieldsList_TEST_3[6];
        ac_TEST_3.phone_no = Integer.parseInt(fieldsList_TEST_3[7]);
        ac_TEST_3.mailing_address = fieldsList_TEST_3[8];      
        */
    }

    @Test
    public void parseEyeColor_test() {
        /* Checks return of parseEyeColor. Both methods. */
        app_eyecolor eye_brown = app_eyecolor.BROWN;
        app_eyecolor eye_blue = app_eyecolor.BLUE;
        app_eyecolor eye_green = app_eyecolor.GREEN;
        String eye_string_brown = "brown";
        String eye_string_blue = "blue";
        String eye_string_green = "green";

        assertEquals("", eye_string_brown, AccountCreation.parseEyeColor(eye_brown));
        assertEquals("", eye_string_blue, AccountCreation.parseEyeColor(eye_blue));
        assertEquals("", eye_string_green, AccountCreation.parseEyeColor(eye_green));
        assertEquals("", eye_brown, AccountCreation.parseEyeColor(eye_string_brown));
        assertEquals("", eye_blue, AccountCreation.parseEyeColor(eye_string_blue));
        assertEquals("", eye_green, AccountCreation.parseEyeColor(eye_string_green));
    }
    @Test
    public void createAccountCreation_test() {
        /* Checks return of createaccountcreation. */
        assertEquals("", -2, AccountCreation.createNewAccountCreation(fieldsList));
        assertEquals("", -3, AccountCreation.createNewAccountCreation(fieldsList_TEST));
        assertEquals("", -4, AccountCreation.createNewAccountCreation(fieldsList_TEST_3));
    }

    @Test
    public void getAccountCreationByID_test() {    
        /* Returns an accoutn based on its ID. */
        createAccountCreation_test();
        assertNotEquals("", null  , AccountCreation.getAccountCreationByID(-2));
        assertNotEquals("", null, AccountCreation.getAccountCreationByID(-3));
        assertNotEquals("", null, AccountCreation.getAccountCreationByID(-4));
    }

    @Test
    public void validateAccountCreationFields_test() {
        /* Testing to see if String[] input is valid or not. */
        assertEquals("", false, AccountCreation.validateAccountCreationFields(new String[]{}));
        assertEquals("", true, AccountCreation.validateAccountCreationFields(fieldsList));
    }

    @Test
    public void finalizeAccountCreation_test() {
        createAccountCreation_test();
        AccountCreation.finalizeAccountCreation(-2);
        AccountCreation.finalizeAccountCreation(-3);
        /* Checks to see if the finalize acc creation activated. This can be checked via the status of the application being null or its ID. */
        /* The account should no longer exist. */
        assertNotEquals("", -1, AccountCreation.getAccountCreationByID(-2).alien_number);
        assertNotEquals("", -1, AccountCreation.getAccountCreationByID(-3).alien_number);

        /* Not deleted yet. */
        assertEquals("", -1, AccountCreation.getAccountCreationByID(-4).alien_number);

        /* Deleted. */
        AccountCreation.finalizeAccountCreation(-4);
        assertNotEquals("", -1, AccountCreation.getAccountCreationByID(-4));


    }

}
    