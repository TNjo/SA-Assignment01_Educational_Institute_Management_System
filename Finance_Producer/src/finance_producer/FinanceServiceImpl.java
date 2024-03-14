package finance_producer;

import java.util.ArrayList;
import java.util.Scanner;

public class FinanceServiceImpl implements FinanceService {

    private ArrayList<Finance> financeDetails = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};
    private static final String[] CLASS_NAMES = {"Maths", "Science", "English", "History", "Geography"};

    public FinanceServiceImpl() {
        // Add sample data to the financeDetails array
        financeDetails.add(new Finance("5498498878979", "Amara", "Maths", "6", getCurrentDate(), 500.0, "March"));
        financeDetails.add(new Finance("5498498878980", "Kamal", "Science", "7", getCurrentDate(), 600.0, "March"));
        financeDetails.add(new Finance("5498498878981", "Nimal", "English", "8", getCurrentDate(), 450.0, "March"));
        financeDetails.add(new Finance("5498479878982", "Nipun", "Maths", "6", getCurrentDate(), 550.0, "March"));
        financeDetails.add(new Finance("5498479878983", "Sadun", "Science", "7", getCurrentDate(), 700.0, "March"));
    }

    @Override
    public void displayFinance() {
        showFinanceDetails();
    }

    @Override
    public void addFinanceDetails() {
        System.out.print("\nEnter Student Name:");
        String studentName = scanner.nextLine();

        System.out.print("Select Class Name:");
        String className = selectClassName();

        System.out.print("Enter Grade:");
        String grade = scanner.nextLine();

        System.out.print("Enter Amount:");
        double amount = scanner.nextDouble();

        String month = selectMonth();

        String id = generateShortUniqueId();

        Finance newFinance = new Finance(id, studentName, className, grade, getCurrentDate(), amount, month);
        financeDetails.add(newFinance);
        System.out.println(" Finance details added successfully! ");
        System.out.println("========================================\n");
    }

    @Override
    public void showFinanceDetails() {
        System.out.println("| FeeID\t\t| Student Name\t| Class Name\t| Grade\t| Date\t\t| Amount\t| Month\t|");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Finance finance : financeDetails) {
            System.out.printf("| %-13s| %-15s| %-14s| %-6s| %-14s| %-14f| %-6s|%n",
                    finance.getId(), finance.getStudentName(), finance.getClassName(),
                    finance.getGrade(), finance.getDate(), finance.getAmount(), finance.getMonth());
        }
        System.out.println("===========================================================================================================");
    }

    @Override
    public void calculateTotalCollectedFees() {
        double totalCollectedFees = 0;
        for (Finance finance : financeDetails) {
            totalCollectedFees += finance.getAmount();
        }
        System.out.println("Total Collected Fees: " + totalCollectedFees);
        System.out.println("========================================\n");
    }

    private String generateShortUniqueId() {
        return Long.toString(System.currentTimeMillis());
    }

    private String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }

    private String selectMonth() {
        int choice;
        while (true) {
            System.out.println("Select Month:");
            for (int i = 0; i < MONTHS.length; i++) {
                System.out.print("|" + (i + 1) + ". " + MONTHS[i] + "| \t");
            }
            System.out.println("\n");
            System.out.print("Enter the number corresponding to the month: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if (choice >= 1 && choice <= MONTHS.length) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and " + MONTHS.length + ".");
            }
        }
        return MONTHS[choice - 1];
    }

    private String selectClassName() {
        int choice;
        while (true) {
            System.out.println("Select Class Name:");
            for (int i = 0; i < CLASS_NAMES.length; i++) {
                System.out.print("|" + (i + 1) + ". " + CLASS_NAMES[i] + "| \t");
            }
            System.out.println("\n");
            System.out.print("Enter the number corresponding to the class: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if (choice >= 1 && choice <= CLASS_NAMES.length) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and " + CLASS_NAMES.length + ".");
            }
        }
        return CLASS_NAMES[choice - 1];
    }
    
    @Override
    public int selectClassId() {
        int choice;
        while (true) {
            System.out.println("Select Class Name:");
            for (int i = 0; i < CLASS_NAMES.length; i++) {
                System.out.print("|" + (i + 1) + ". " + CLASS_NAMES[i] + "| \t");
            }
            System.out.println("\n");
            System.out.print("Enter the number corresponding to the class: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if (choice >= 1 && choice <= CLASS_NAMES.length) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and " + CLASS_NAMES.length + ".");
            }
        }
        return choice;
    }

    @Override
    public void getTotalPaidForSubject(int subId) {
        String subjectName = CLASS_NAMES[subId - 1];
        double totalPaidForSubject = 0;

        for (Finance finance : financeDetails) {
            if (finance.getClassName().equals(subjectName)) {
                totalPaidForSubject += finance.getAmount();
            }
        }
        System.out.println("Total Paid for " + subjectName + ": " + totalPaidForSubject );
        System.out.println("========================================\n");
    }
}
