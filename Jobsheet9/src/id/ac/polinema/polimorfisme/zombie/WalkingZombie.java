package id.ac.polinema.polimorfisme.zombie;

public class WalkingZombie extends Zombie {
    public WalkingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        int percentage = switch (level) {
            case 1 -> 10;
            case 2 -> 30;
            case 3 -> 40;
            default -> 0;
        };
        health = Math.min(100, health + health * percentage / 100);
    }

    @Override
    public void destroyed() {
        health -= health * 20 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Walking Zombie Data = \n" + super.getZombieInfo();
    }
}
