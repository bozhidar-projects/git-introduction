import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BagDepo {
    private FlourBag[] bags;
    private int currentFreePosition;
    private Lock lock;
    private Condition depotIsFull;
    private Condition depotIsEmpty;

    public BagDepo(int capacity) {
        bags = new FlourBag[capacity];
        currentFreePosition = 0;

        lock = new ReentrantLock();
        depotIsFull = lock.newCondition();
        depotIsEmpty = lock.newCondition();
    }

    public void put(FlourBag bag) {
        lock.lock();
        try {
            while (currentFreePosition == bags.length) {
                System.out.println("Depot is full - blocking");
                depotIsFull.await();
            }
            bags[currentFreePosition] = bag;
            currentFreePosition++;

            depotIsEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public FlourBag get() {
        lock.lock();
        FlourBag result = null;
        try {
            while (currentFreePosition == 0) {
                System.out.println("Depot is empting - blocking");
                depotIsEmpty.await();
            }
            currentFreePosition--;
            result = bags[currentFreePosition];

            depotIsFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return result;
    }
}
