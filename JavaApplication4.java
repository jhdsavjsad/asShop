import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Equipment {
    private int id;
    private String name;
    private double dailyRate;

    public Equipment(int id, String name, double dailyRate) {
        this.id = id;
        this.name = name;
        this.dailyRate = dailyRate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDailyRate() {
        return dailyRate;
    }
}

class Customer {
    private int id;
    private String name;
    private String phoneNumber;

    public Customer(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class RentalAgreement {
    private int id;
    private Equipment equipment;
    private Customer customer;
    private int daysRented;

    public RentalAgreement(int id, Equipment equipment, Customer customer, int daysRented) {
        this.id = id;
        this.equipment = equipment;
        this.customer = customer;
        this.daysRented = daysRented;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDaysRented() {
        return daysRented;
    }
}

public class JavaApplication4 {
    private List<Equipment> equipmentList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();
    private int agreementIdCounter = 1;

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void rentEquipment(Customer customer, Equipment equipment, int days) {
        // Check if equipment and customer exist
        if (!equipmentList.contains(equipment) || !customerList.contains(customer)) {
            System.out.println("Equipment or customer not found.");
            return;
        }

        int agreementId = agreementIdCounter++;
        RentalAgreement rentalAgreement = new RentalAgreement(agreementId, equipment, customer, days);
        rentalAgreements.add(rentalAgreement);

        System.out.println("Rental agreement created with ID: " + agreementId);
    }

    public void listAvailableEquipment() {
        System.out.println("Available Equipment:");
        for (Equipment equipment : equipmentList) {
            System.out.println("ID: " + equipment.getId() + ", Name: " + equipment.getName() + ", Daily Rate: $" + equipment.getDailyRate());
        }
    }

    public void listCustomers() {
        System.out.println("Customers:");
        for (Customer customer : customerList) {
            System.out.println("ID: " + customer.getId() + ", Name: " + customer.getName() + ", Phone: " + customer.getPhoneNumber());
        }
    }

    public void listRentalAgreements() {
        System.out.println("Rental Agreements:");
        for (RentalAgreement agreement : rentalAgreements) {
            System.out.println("ID: " + agreement.getId() +
                    ", Equipment: " + agreement.getEquipment().getName() +
                    ", Customer: " + agreement.getCustomer().getName() +
                    ", Days Rented: " + agreement.getDaysRented() +
                    ", Total Cost: $" + (agreement.getDaysRented() * agreement.getEquipment().getDailyRate()));
        }
    }

    public static void main(String[] args) {
        JavaApplication4 shop = new JavaApplication4();

        Equipment equipment1 = new Equipment(1, "Snowboard", 25.0);
        Equipment equipment2 = new Equipment(2, "Ski Set", 30.0);

        Customer customer1 = new Customer(1, "John Doe", "123-456-7890");
        Customer customer2 = new Customer(2, "Jane Smith", "987-654-3210");

        shop.addEquipment(equipment1);
        shop.addEquipment(equipment2);

        shop.addCustomer(customer1);
        shop.addCustomer(customer2);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSnow Equipment Hire Shop Menu:");
            System.out.println("1. Rent Equipment");
            System.out.println("2. List Available Equipment");
            System.out.println("3. List Customers");
            System.out.println("4. List Rental Agreements");
            System.out.println("5. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter customer ID:");
                    int customerId = scanner.nextInt();
                    System.out.println("Enter equipment ID:");
                    int equipmentId = scanner.nextInt();
                    System.out.println("Enter number of days to rent:");
                    int days = scanner.nextInt();

                    Customer rentCustomer = shop.customerList.stream().filter(c -> c.getId() == customerId).findFirst().orElse(null);
                    Equipment rentEquipment = shop.equipmentList.stream().filter(e -> e.getId() == equipmentId).findFirst().orElse(null);

                    if (rentCustomer != null && rentEquipment != null) {
                        shop.rentEquipment(rentCustomer, rentEquipment, days);
                    } else {
                        System.out.println("Invalid customer or equipment ID.");
                    }
                    break;

                case 2:
                    shop.listAvailableEquipment();
                    break;

                case 3:
                    shop.listCustomers();
                    break;

                case 4:
                    shop.listRentalAgreements();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Exiting Snow Equipment Hire Shop.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
