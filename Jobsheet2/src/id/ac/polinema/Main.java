package id.ac.polinema;

public class Main {
    public static void main(String[] args) {
        Rectangle[] shapes = {
            new Rectangle(6, 4),
            new Rectangle(5, 3),
            new Rectangle(8, 2)
        };

        for (Rectangle shape : shapes) {
            System.out.println("Area: " + shape.area());
            System.out.println("Perimeter: " + shape.perimeter());
        }

        Student student = new Student("Nadia", "S001", 3.8);
        System.out.println(student);

        Circle circle = new Circle(5);
        System.out.println("Circle Area: " + circle.area());
        System.out.println("Circle Circumference: " + circle.circumference());
    }
}
