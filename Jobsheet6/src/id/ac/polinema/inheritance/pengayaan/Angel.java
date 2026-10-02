package id.ac.polinema.inheritance.pengayaan;

public class Angel extends Character {
    private int potion;

    public Angel(String nama, int level, int health, int potion) {
        super(nama, level, health);
        this.potion = potion;
    }

    public void cure(Character target) {
        if (potion > 0) {
            target.health = 100;
            potion--;
        }
    }
}
