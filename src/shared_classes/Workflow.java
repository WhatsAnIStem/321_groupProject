package src.shared_classes;

import src.app_enums.app_status;

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
   private app_Node tail = null;
   private int length = 0x00000000;

   private static Workflow workflow = new Workflow();

   /* Initializing SLL. */
   private Workflow() {
      this.head = null;
      this.tail = head;
   }
   private Workflow(app_Node node) {
      /* Head initialized. */
      this.head = node;
      this.tail = head;
      length = 1;
   }

   /* Add node to LL. */
   public static void makeNewWorkflowItem(int application_ID) {
      app_Node node = new app_Node();
      node.application_ID = application_ID;
      /* Defaults to review. */
      node.application_status = app_status.REVIEW;
   
      /* Sets as head. */
      if (workflow.length == 0x00000000) {
         workflow.head = node;
         workflow.tail = workflow.head;
      }
      else {
         /* Adds to tail otherwise. */
         workflow.tail.next = node;
         workflow.tail = workflow.tail.next;
      }
      workflow.length++;
   
      return;
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

   /*  Changes status of the workflow item based on ID. */
   public static void updateWorkflowItem(int app_ID, app_status someStatus) {
      app_Node trav = workflow.head;
      boolean found = false;
      
      /* Grabs the ID from the LL. */
      while (trav != null) {
         if (trav.application_ID == app_ID) { found = true; 
            break; }
         trav = trav.next;
      }
   
      /* Invalid ID. */
      if (!found) { 
         return; }
   
      /* Changes status. */
      trav.application_status = someStatus;
      return;
   }

}