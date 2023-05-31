package my.project;

import java.io.*; // Used in Loading a file, I'm nott 100% sure how that code works.
import java.util.HashMap;
import java.util.Map;

// Parking garage with a specified number of floors and parking spots per floor.
public class ParkingGarage implements Serializable {
    private int numFloors;
    private int numSpotsPerFloor;
    private Map<String, Car> parkedCars;

    public ParkingGarage(int numFloors, int numSpotsPerFloor) {
        this.numFloors = numFloors;
        this.numSpotsPerFloor = numSpotsPerFloor;
        this.parkedCars = new HashMap<>();
        initializeParkingSpots();
    }

    // Sets all parking spots as empty.
    private void initializeParkingSpots() {
        for (int floor = 1; floor <= numFloors; floor++) {
            for (int spot = 1; spot <= numSpotsPerFloor; spot++) {
                String parkingSpot = String.format("%d-%d", floor, spot);
                parkedCars.put(parkingSpot, null);
            }
        }
    }

    //  Parks a car in the garage using registration number.
    public boolean parkCar(Car car) {
        String parkingSpot = findAvailableSpot();
        if (parkingSpot == null) {
            System.out.println("Sorry, the parking garage is full.");
            return false;
        }
        parkedCars.put(parkingSpot, car);
        System.out.printf("Car with registration number %s is parked at spot %s.\n", car.getRegistrationNumber(),
                parkingSpot);
        return true;
    }

        // Find the first available spot in the garage.
    private String findAvailableSpot() {
        for (int floor = 1; floor <= numFloors; floor++) {
            for (int spot = 1; spot <= numSpotsPerFloor; spot++) {
                String parkingSpot = String.format("%d-%d", floor, spot);
                if (parkedCars.get(parkingSpot) == null) {
                    return parkingSpot;
                }
            }
        }
        return null; // No available spot found = garage full.
    }

    // Unparks a car with the specified registration number.
    public boolean unparkCar(String regNum) {
        for (String parkingSpot : parkedCars.keySet()) {
            Car car = parkedCars.get(parkingSpot);
            if (car != null && car.getRegistrationNumber().equals(regNum)) {
                parkedCars.put(parkingSpot, null);
                System.out.printf("Car with registration number %s has been checked out from spot %s.\n", regNum,
                        parkingSpot);
                return true;
            }
        }
        System.out.printf("Car with registration number %s is not parked in the garage.\n", regNum);
        return false;
    }

    // Saves parked cars to file.
    public boolean saveToFile(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(parkedCars);
            out.close();
            fileOut.close();
            System.out.printf("Parked cars have been saved to %s.\n", filename);
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    // Loads parked cars from file.
    public boolean loadFromFile(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            parkedCars = (HashMap<String, Car>) in.readObject(); // unchecked cast, I don't really know what this means but it does work.
            in.close();
            fileIn.close();
            System.out.printf("Parked cars have been loaded from %s.\n", filename);
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("Class Car not found.");
            c.printStackTrace();
            return false;
        }
    }

        //Lists all parked cars and their registration number.
    public void listParkedCars() {
        if (parkedCars.isEmpty()) {
            System.out.println("No cars parked in the garage.");
        } else {
            System.out.println("Currently parked cars:");
            for (String parkingSpot : parkedCars.keySet()) {
                Car car = parkedCars.get(parkingSpot);
                if (car != null) {
                    System.out.printf("Spot %s: %s\n", parkingSpot, car.getRegistrationNumber());
                }
            }
        }
    }

        // Lists all available parking spots and also show the floors.
    public void displayAvailableSpots() {
        System.out.println("Available Parking Spots:");
        for (int floor = 1; floor <= numFloors; floor++) {
            System.out.print("Floor " + floor + ": ");
            for (int spot = 1; spot <= numSpotsPerFloor; spot++) {
                String parkingSpot = String.format("%d-%d", floor, spot);
                if (parkedCars.get(parkingSpot) == null) {
                    System.out.print(parkingSpot + " ");
                }
            }
            System.out.println();
        }
    }
}