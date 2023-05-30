package my.project;

public class Main {
    public static void main(String[] args) {
        ParkingGarage garage = new ParkingGarage(5, 4);
        Car car1 = new Car("ABC123");
        Car car2 = new Car("DEF456");
        Car car3 = new Car("GHI789");
        garage.parkCar(car1);
        garage.parkCar(car2);
        garage.parkCar(car3);
        garage.listParkedCars();
        garage.unparkCar("DEF456");
        garage.listParkedCars();
        garage.saveToFile("parked_cars.ser");
        garage = new ParkingGarage(5, 4);
        garage.loadFromFile("parked_cars.ser");
        garage.listParkedCars();
        garage.displayAvailableSpots();
    }
}
