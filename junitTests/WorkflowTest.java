import src.app_enums.app_status;
import src.shared_classes.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;


/* Credits: Titania Ervin. */
public class WorkflowTest{

    @Test
    public void testWorkflowAdditions(){

        //test makeNewWorkflowItem
        Workflow.makeNewWorkflowItem(1);
        Workflow.makeNewWorkflowItem(2);
        Workflow.makeNewWorkflowItem(4);
        Workflow.makeNewWorkflowItem(3);
        Workflow.makeNewWorkflowItem(0);
    }

    @Test
    public void testWorkflowGettersAndUpdaters(){

        //seed the workflow so we can test the data manipulation functions
        testWorkflowAdditions();

        //test basic functionality
        assertEquals("",1, Workflow.getNextWorkflowItem(app_status.REVIEW));
        assertEquals("", -1, Workflow.getNextWorkflowItem(app_status.APPROVAL));

        //test the tail
        Workflow.updateWorkflowItem(0, app_status.APPROVAL);
        assertEquals("",0, Workflow.getNextWorkflowItem(app_status.APPROVAL));

        //test the body
        Workflow.updateWorkflowItem(4, app_status.APPROVAL);
        assertEquals("",4, Workflow.getNextWorkflowItem(app_status.APPROVAL));

        //test updateWorkflowItem
        //test the head
        Workflow.updateWorkflowItem(1, app_status.APPROVAL);
        assertEquals("",1, Workflow.getNextWorkflowItem(app_status.APPROVAL));

        //remove the last two review workflow items from the workflow and make sure there are no more review items pending
        Workflow.updateWorkflowItem(2, null);
        Workflow.updateWorkflowItem(3, null);
        assertEquals("", -1, Workflow.getNextWorkflowItem(app_status.REVIEW));
    }
}