package id.ac.polinema.polimorfisme.zombie;

public class MainTugas2 {
    public static void main(String[] args) {
        Destroyable[] targets = {
                new WalkingZombie(100, 1),
                new JumpingZombie(100, 2),
                new Barrier(100)
        };
        Plant plant = new Plant();

        for (int i = 0; i < 4; i++) {            // hancurkan semua elemen 4 kali
            for (Destroyable target : targets) {
                plant.doDestroy(target);
            }
        }

        for (Destroyable target : targets) {
            if (target instanceof Zombie) {
                Zombie z = (Zombie) target;
                z.heal();
                System.out.println(z.getZombieInfo());
            } else if (target instanceof Barrier) {
                Barrier b = (Barrier) target;
                System.out.println(b.getBarrierInfo());
            }
        }
    }
}
