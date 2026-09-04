package id.ac.polinema;

public class Student {
    private String name;
    private String studentId;
    private double gpa;

    public Student(String name, String studentId, double gpa) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + " (" + studentId + ", GPA: " + gpa + ")";
    }
}
