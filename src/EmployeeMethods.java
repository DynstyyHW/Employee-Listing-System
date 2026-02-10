public class EmployeeMethods {
    private String record1Status;
    private String record2Status;
    private String record3Status;
    private String record4Status;
    private String record5Status;

    public EmployeeMethods() {
        record1Status = "1. Record 1 [UNUSED]";
        record2Status = "2. Record 2 [UNUSED]";
        record3Status = "3. Record 3 [UNUSED]";
        record4Status = "4. Record 4 [UNUSED]";
        record5Status = "5. Record 5 [UNUSED]";
    }

    /**
     * for recordStatus purposes:
     * use 'static EmployeeMethod status = new EmployeeMethods()' in the program to utilize this
     */
    public void viewRecordStatus() {
        System.out.println(record1Status);
        System.out.println(record2Status);
        System.out.println(record3Status);
        System.out.println(record4Status);
        System.out.println(record5Status);
        System.out.println("6. Back to Main Menu");
    }

    public void setRecord1Status(String newRecord1Status) {
        record1Status = newRecord1Status;
    }

    public void setRecord2Status(String newRecord2Status) {
        record2Status = newRecord2Status;
    }

    public void setRecord3Status(String newRecord3Status) {
        record3Status = newRecord3Status;
    }

    public void setRecord4Status(String newRecord4Status) {
        record4Status = newRecord4Status;
    }

    public void setRecord5Status(String newRecord5Status) {
        record5Status = newRecord5Status;
    }
    
    public void employeeListHeader() {
        System.out.printf("%-10s || %-13s || %-30s || %-3s || %-30s || %-16s ||",
                "Record No.","ID No.","Name","Age","Position","Monthly Salary");
    }
}
