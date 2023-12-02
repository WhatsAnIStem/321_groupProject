package com.cs321.app;

import com.cs321.app.shared_classes.*;

import static org.junit.Assert.assertEquals;

import org.junit.*;


/* Credits: Diane Hamilton. */
public class AccountCreationTest {

    private AccountCreation ac_TEST = null;
    private AccountCreation ac_TEST_2 = null;
    private AccountCreation ac_TEST_3 = null;

    private String[] fieldsList = 
        {
            "Chippy Mcdonald",
            "Feb/2/1996",
            "165",
            "165",
            "Green",
            "Ireland",
            "chippy_m334@email.com",
            "1234567890",
            "123 chippy ln, Ireland 22552"
        };

    private String[] fieldsList_TEST = 
        {
            "Horizon Hall",
            "Jan/21/2023",
            "100002",
            "100002",
            "Blue",
            "France",
            "horizon4456_hall@email.com",
            "0987654321",
            "987 horizon road, France 25522"
        };

    private String[] fieldsList_TEST_3 = 
        {
            "Tom Lee",
            "May/23/1998",
            "151512",
            "151512",
            "Brown",
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
    public void createAccountCreation_test() {

        /* Checks return of createaccountcreation. */
    }

    @Test
    public void getAccountCreationByID_test() {  
        AccountCreation ac_TEST_4 = null;      

        /* Returns an accoutn based on its ID. */
        assertEquals("", ac_TEST  , AccountCreation.getAccountCreationByID(1));
        assertEquals("", ac_TEST_2, AccountCreation.getAccountCreationByID(10));
        assertEquals("", ac_TEST_3, AccountCreation.getAccountCreationByID(100));
        assertEquals("", ac_TEST_4, AccountCreation.getAccountCreationByID(1000));
    }

    @Test
    public void validateAccountCreationFields_test() {

        /* Testing to see if String[] input is valid or not. */
    }

    @Test
    public void finalizeAccountCreation_test() {
        ac_TEST.finalizeAccountCreation(1);
        ac_TEST_2.finalizeAccountCreation(10);


        /* Checks to see if the finalize acc creation activated. This can be checked via the status of the application being null or its ID. */
        /* The account should no longer exist. */
        assertEquals("", null, ac_TEST.getAccountCreationByID(1));
        assertEquals("", null, ac_TEST_2.getAccountCreationByID(10));

        /* Not deleted yet. */
        assertEquals("", ac_TEST_3, ac_TEST_3.getAccountCreationByID(100));

        /* Deleted. */
        ac_TEST_3.finalizeAccountCreation(100);
        assertEquals("", null, ac_TEST_3.getAccountCreationByID(100));


    }

}
    