package my.project;

import java.util.Scanner;

public class ParkingGarageMenu {
    private static ParkingGarage garage = new ParkingGarage(5, 4);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nWhat do you want to do?:");
            System.out.println("1 - Park a car");
            System.out.println("2 - Unpark a car");
            System.out.println("3 - List parked cars");
            System.out.println("4 - Save parked cars to file");
            System.out.println("5 - Load parked cars from file");
            System.out.println("6 - Show floors and free parking spots");
            System.out.println("0 - Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    parkCar();
                    break;
                case 2:
                    unparkCar();
                    break;
                case 3:
                    listParkedCars();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 5:
                    loadFromFile();
                    break;
                case 6:
                    displayAvailableSpots();
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void parkCar() {
        System.out.println("Enter the car's registration number:");
        String regNum = scanner.nextLine();
        Car car = new Car(regNum);
        garage.parkCar(car);
    }

    private static void unparkCar() {
        System.out.println("Enter the registration number of the car to unpark:");
        String regNum = scanner.nextLine();
        garage.unparkCar(regNum);
    }

    private static void listParkedCars() {
        garage.listParkedCars();
    }

    private static void saveToFile() {
        System.out.println("Enter the name of the file to save the parked cars to:");
        String filename = scanner.nextLine();
        garage.saveToFile(filename);
    }

    private static void loadFromFile() {
        System.out.println("Enter the name of the file to load parked cars from:");
        String filename = scanner.nextLine();
        garage.loadFromFile(filename);
    }

    private static void displayAvailableSpots() {
        System.out.println("Floors and free spots:");
        garage.displayAvailableSpots();
    }
}
