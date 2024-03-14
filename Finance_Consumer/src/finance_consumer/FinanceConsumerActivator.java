package finance_consumer;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import finance_producer.FinanceService;

public class FinanceConsumerActivator implements BundleActivator {

    ServiceReference serviceReference;
    FinanceService financeService;

    public void start(BundleContext context) throws Exception {
        System.out.println("Start FinanceConsumer");
        serviceReference = context.getServiceReference(FinanceService.class.getName());
        financeService = (FinanceService) context.getService(serviceReference);

        handleActions();
    }

    public void stop(BundleContext context) throws Exception {

        System.out.println("Good Bye");
        context.ungetService(serviceReference);

    }
    
    void handleActions() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("----------------------------");
            System.out.println("Choose an action:");
            System.out.println("----------------------------");
            System.out.println("1. Collect Class Fees");
            System.out.println("2. Display Paid Details");
            System.out.println("3. Calculate Total Collected Fees");
            System.out.println("4. Get Total Paid for a Subject");
            System.out.println("5. Exit\n");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        financeService.addFinanceDetails();
                        break;
                    case 2:
                        financeService.showFinanceDetails();
                        break;
                    case 3:
                        financeService.calculateTotalCollectedFees();
                        break;
                    case 4:
                    	int subId = financeService.selectClassId();
                        financeService.getTotalPaidForSubject(subId);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number.\n\n");
                scanner.nextLine();
            }
        }
    }
}
