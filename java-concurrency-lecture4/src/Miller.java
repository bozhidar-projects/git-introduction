import java.util.Random;

public class Miller extends Thread {
    private static final int BAGS_TO_GENERATE = 100;
    private static final int MAX_MILIS_TO_WAIT = 1000;

    private BagDepo bagDepo;
    private Random random;

    public Miller(BagDepo bagDepo) {
        this.bagDepo = bagDepo;
        random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < BAGS_TO_GENERATE; i++) {
            FlourBag flourBag = new FlourBag();
            System.out.println(getName() + " tries to put a bag.");
            bagDepo.put(flourBag);
            try {
                Thread.sleep(random.nextInt(MAX_MILIS_TO_WAIT));
            } catch (InterruptedException e) {
                System.out.println("Miller Interrupted");
            }
        }
    }
}
