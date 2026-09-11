package id.ac.polinema;

public class EncapTestDemo {
    public static void main(String[] args) {
        EncapTest encap = new EncapTest();

        encap.setName("Ricky");
        encap.setId("1234");
        encap.setAge(35);

        System.out.println("Name: " + encap.getName());
        System.out.println("Id  : " + encap.getId());
        System.out.println("Age : " + encap.getAge());
    }
}
