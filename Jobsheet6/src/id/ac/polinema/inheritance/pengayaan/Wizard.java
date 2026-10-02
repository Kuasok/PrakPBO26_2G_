package id.ac.polinema.inheritance.pengayaan;

public class Wizard extends Character {
    private int spell;

    public Wizard(String nama, int level, int health, int spell) {
        super(nama, level, health);
        this.spell = spell;
    }

    public void magic(Character target) {
        if (spell > 0) {
            target.health -= 50;
            spell--;
        }
    }
}
