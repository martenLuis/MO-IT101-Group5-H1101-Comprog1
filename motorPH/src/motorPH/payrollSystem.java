package motorPH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class payrollSystem {
    private static final String EMPLOYEE_DETAILS_FILE = "src\\Employee Details.csv";
    private static final String ATTENDANCE_RECORD_FILE = "src\\Attendance Record.csv";

    public static void main(String[] args) {
        // Step 1: Prompt for employee ID
        String employeeId = promptEmployeeId();
        // Step 2: Read employee details from the CSV file
        String[] employeeDetails = readEmployeeDetails(employeeId);
        if (employeeDetails != null) {
            // Step 3: Display employee details
            displayEmployeeDetails(employeeDetails);

            // Step 4: Display attendance record
            displayAttendanceRecord(employeeId);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static String promptEmployeeId() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Employee ID: ");
            return scanner.nextLine();
        }
    }

    private static void displayAttendanceRecord(String employeeId) {
        try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_RECORD_FILE))) {
            String line;
            boolean found = false;
            // Read the header row
            String header = br.readLine();
            System.out.println(header); // Print the header

            // Read attendance records
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(employeeId)) {
                    // If employee ID matches, print the attendance record
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No attendance record found for employee ID: " + employeeId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] readEmployeeDetails(String employeeId) {
        String[] employeeDetails = null;
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_DETAILS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 19 && parts[0].equals(employeeId)) {
                    employeeDetails = new String[]{parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13], parts[14], parts[15], parts[16], parts[17], parts[18]};
                    break; // Stop searching once employee details are found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }

    private static void displayEmployeeDetails(String[] employeeDetails) {
        if (employeeDetails != null) {
            System.out.println("Last name: " + employeeDetails[0]);
            System.out.println("First name: " + employeeDetails[1]);
            System.out.println("Birthday: " + employeeDetails[2]);
            System.out.println("Address: " + employeeDetails[3]);
            System.out.println("Phone number:" + employeeDetails[4]);
            System.out.println("Sss number:" + employeeDetails[5]);
            System.out.println("Philhealth number:" + employeeDetails[6]);
            System.out.println("Tin number:" + employeeDetails[7]);
            System.out.println("Pag-ibig:" + employeeDetails[8]);
            System.out.println("Status:" + employeeDetails[9]);
            System.out.println("Position:" + employeeDetails[10]);
            System.out.println("Immediate supervisor:" + employeeDetails[11]);
            System.out.println("Basic salary:" + employeeDetails[12]);
            System.out.println("Rice subsidy:" + employeeDetails[13]);
            System.out.println("Phone allowance:" + employeeDetails[14]);
            System.out.println("Clothing allowance:" + employeeDetails[15]);
            System.out.println("Gross semi-monthly rate:" + employeeDetails[16]);
            System.out.println("Hourly Rate:" + employeeDetails[17]);
            double basicSalary = Double.parseDouble(employeeDetails[12]);
            double riceSubsidy = Double.parseDouble(employeeDetails[13]);
            double phoneAllowance = Double.parseDouble(employeeDetails[14]);
            double clothingAllowance = Double.parseDouble(employeeDetails[15]);
            double grossIncome = basicSalary + riceSubsidy + phoneAllowance + clothingAllowance;

            // Deductions
            double sssDeduction = 1000;
            double philhealthDeduction = 500;
            double withholdingTax = 0.10 * (grossIncome - sssDeduction - philhealthDeduction);

            // Calculate net salary
            double netSalary = grossIncome - sssDeduction - philhealthDeduction - withholdingTax;

            System.out.println("Gross Income: " + grossIncome);
            System.out.println("SSS Deduction: " + sssDeduction);
            System.out.println("Philhealth Deduction: " + philhealthDeduction);
            System.out.println("Withholding Tax: " + withholdingTax);
            System.out.println("Net Salary: " + netSalary);
        } else {
            System.out.println("Employee details not found.");
        }
    }
}
