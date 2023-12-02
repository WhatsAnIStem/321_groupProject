import src.app_enums.app_status;
import src.shared_classes.*;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/* Credits: Titania Ervin. */
public class WorkflowTest{

    @Before
    public void initWorkflow(){
        Workflow.InitializeWorkflow();
        Workflow.makeNewWorkflowItem(0);
        Workflow.makeNewWorkflowItem(1);
        Workflow.makeNewWorkflowItem(2);
        Workflow.makeNewWorkflowItem(3);
        Workflow.makeNewWorkflowItem(4);
    }

    @After
    public void hideData(){
        Workflow.updateWorkflowItem(0, app_status.DATA_ENTRY);
        Workflow.updateWorkflowItem(1, app_status.DATA_ENTRY);
        Workflow.updateWorkflowItem(2, app_status.DATA_ENTRY);
        Workflow.updateWorkflowItem(3, app_status.DATA_ENTRY);
        Workflow.updateWorkflowItem(4, app_status.DATA_ENTRY);
    }

    @Test
    public void testWorkflowAdditions(){

        assertEquals(false, Workflow.makeNewWorkflowItem(1));
        Workflow.updateWorkflowItem(1, null);
        assertEquals(true, Workflow.makeNewWorkflowItem(1));

        assertEquals(false, Workflow.makeNewWorkflowItem(0));
        Workflow.updateWorkflowItem(0, null);
        assertEquals(true, Workflow.makeNewWorkflowItem(0));
        
        assertEquals(false, Workflow.makeNewWorkflowItem(4));
        Workflow.updateWorkflowItem(4, null);
        assertEquals(true, Workflow.makeNewWorkflowItem(4));

        Workflow.updateWorkflowItem(0, null);
        Workflow.updateWorkflowItem(1, null);
        Workflow.updateWorkflowItem(2, null);
        Workflow.updateWorkflowItem(3, null);
        Workflow.updateWorkflowItem(4, null);

        assertEquals(true, Workflow.makeNewWorkflowItem(1));
        assertEquals(true, Workflow.makeNewWorkflowItem(2));
        assertEquals(true, Workflow.makeNewWorkflowItem(4));
        assertEquals(true, Workflow.makeNewWorkflowItem(3));
        assertEquals(true, Workflow.makeNewWorkflowItem(0));
        //*/
    }

    @Test
    public void testWorkflowGettersAndUpdaters(){

        //test basic functionality
        Workflow.updateWorkflowItem(0, app_status.REVIEW);
        Workflow.updateWorkflowItem(2, app_status.REVIEW);
        Workflow.updateWorkflowItem(1, app_status.REVIEW);
        Workflow.updateWorkflowItem(3, app_status.REVIEW);
        assertEquals("",0, Workflow.getNextWorkflowItem(app_status.REVIEW));
        Workflow.updateWorkflowItem(0, app_status.APPROVAL);
        assertEquals("",1, Workflow.getNextWorkflowItem(app_status.REVIEW));
        Workflow.updateWorkflowItem(1, app_status.APPROVAL);
        assertEquals("",2, Workflow.getNextWorkflowItem(app_status.REVIEW));
        Workflow.updateWorkflowItem(2, app_status.APPROVAL);
        assertEquals("",3, Workflow.getNextWorkflowItem(app_status.REVIEW));
        assertEquals(0, Workflow.getNextWorkflowItem(app_status.APPROVAL));
        Workflow.updateWorkflowItem(0, app_status.DATA_ENTRY);
        assertEquals(1, Workflow.getNextWorkflowItem(app_status.APPROVAL));
        Workflow.updateWorkflowItem(1, app_status.DATA_ENTRY);
        assertEquals(2, Workflow.getNextWorkflowItem(app_status.APPROVAL));
        Workflow.updateWorkflowItem(2, app_status.DATA_ENTRY);
        assertEquals(-1, Workflow.getNextWorkflowItem(app_status.APPROVAL));

    }
}