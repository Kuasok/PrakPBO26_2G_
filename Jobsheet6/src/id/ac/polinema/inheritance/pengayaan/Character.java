package id.ac.polinema.inheritance.pengayaan;

public class Character {
    protected String nama;
    protected int level;
    protected int health;

    public Character(String nama, int level, int health) {
        this.nama = nama;
        this.level = level;
        this.health = health;
    }

    public void attack(Character target) {
        target.health -= 10;
    }

    public void showStatus() {
        System.out.println("Nama   : " + nama);
        System.out.println("Level  : " + level);
        System.out.println("Health : " + health);
    }
}
