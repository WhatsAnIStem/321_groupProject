package src.shared_classes;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import src.app_enums.app_status;

/* FILE DATA SHOULD BE FORMATTED AS SO:
 * FILE NAME: applicant_id
 * LINE 1: applicant_id
 * LINE 2: app_status
 */

/* Credits: Diane Hamilton. */
/* Linked list by ID. */
class app_Node {
   /* Class attributes. This SLL keeps tracks of items put into the workflow (IDs). This is the backbone of the WF basically. */
   int application_ID = 0x00000000;
   app_status application_status = null;

   /* SLL node. */
   app_Node next = null;
}

/* Workflow. */
public class Workflow {
   private app_Node head = null;
   private int length;

   private static final String FILEPATH = "./src/shared_classes/WorkflowData";
   private static Workflow workflow = new Workflow();

   /* Initializing SLL. */
   private Workflow() {
      this.head = null;
      length = 0;
      //need to read file and set head and tail accordingly.
      File data =  new File(FILEPATH);
      //System.out.println(data.getAbsolutePath());
      //get file children, iterate through, scan data in each file, make node accordingly.
      File[] children = data.listFiles();
      Scanner reader = null;
      String field;
      app_Node node;
      for(File fileItem: children){
         //skip dummy data
         if(fileItem.getName().equals("-1")){
            continue;
         }
         try{
            //prepare node
            reader = new Scanner(fileItem.toPath());
            field = reader.nextLine();
            node = new app_Node();
            node.application_ID = Integer.parseInt(field);
            field = reader.nextLine();
            node.application_status = parseAppStatus(field);
            //insert into workflow
            if(!insertWorkflowNode(node)){
               System.err.println("Failed to insert node id: " + node.application_ID);
            }
         }
         catch(Exception E){
            System.err.println("Error reading file path: " + fileItem.toPath());
            System.err.println(E);
         }
         finally{
            if(reader != null){
               reader.close();
            }
         }
      }
   }

   /* Add node to LL. */
   //precondition: application_ID must be greater than zero for successful addition --ti
   //returns true on success, or false on any failure
   //need to rework this code...
   private static boolean insertWorkflowNode(app_Node newNode){
      app_Node curr = workflow.head;
      if(curr == null){
         workflow.head = newNode;
      }
      else{
         while(curr.next != null && newNode.application_ID > curr.next.application_ID){
            curr = curr.next;
         }
         //at this point, curr's next is either null , the same id as curr, or the right spot..
         if(curr.next.application_ID == newNode.application_ID){
            return false;
         }
         newNode.next = curr.next.next;
         curr.next = newNode;
      }
      workflow.length++;
      return true;
   }

   public static boolean makeNewWorkflowItem(int application_ID) {
       
      if(application_ID < 0){
         return false;
      }
      app_Node node = new app_Node();
      node.application_ID = application_ID;
      /* Defaults to review. */
      node.application_status = app_status.REVIEW;
      if(insertWorkflowNode(node)){
         updateWorkflowFile(node.application_ID, node.application_status);
         return true;
      }
      return false;
   }

   /* Searches workflow and returns first found workflow item of some status. Returns app_ID of that status. */
   public static int getNextWorkflowItem(app_status someStatus) {
      app_Node trav = workflow.head;
      
      while (trav != null) {
         if (trav.application_status == someStatus) { 
            return trav.application_ID; }
         trav = trav.next;
      }
   
      /* Status not found -- returns illegal number. */
      return -1;
   }

   /*  Changes status of the workflow item based on ID. 
    * NEED TO ADD PROPER REMOVAL FUNCTIONALITY, REMOVE TAIL POINTER?
   */
   public static void updateWorkflowItem(int app_ID, app_status someStatus) {
      app_Node curr = workflow.head;
      if(curr == null){
         return;
      }
      else if(curr.application_ID == app_ID){
         //update the head
         if(someStatus == null){
            //reset the head...
            workflow.head = curr.next;
            curr.next = null;
            //delete the file storing the data...
            try{
               new File(FILEPATH + "/" + app_ID).delete();
            }
            catch(Exception E){}
         }
         else{
            //update the node...
            curr.application_status = someStatus;
            //update the file...
            updateWorkflowFile(app_ID, someStatus);
         }
      }
      else{
         while(curr.next != null && curr.next.application_ID != app_ID){
            curr = curr.next;
         }
         //at this point, curr's next is either null or the node we are looking for...
         if(curr.next == null){
            return;
         }
         //curr's next is the node we are looking for.
         //if we intend to remove the node..
         if(someStatus == null){
            //unlink the node and remove the file...
            curr.next = curr.next.next;
            //remove file...
            try{
               new File(FILEPATH + "/" + app_ID).delete();
            }
            catch(Exception E){}
         }
         else{
            //update node and update file...
            curr.next.application_status = someStatus;
            updateWorkflowFile(app_ID, someStatus);
         }
      }
   }

   private static boolean updateWorkflowFile(int app_ID, app_status someStatus){
      PrintStream fileout = null;
      try{
         fileout = new PrintStream(new File(FILEPATH + "/" + app_ID));
         fileout.println(app_ID);
         fileout.println(parseAppStatus(someStatus));
         fileout.flush();
         fileout.close();
         return true;
      }
      catch(Exception E){
         if(fileout != null){
            fileout.close();
         }
         return false;
      }
   }

   private static app_status parseAppStatus(String status){
      if(status.equals("data_entry")){
         return app_status.DATA_ENTRY;
      }
      if(status.equals("review")){
         return app_status.REVIEW;
      }
      if(status.equals("approval")){
         return app_status.APPROVAL;
      }
      return null;
   }

   private static String parseAppStatus(app_status status){
      if(status.equals(app_status.DATA_ENTRY)){
         return "data_entry";
      }
      if(status.equals(app_status.REVIEW)){
         return "review";
      }
      if(status.equals(app_status.APPROVAL)){
         return "approval";
      }
      return null;
   }

   //left in for testing...
   public static void main(String[] args){}
}