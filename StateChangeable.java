/* File: StateChangeable.java
 * Author: Kevin Abrahams
 * Date: 31-03-2020
 * Purpose: Create an interface which the Property class shall implement.
            Serve as foundation upon which to implement methods.
 */

public interface StateChangeable <E extends Enum<Status>> {
    // method that shall be implemented by the Property class
    abstract public void changeState(E status);
}
