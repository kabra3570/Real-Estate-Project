/* File: Property.java
 * Author: Kevin Abrahams
 * Date: 31-03-2020
 * Purpose: Create a Property class to store property information
 */

public class Property implements StateChangeable {
    // private instance variables of class
    private String address;
    private int numOfBedrooms, squareFootage, price;
    private Status status;
    
    // constructor
    public Property(String address, int numOfBedrooms, int squareFootage, int price) {
        // initializing instance variables
        this.address = address;
        this.numOfBedrooms = numOfBedrooms;
        this.squareFootage = squareFootage;
        this.price = price;
        this.status = Status.FOR_SALE;
    }
    
    // changes the state of the property to state of the parameter
    public void changeState(Enum status) {
        // set property's status to the status of the parameter
        if (status.name().equals("FOR_SALE"))
            this.status = Status.FOR_SALE;
        else if (status.name().equals("UNDER_CONTRACT"))
            this.status = Status.UNDER_CONTRACT;
        else
            this.status = Status.SOLD;
    }
    
    // returns string representation of object (info on each private instance variable)
    public String toString() {
        String propertyInfo = "Property Address: " + address + "\nNumber of Bedrooms: " + numOfBedrooms;
        propertyInfo += "\nSquare Footage: " + squareFootage + "\nPrice: " + price + "\nCurrent Status: " + status;
        return propertyInfo;
    }
}
