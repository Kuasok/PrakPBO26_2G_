package id.ac.polinema.polimorfisme.zombie;

public class JumpingZombie extends Zombie {
    public JumpingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        int percentage = switch (level) {
            case 1 -> 30;
            case 2 -> 40;
            case 3 -> 50;
            default -> 0;
        };
        health = Math.min(100, health + health * percentage / 100);
    }

    @Override
    public void destroyed() {
        health -= health * 10 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Jumping Zombie Data = \n" + super.getZombieInfo();
    }
}
