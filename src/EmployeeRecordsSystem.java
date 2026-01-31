import java.util.*;
import java.lang.*;

public class EmployeeRecordsSystem {
    static Scanner scan = new Scanner(System.in);
    static Employee[] currentRecord;
    static Employee[] record1;
    static Employee[] record2;
    static Employee[] record3;
    static Employee[] record4;
    static Employee[] record5;

    static EmployeeMethods statusMethods = new EmployeeMethods();
    static EmployeeMethods listMethods = new EmployeeMethods();

    static int choice;
    static boolean repeat;

    public static void main(String[] args) {
        EmployeeRecordsSystem program;
        try {
            program = new EmployeeRecordsSystem();
            program.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    } // end of main()

    public void run() throws Exception {
        System.out.println("\nWelcome to the DXM Employee Data Systems Program!\n" +
                "Main Menu\n" +
                "----------------------------------------------------");
        System.out.print("""
                            Which program would you like to use?
                            1. Create new data list [REQUIRED IF PROGRAM JUST STARTED]
                            2. View specific record (unsorted)
                            3. View specific record (with different sorts)
                            4. Change items in a specific record
                            5. Exit Program
                            """);
        choice = readNumber("Input choice here: ", 1, 6);
        switch (choice) {
            case 1 -> inputEmployeeProgram();
            case 2 -> viewRecordsProgram();
            case 3 -> sortRecordsProgram();
            case 4 -> changeDataProgram();
            case 5 -> {
                System.out.println("\nClosing System... Goodbye.");
                System.exit(0);
            }
        }
    } // end of run()

    private void inputEmployeeProgram() throws Exception {
        System.out.println("\nWhich record would you like to view?");
            statusMethods.viewRecordStatus();
            choice = readNumber("Input choice here: ", 1, 6);
            currentRecord = currentRecordChoice(choice);

        int recordQuantity = readNumber("\nInput the number of employees that will be listed in this record: ", 1, 55);
        updateRecordInformation(choice, recordQuantity);

        currentRecord = new Employee[recordQuantity];

        System.out.println("A new employee record has been made!");
        for (int count = 0; count < currentRecord.length; count++) {
            System.out.println("\nInputting the record for Employee No." + (count + 1) + "...");
            currentRecord[count] = interviewEmployee();
        }
        System.out.println("\nFinished inputting every employee's data!\n");

        updateRecordData(choice);

        run();
    } // end of inputEmployeeProgram()

    private void viewRecordsProgram() throws Exception {
        System.out.println("\nWhich record would you like to use?");
            statusMethods.viewRecordStatus();
        choice = readNumber("Input choice here: ", 1, 6);
            currentRecord = currentRecordChoice(choice);

        showRecord(currentRecord);
        run(); // back to mainProgram
    } // end of viewRecordsProgram()

    private void sortRecordsProgram() throws Exception {
        System.out.println("\nWhich record would you like to use?");
            statusMethods.viewRecordStatus();
        choice = readNumber("Input choice here: ", 1, 6);
            currentRecord = currentRecordChoice(choice);

        showRecord(currentRecord);
        System.out.println("""
                            
                            How would you like to sort this list:
                            1. First Name (A-Z)
                            2. First Name (Z-A)
                            3. Middle Name (A-Z)
                            4. Middle Name (Z-A)
                            5. Last Name (A-Z)
                            6. Last Name (Z-A)
                            7. Age (Youngest to Oldest)
                            8. Age (Oldest to Youngest)
                            9. Position (A-Z)
                            10. Position (Z-A)
                            11. Monthly Salary (Least to Most)
                            12. Monthly Salary (Most to Least)
                            13. Back to Selection of Records
                            """);
        choice = readNumber("Input choice here: ", 1, 13);
            switch (choice) {
                case 1 -> sortFirstNameA2ZMethod(currentRecord);
                case 2 -> sortFirstNameZ2AMethod(currentRecord);
                case 3 -> sortMiddleNameA2ZMethod(currentRecord);
                case 4 -> sortMiddleNameZ2AMethod(currentRecord);
                case 5 -> sortLastNameA2ZMethod(currentRecord);
                case 6 -> sortLastNameZ2AMethod(currentRecord);
                case 7 -> sortAgeY2OMethod(currentRecord);
                case 8 -> sortAgeO2YMethod(currentRecord);
                case 9 -> sortPositionA2ZMethod(currentRecord);
                case 10 -> sortPositionZ2AMethod(currentRecord);
                case 11 -> sortMonthlySalaryL2MMethod(currentRecord);
                case 12 -> sortMonthlySalaryM2LMethod(currentRecord);
                case 13 -> { return; }
            }
        showRecord(currentRecord);
        run(); // back to mainProgram
    } // end of sortRecordsProgram()

    private void changeDataProgram() throws Exception {
        System.out.println("\nWhich record would you like to use?");
            statusMethods.viewRecordStatus();
        choice = readNumber("Input choice here: ", 1, 6);
            currentRecord = currentRecordChoice(choice);

        do { repeat = false;
            showRecord(currentRecord);
            int recordNo = readNumber("\nWhich Record No. would you like to use?: ", 1, currentRecord.length);
                Employee currentEmployee = currentRecord[recordNo - 1];

            System.out.println("\nRecord of Employee No." + recordNo);
                listMethods.employeeListHeader();
            System.out.println();
                currentEmployee.showEmployeeData(recordNo - 1);

            System.out.println("""
                    
                    Which data of the chosen Employee's record would you like to change:
                    1. ID Number
                    2. Age
                    3. Position
                    4. Monthly salary
                    5. Back to Selection of Record No.
                    
                    """);
            choice = readNumber("Input choice here: ", 1, 8);
                switch (choice) {
                    case 1 -> {
                        System.out.println("Current ID Number: " + currentEmployee.getId());
                        currentEmployee.setId(readString("Input new ID Number: "));
                    }
                    case 2 -> {
                        System.out.println("Current Age: " + currentEmployee.getAge());
                        currentEmployee.setAge(readNumber("Input new Age: ", 18, 120));
                    }
                    case 3 -> {
                        System.out.println("Current Company Position: " + currentEmployee.getMiddleName());
                        currentEmployee.setMiddleName(readString("Input new Company Position: "));
                    }
                    case 4 -> {
                        System.out.println("Current Monthly Salary: " + currentEmployee.getMonthlySalary());
                        currentEmployee.setMonthlySalary(readNumber("Input new Monthly Salary: ", 2500.00, 100000.00));
                    }
                    case 5 -> { return; }
                } // end of switch(choice)

            System.out.println("\nNew Record of Employee No." + recordNo);
                listMethods.employeeListHeader();
            currentEmployee.showEmployeeData(recordNo - 1);
            choice = readNumber("""
                                        Would you still like to change a data on an employee's record?
                                        1. Yes
                                        2. No
                                        """,1, 2);
                if (choice == 1) repeat = true;
                    else run();
        } while (repeat);
    } // end of changeDataProgram

    private Employee interviewEmployee() {
        System.out.print("Input your ID Number: ");
            String catchID = scan.nextLine();
        System.out.print("Input your First Name: ");
            String catchFirstName = scan.nextLine();
        System.out.print("Input your Middle Name: ");
            String catchMiddleName = scan.nextLine();
        System.out.print("Input your Last Name: ");
            String catchLastName = scan.nextLine();
            int catchAge = readNumber("Input your age: ", 18, 120);
        System.out.print("Input your Position: ");
            String catchPosition = scan.nextLine();
            double catchMonthlySalary = readNumber("Input your Salary per Month (at most 2 decimal places): ", 2500.00, 100000.00);

        return new Employee(catchID, catchFirstName, catchMiddleName, catchLastName, catchPosition, catchAge, catchMonthlySalary);
    } // end of interviewEmployee()

    private void showRecord(Employee[] recordForView) {
        listMethods.employeeListHeader();
        System.out.println();
            for (int count = 0; count < recordForView.length; count++) {
                recordForView[count].showEmployeeData(count);
                System.out.println();
            }
    } // end of showRecord()

    public int readNumber(String prompt, int min, int max) {
        int number = 0;
        boolean repeat;
        do {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(scan.nextLine());
                if (number < min) {
                    System.out.println("You must enter an integer that is not lower than " + min
                            + ". Please try again...\n"); repeat = true;
                } else if (number > max) {
                    System.out.println("You must enter an integer that is not higher than " + max
                            + ". Please try again...\n"); repeat = true;
                } else
                    repeat = false;
            } catch (NumberFormatException x) {
                System.out.println("You may have entered an invalid integer. Please try again...\n");
                repeat = true;
            }
        } while (repeat);
        return number;
    } // end of readNumber(int)

    public double readNumber(String prompt, double doubMin, double doubMax) {
        double number = 0.0;
        boolean repeat;
        do {
            try {
                System.out.print(prompt);
                number = Double.parseDouble(scan.nextLine());
                if (number < doubMin) {
                    System.out.println("You must enter a number that is not lower than " + doubMin
                            + ". Please try again...\n"); repeat = true;
                } else if (number > doubMax) {
                    System.out.println("You must enter a number that is not higher than " + doubMax
                            + ". Please try again...\n"); repeat = true;
                } else
                    repeat = false;
            } catch (NumberFormatException x) {
                System.out.println("You may have entered an invalid number. Please try again...\n");
                repeat = true;
            }
        } while (repeat);
        return number;
    } // end of readNumber(double)

    public String readString(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    } // end of readString()

    /**
     Record Methods
     */
    private Employee[] currentRecordChoice(int input) throws Exception {
        switch (input) {
            case 1 -> currentRecord = record1;
            case 2 -> currentRecord = record2;
            case 3 -> currentRecord = record3;
            case 4 -> currentRecord = record4;
            case 5 -> currentRecord = record5;
            case 6 -> run();
        }
        return currentRecord;
    }

    private void updateRecordData(int input) throws Exception {
        switch (input) {
            case 1 -> record1 = currentRecord;
            case 2 -> record2 = currentRecord;
            case 3 -> record3 = currentRecord;
            case 4 -> record4 = currentRecord;
            case 5 -> record5 = currentRecord;
        }
    }

    private void updateRecordInformation(int input, int quantity) throws Exception {
        switch (input) {
            case 1 -> statusMethods.setRecord1Status("1. Record 1 [" + quantity + " Employee/s Listed]");
            case 2 -> statusMethods.setRecord2Status("2. Record 2 [" + quantity + " Employee/s Listed]");
            case 3 -> statusMethods.setRecord3Status("3. Record 3 [" + quantity + " Employee/s Listed]");
            case 4 -> statusMethods.setRecord4Status("4. Record 4 [" + quantity + " Employee/s Listed]");
            case 5 -> statusMethods.setRecord5Status("5. Record 5 [" + quantity + " Employee/s Listed]");
        }
    }

    /**
        Sorting Methods
     */
    private void verifySort(Employee[] employee, int left, int right) {
        Employee temp;
        temp = employee[left];
        employee[left] = employee[right];
        employee[right] = temp;
    } // end of verifySort()

    private void sortFirstNameA2ZMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count].getFirstName().compareToIgnoreCase(employee[count + 1].getFirstName());
            if (value > 0) {
                verifySort(employee, count, count + 1);
                count = -1;
            }
        }
    }

    private void sortFirstNameZ2AMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count + 1].getFirstName().compareToIgnoreCase(employee[0].getFirstName());
            if (value > 0) {
                verifySort(employee, count + 1, count);
                count = -1;
            }
        }
    }

    private void sortMiddleNameA2ZMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count].getMiddleName().compareToIgnoreCase(employee[count + 1].getMiddleName());
            if (value > 0) {
                verifySort(employee, count, count + 1);
                count = -1;
            }
        }
    }

    private void sortMiddleNameZ2AMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count + 1].getMiddleName().compareToIgnoreCase(employee[count].getMiddleName());
            if (value > 0) {
                verifySort(employee, count + 1, count);
                count = -1;
            }
        }
    }

    private void sortLastNameA2ZMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count].getLastName().compareToIgnoreCase(employee[count + 1].getLastName());
            if (value > 0) {
                verifySort(employee, count, count + 1);
                count = -1;
            }
        }
    }

    private void sortLastNameZ2AMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count + 1].getLastName().compareToIgnoreCase(employee[count].getLastName());
            if (value > 0) {
                verifySort(employee, count + 1, count);
                count = -1;
            }
        }
    }

    private void sortPositionA2ZMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count].getPosition().compareToIgnoreCase(employee[count + 1].getPosition());
            if (value > 0) {
                verifySort(employee, count, count + 1);
                count = -1;
            }
        }
    }

    private void sortPositionZ2AMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            int value = employee[count + 1].getPosition().compareToIgnoreCase(employee[count].getPosition());
            if (value > 0) {
                verifySort(employee, count + 1, count);
                count = -1;
            }
        }
    }

    private void sortAgeY2OMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            boolean verify = employee[count].getAge() > (employee[count + 1].getAge());
            if (verify) {
                verifySort(employee, count, count + 1);
                count = -1;
            }
        }
    }

    private void sortAgeO2YMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            boolean verify = employee[count].getAge() < (employee[count + 1].getAge());
            if (verify) {
                verifySort(employee, count + 1, count);
                count = -1;
            }
        }
    }

    private void sortMonthlySalaryL2MMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            boolean verify = employee[count].getMonthlySalary() > (employee[count + 1].getMonthlySalary());
            if (verify) {
                verifySort(employee, count, count + 1);
                count = -1;
            }
        }
    }

    private void sortMonthlySalaryM2LMethod(Employee[] employee) {
        for (int count = 0; count < employee.length - 1; count++) {
            boolean verify = employee[count].getMonthlySalary() < (employee[count + 1].getMonthlySalary());
            if (verify) {
                verifySort(employee, count + 1, count);
                count = -1;
            }
        }
    }

} // end of EmployeeRecords class
