import src.app_enums.app_status;
import src.shared_classes.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;


/* Credits: Ti Ervin. */
public class WorkflowTest{

    @Test
    public void testWorkflowMethods(){

        //test makeNewWorkflowItem
        //test the head
        //test the body
        //test the tail
        Workflow.makeNewWorkflowItem(0);
        Workflow.makeNewWorkflowItem(1);
        Workflow.makeNewWorkflowItem(2);
        Workflow.makeNewWorkflowItem(3);

        //test getNextWorkflowItem
        //test the head
        //test the body
        //test the tail
        assertEquals("",0, Workflow.getNextWorkflowItem(app_status.REVIEW));
        assertEquals("", -1, Workflow.getNextWorkflowItem(app_status.APPROVAL));

        //test updateWorkflowItem
        //test the head
        Workflow.updateWorkflowItem(0, app_status.APPROVAL);
        assertEquals("",0, Workflow.getNextWorkflowItem(app_status.APPROVAL));
        //test the body
        //assertEquals();
        //test the tail
        //assertEquals();
    }


    
}