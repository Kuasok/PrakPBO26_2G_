package id.ac.polinema;

public class MotorDemo {
    public static void main(String[] args) {
        Motor motor = new Motor();

        motor.printStatus();

        motor.tambahKecepatan(50);
        System.out.println();

        motor.nyalakanMesin();
        motor.tambahKecepatan(30);
        motor.tambahKecepatan(20);
        motor.kurangiKecepatan(10);
        System.out.println();

        motor.printStatus();
        motor.matikanMesin();
        motor.printStatus();
    }
}
