package id.ac.polinema.inheritance.pengayaan;

public class Human extends Character {
    private int strength;

    public Human(String nama, int level, int health, int strength) {
        super(nama, level, health);
        this.strength = strength;
    }

    public void specialAttack(Character target) {
        target.health -= (10 + strength);
    }
}
