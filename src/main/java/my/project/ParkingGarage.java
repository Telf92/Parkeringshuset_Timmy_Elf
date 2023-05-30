package my.project;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ParkingGarage implements Serializable {
    private int numFloors;
    private int numSpotsPerFloor;
    private Map<String, Car> parkedCars; // Map to hold parking spot to car mapping

    public ParkingGarage(int numFloors, int numSpotsPerFloor) {
        this.numFloors = numFloors;
        this.numSpotsPerFloor = numSpotsPerFloor;
        this.parkedCars = new HashMap<>();
        initializeParkingSpots();
    }

    private void initializeParkingSpots() {
        for (int floor = 1; floor <= numFloors; floor++) {
            for (int spot = 1; spot <= numSpotsPerFloor; spot++) {
                String parkingSpot = String.format("%d-%d", floor, spot);
                parkedCars.put(parkingSpot, null); // initialize all spots as empty
            }
        }
    }

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

    private String findAvailableSpot() {
        for (int floor = 1; floor <= numFloors; floor++) {
            for (int spot = 1; spot <= numSpotsPerFloor; spot++) {
                String parkingSpot = String.format("%d-%d", floor, spot);
                if (parkedCars.get(parkingSpot) == null) {
                    return parkingSpot;
                }
            }
        }
        return null; // no available spot found
    }

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

    public boolean loadFromFile(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            parkedCars = (HashMap<String, Car>) in.readObject();
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