import java.util.Scanner;

public class PayrollSystem {
    static class Employee {
        String name;
        double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        // Monthly Salary Credit: nearest 500, minimum 5,000, maximum 35,000
        double sss() {
            double msc = Math.min(Math.max(Math.round(salary / 500.0) * 500.0, 5000), 35000);
            return msc * 0.05;
        }

        // 5% premium, shared equally; salary floor 10,000, ceiling 100,000
        double philhealth() {
            return Math.min(Math.max(salary, 10000), 100000) * 0.025;
        }

        // 2% of salary, salary capped at 10,000 (max 200)
        double pagibig() {
            return Math.min(salary, 10000) * 0.02;
        }

        // BIR monthly withholding tax (TRAIN law)
        double incomeTax(double t) {
            if (t <= 20833) return 0;
            else if (t <= 33333) return (t - 20833) * 0.15;
            else if (t <= 66667) return 1875 + (t - 33333) * 0.20;
            else if (t <= 166667) return 8541.80 + (t - 66667) * 0.25;
            else if (t <= 666667) return 33541.80 + (t - 166667) * 0.30;
            return 183541.80 + (t - 666667) * 0.35;
        }

        void displayReport() {
            double s = sss(), p = philhealth(), h = pagibig();
            double taxable = salary - (s + p + h);
            double tax = incomeTax(taxable);
            double total = s + p + h + tax;
            System.out.println("Employee Name: " + name);
            System.out.printf("Monthly Salary: %,.2f%n", salary);
            System.out.printf("SSS: %,.2f%n", s);
            System.out.printf("PhilHealth: %,.2f%n", p);
            System.out.printf("Pag-IBIG: %,.2f%n", h);
            System.out.printf("Taxable Income: %,.2f%n", taxable);
            System.out.printf("Withholding Tax: %,.2f%n", tax);
            System.out.printf("Total Deductions: %,.2f%n", total);
            System.out.printf("Net Pay: %,.2f%n", salary - total);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Number of employees: ");
            int count = Integer.parseInt(input.nextLine().trim());
            for (int i = 0; i < count; i++) {
                System.out.println();
                System.out.print("Employee Name: ");
                String name = input.nextLine();
                System.out.print("Monthly Salary: ");
                double salary = Double.parseDouble(input.nextLine().trim());
                System.out.println();
                new Employee(name, salary).displayReport();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered: " + e.getMessage());
        }
    }
}
