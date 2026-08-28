public class Bike{
    private String brand;
    private int speed;
    private int gear = 1;
    private final int [] GEAR_SPEEDS_LIMITS = {5, 10, 25, 30, 40, 60};
    public void setBrand(String brandName){
        brand = brandName;
    }
    public void gearChange(int gearValue){
        if (gearValue < 1 || gearValue > 6){
            System.out.println("Invalid gear value. Please select a gear between 1 and 6.");
        }
        else {
            gear = gearValue;
        }
    }
    public int speedAcceleration(int increment) {
        speed += increment;
        if (speed > GEAR_SPEEDS_LIMITS[gear - 1]) {
            speed = GEAR_SPEEDS_LIMITS[gear - 1];
        }
        return speed;
    }
public int speedDeceleration(int decrement) {
        speed -= decrement;
        if (speed < 0) {
            speed = 0;
        }
        return speed;
    }
public void printInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Current Speed: " + speed + " km/h");
        System.out.println("Current Gear: " + gear);
    }
}
