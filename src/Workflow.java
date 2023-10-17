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

   /* Initializing SLL. */
   public Workflow() {
      this.head = null;
      this.tail = head;
   }
   public Workflow(app_Node node) {
      /* Head initialized. */
      this.head = node;
      this.tail = head;
      length = 1;
   }

   /* Add node to LL. */
   public void makeNewWorkflowItem(int application_ID) {
      app_Node node = new app_Node();
      node.application_ID = application_ID;
      /* Defaults to review. */
      node.application_status = app_status.REVIEW;
   
      /* Sets as head. */
      if (length == 0x00000000) {
         this.head = node;
         this.tail = head;
         return;
      }
      else {
         /* Adds to tail otherwise. */
         tail.next = node;
         this.tail = tail.next;
      }
      length++;
   
      return;
   }

   /* Searches workflow and returns first found workflow item of some status. Returns app_ID of that status. */
   public int getNextWorkflowItem(app_status someStatus) {
      app_Node trav = head;
      
      while (trav != null) {
         if (trav.application_status == someStatus) { 
            return trav.application_ID; }
         trav = trav.next;
      }
   
      /* Status not found -- returns illegal number. */
      return -1;
   }

   /*  Changes status of the workflow item based on ID. */
   public void updateWorkflowItem(int app_ID, app_status someStatus) {
      app_Node trav = head;
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