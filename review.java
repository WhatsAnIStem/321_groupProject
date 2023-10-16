public class review {

    /* Private class attributes. */
    private String application_status = "";
    private int application_ID        = 0x0000;
    private int validated             = 0x0000;

    /* Constructor method that initializes status and ID of application. */
    public review(int app_ID, String status) {
        this.application_ID     = app_ID;
        this.application_status = status;
        this.validated          = 0;
    }

    /* Class Methods. */
    Object accessWorkflow(int application_ID) {
        return null;
    }


    public static void main(String args[]) {
        System.out.println("Test");
    }

}