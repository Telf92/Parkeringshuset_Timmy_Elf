package my.project;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class ParkingGarageTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testParkCar() {
        ParkingGarage garage = new ParkingGarage(5, 4);
        Car car = new Car("ABC123");
        assertTrue(garage.parkCar(car));
    }

    @Test
    void testParkCar_FullGarage() {
        ParkingGarage garage = new ParkingGarage(1, 1);
        Car car1 = new Car("ABC123");
        Car car2 = new Car("DEF456");
        assertTrue(garage.parkCar(car1));
        assertFalse(garage.parkCar(car2));
    }

    @Test
    void testUnparkCar() {
        ParkingGarage garage = new ParkingGarage(5, 4);
        Car car = new Car("ABC123");
        garage.parkCar(car);
        assertTrue(garage.unparkCar("ABC123"));
    }

    @Test
    void testUnparkCar_NotParked() {
        ParkingGarage garage = new ParkingGarage(5, 4);
        assertFalse(garage.unparkCar("ABC123"));
    }

    @Test
    void testSaveToFile() {
        ParkingGarage garage = new ParkingGarage(5, 4);
        Car car = new Car("ABC123");
        garage.parkCar(car);
        assertTrue(garage.saveToFile("parked_cars.ser"));
    }

    @Test
    void testLoadFromFile() {
        ParkingGarage garage = new ParkingGarage(5, 4);
        Car car = new Car("ABC123");
        garage.parkCar(car);
        garage.saveToFile("parked_cars.ser");
        garage = new ParkingGarage(5, 4);
        assertTrue(garage.loadFromFile("parked_cars.ser"));
    }

    @Test
    void testParkCar_GarageFullAfterUnparking() {
        ParkingGarage garage = new ParkingGarage(1, 1);
        Car car1 = new Car("ABC123");
        Car car2 = new Car("DEF456");

        assertTrue(garage.parkCar(car1)); // First car parked successfully
        assertTrue(garage.unparkCar("ABC123")); // Unpark the first car
        assertTrue(garage.parkCar(car2)); // Second car parked successfully in the previously occupied spot
    }

    @Test
    void testGetParkingLocation() {
        String registrationNumber = "ABC123";
        String parkingLocation = "Floor 1, Spot 3";
        Car car = new Car(registrationNumber);
        car.setParkingLocation(parkingLocation);

        assertEquals(parkingLocation, car.getParkingLocation());
    }

}
