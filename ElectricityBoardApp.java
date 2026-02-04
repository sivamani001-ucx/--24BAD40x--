import java.util.Scanner;

class Consumer {

    // Step 7: Private data members (Encapsulation)
    private int consumerNumber;
    private String consumerName;
    private int unitsConsumed;
    private String connectionType;
    private String meterStatus;

    // Step 2: Constructor with limited data
    Consumer(int consumerNumber, String consumerName) {
        this.consumerNumber = consumerNumber;
        this.consumerName = consumerName;
        this.unitsConsumed = 0;
        this.connectionType = "Not Assigned";
        this.meterStatus = "Not Assigned";
    }

    // Step 2: Constructor with complete data
    Consumer(int consumerNumber, String consumerName, int unitsConsumed,
             String connectionType, String meterStatus) {
        this.consumerNumber = consumerNumber;
        this.consumerName = consumerName;
        this.unitsConsumed = unitsConsumed;
        this.connectionType = connectionType;
        this.meterStatus = meterStatus;
    }

    // -------- GETTER METHODS (IMPORTANT FIX) --------
    public int getConsumerNumber() {
        return consumerNumber;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getMeterStatus() {
        return meterStatus;
    }

    // Step 1: Display method
    public void displayConsumer() {
        System.out.println("Consumer Number : " + consumerNumber);
        System.out.println("Consumer Name   : " + consumerName);
        System.out.println("Units Consumed  : " + unitsConsumed);
        System.out.println("Connection Type : " + connectionType);
        System.out.println("Meter Status    : " + meterStatus);
        System.out.println("--------------------------------");
    }

    // Step 3: Update units using public method
    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    // Step 4: Change connection type
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    // Step 5: Update method with same parameter names
    public void updateDetails(int consumerNumber, String consumerName,
                              int unitsConsumed, String connectionType,
                              String meterStatus) {
        this.consumerNumber = consumerNumber;
        this.consumerName = consumerName;
        this.unitsConsumed = unitsConsumed;
        this.connectionType = connectionType;
        this.meterStatus = meterStatus;
    }

    // Step 6 & 8: Bill calculation with temporary object
    public double calculateBill(Consumer c) {

        // Temporary object (used only inside method)
        Consumer tempConsumer =
                new Consumer(999, "Test User", 150, "Domestic", "Normal");

        int fixedCharge = 100;
        int ratePerUnit;
        int applicableUnits;

        if (c.meterStatus.equalsIgnoreCase("Faulty")) {
            applicableUnits = 200;
        } else {
            applicableUnits = c.unitsConsumed;
        }

        if (c.connectionType.equalsIgnoreCase("Domestic")) {
            ratePerUnit = 5;
        } else {
            ratePerUnit = 8;
        }

        return fixedCharge + (applicableUnits * ratePerUnit);
    }
}

public class ElectricityBoardApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Step 1: Runtime input
        System.out.print("Enter Consumer Number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Consumer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Units Consumed: ");
        int units = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Connection Type (Domestic/Commercial): ");
        String type = sc.nextLine();

        System.out.print("Enter Meter Status (Normal/Faulty): ");
        String status = sc.nextLine();

        Consumer runtimeConsumer =
                new Consumer(number, name, units, type, status);

        System.out.println("\nRuntime Consumer Details:");
        runtimeConsumer.displayConsumer();

        // Step 2: Partial and complete object creation
        Consumer partialConsumer = new Consumer(101, "Ravi");
        Consumer fullConsumer =
                new Consumer(102, "Anita", 180, "Domestic", "Normal");

        System.out.println("Partial Consumer:");
        partialConsumer.displayConsumer();

        System.out.println("Full Consumer:");
        fullConsumer.displayConsumer();

        // Step 3: Reference assignment
        Consumer billingRef = partialConsumer;
        billingRef.setUnitsConsumed(220);

        System.out.println("After Updating Using Second Reference:");
        partialConsumer.displayConsumer();
        billingRef.displayConsumer();

        // Step 4: Independent object creation using getters
        Consumer copiedConsumer =
                new Consumer(fullConsumer.getConsumerNumber(),
                             fullConsumer.getConsumerName(),
                             fullConsumer.getUnitsConsumed(),
                             fullConsumer.getConnectionType(),
                             fullConsumer.getMeterStatus());

        copiedConsumer.setConnectionType("Commercial");

        System.out.println("Original Consumer:");
        fullConsumer.displayConsumer();

        System.out.println("New Independent Consumer:");
        copiedConsumer.displayConsumer();

        // Step 5: Update using identical parameter names
        copiedConsumer.updateDetails(500, "Suresh",
                300, "Commercial", "Faulty");

        System.out.println("Updated Consumer Details:");
        copiedConsumer.displayConsumer();

        // Step 6: Bill calculation
        double billAmount = copiedConsumer.calculateBill(copiedConsumer);
        System.out.println("Total Electricity Bill = ₹" + billAmount);

        sc.close();
    }
}
