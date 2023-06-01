package my.project;

import java.io.Serializable;

    // Car object with a registration number and parking location.
public class Car implements Serializable {
    private String registrationNumber;
    private String parkingLocation;

    public Car(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    
    /** 
     * @return String
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    
    /** 
     * @return String
     */
    public String getParkingLocation() {
        return parkingLocation;
    }

    
    /** 
     * @param parkingLocation
     */
    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }
}
