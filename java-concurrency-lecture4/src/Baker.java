import java.util.Random;

public class Baker extends Thread {
    private static final int BREADS_TO_BAKE = 100;
    private static final int MAX_MILIS_TO_WAIT = 1000;

    private BagDepo bagDepo;
    private Random random;

    public Baker(BagDepo bagDepo) {
        this.bagDepo = bagDepo;
        random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < BREADS_TO_BAKE; i++) {
            try {
                FlourBag bag = bagDepo.get();
                Thread.sleep(random.nextInt(MAX_MILIS_TO_WAIT));
                System.out.println(getName() + " baked a bread");
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
