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
     * Registration number.
     * 
     * @return String
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    
    /** 
     * Gets Parking location.
     * 
     * @return String
     */
    public String getParkingLocation() {
        return parkingLocation;
    }

    
    /** 
     * Shows parking location.
     * @param parkingLocation
     */
    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }
}
