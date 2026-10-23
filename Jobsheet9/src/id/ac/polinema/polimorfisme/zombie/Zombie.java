package id.ac.polinema.polimorfisme.zombie;

public abstract class Zombie implements Destroyable {
    protected int health;
    protected int level;

    public Zombie(int health, int level) {
        this.health = health;
        this.level = level;
    }

    public abstract void heal();

    public String getZombieInfo() {
        return "Health = " + health + "\n"
                + "Level = " + level + "\n";
    }
}
