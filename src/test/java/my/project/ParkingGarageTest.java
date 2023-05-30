package my.project;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;     


public class ParkingGarageTest {
    private ParkingGarage garage;
    private Car car1;
    private Car car2;
    private Car car3;

    @BeforeEach
    public void setUp() {
        garage = new ParkingGarage(5, 4);
        car1 = new Car("AB123");
        car2 = new Car("CD456");
        car3 = new Car("EF789");
    }

    @Test
    public void testParkCar() {
        ParkingGarage parkingGarage = new ParkingGarage(3, 4);
        Car car = new Car("ABC123");
        assertTrue(parkingGarage.parkCar(car));
    }
    
    @Test
    public void testParkCar_GarageFull() {
        ParkingGarage parkingGarage = new ParkingGarage(1, 4);
        Car car1 = new Car("ABC123");
        Car car2 = new Car("XYZ789");
        parkingGarage.parkCar(car1);
        assertFalse(parkingGarage.parkCar(car2));
    }
    

    @Test
    public void testUnparkCar() {
        ParkingGarage parkingGarage = new ParkingGarage(3, 4);
        Car car = new Car("ABC123");
        parkingGarage.parkCar(car);
        assertTrue(parkingGarage.unparkCar("ABC123"));
    }
    

    @Test
    public void testUnparkCar_InvalidRegistrationNumber() {
        ParkingGarage parkingGarage = new ParkingGarage(3, 4);
        Car car = new Car("ABC123");
        parkingGarage.parkCar(car);
        assertFalse(parkingGarage.unparkCar("XYZ789"));
    }
    

    @Test
    public void testListParkedCars_EmptyGarage() {
        ParkingGarage parkingGarage = new ParkingGarage(3, 4);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parkingGarage.listParkedCars();
        assertEquals("No cars parked in the garage.\n", outputStream.toString());
    }
    

    @Test
    public void testListParkedCars() {
        ParkingGarage parkingGarage = new ParkingGarage(3, 4);
        Car car1 = new Car("ABC123");
        Car car2 = new Car("XYZ789");
        parkingGarage.parkCar(car1);
        parkingGarage.parkCar(car2);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parkingGarage.listParkedCars();
        String expectedOutput = "Currently parked cars:\n" +
                                "Spot 1-1: ABC123\n" +
                                "Spot 1-2: XYZ789\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
    

    @Test
    public void testSaveToFile() {
        ParkingGarage parkingGarage = new ParkingGarage(3, 4);
        Car car = new Car("ABC123");
        parkingGarage.parkCar(car);
        assertTrue(parkingGarage.saveToFile("parking_data.ser"));
    }
}
