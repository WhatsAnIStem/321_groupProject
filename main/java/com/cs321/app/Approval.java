package com.cs321.app;
import com.cs321.app.shared_classes.AccountCreation;
import com.cs321.app.shared_classes.Workflow;
import com.cs321.app.app_enums.app_status;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;
/*Tan Phat Tran */
public class Approval extends Application{
    private app_status application_status = null; 
    private int application_ID = 0x00000000; 
    private Scene ApprovalScene;
    private Button Approve = new Button("Approve");
    private Button Deny = new Button("Deny");
    private Stage ApprovalStage;

    /*Constructor for initializing the status, ID, and accountcreation class */
    public Approval(int app_ID, app_status status) { 
        this.application_ID = app_ID; 
        this.application_status = status;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {   
        this.ApprovalStage = primaryStage;
        Parent myFXML = FXMLLoader.load(this.getClass().getResource("./MainForm.fxml"));
         ApprovalScene = new Scene(myFXML, 400, 300);
         ApprovalStage.setTitle("Approval Window");
         ApprovalStage.setScene(ApprovalScene);
         ApprovalStage.show();

        Approve = (Button)ApprovalScene.lookup("#button_approve");
        Deny = (Button)ApprovalScene.lookup("#button_deny");
/*the applicant confirms the data looks correct */
EventHandler submitHandler1 = new EventHandler<ActionEvent>(){
    public void handle(ActionEvent a){
        System.out.println("Application Approved!");
        generateEmail();
    }
};
        Approve.setOnAction(submitHandler1);
    /*The applicant will check the data and confirm if it looks good, otherwise the applicant can confirm the
     information is incorrect then the application will be denied and the approval sends the application back to review. */
EventHandler submitHandler2 = new EventHandler<ActionEvent>(){
    public void handle(ActionEvent a){
        System.out.println("Application Denied! \nApplication will now send back to review process.");
        denyApplication(application_ID);
    }
        };
        Deny.setOnAction(submitHandler2);
    }

    public void denyApplication(int application_ID){
        /* Grabs workflow object. */
        /* Changes status of that application to review. */
        /* if denied, send the application back to the workflow to be reviewed 
         * or reedited as needed
         */
         
        Workflow.updateWorkflowItem(application_ID, app_status.REVIEW);
    }
    
    public void generateEmail(){
        /* Sends the user the email if the user approve the application */
            System.out.println("Email generated and sent");
    }
    /* Shows review screen to approval. */
    public void showApprovalScreen() {
        /* Done from ApproveController. */
        return;
    }
}

         
