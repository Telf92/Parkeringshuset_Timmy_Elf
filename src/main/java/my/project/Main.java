package my.project;

public class Main {
    public static void main(String[] args) {
        // Creates a new parking garage with 5 floors and 4 spots per floor.
        ParkingGarage garage = new ParkingGarage(5, 4);
        
        // Lists parked cars.
        garage.listParkedCars();
        
        // Unparks a car.
        garage.unparkCar("ABC123");
        
        // Lists all parked cars.
        garage.listParkedCars();
        
        // Saves parked cars to file choosen by the user.
        garage.saveToFile("parked_cars.txt");
        
        // Loads parked cars from file choosen by the user.
        garage.loadFromFile("parked_cars.txt");
        
        // Lists all parked cars.
        garage.listParkedCars();

        // Displays the number of available spots. Also shows floors.
        garage.displayAvailableSpots();
    }
}
