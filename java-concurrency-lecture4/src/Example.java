import java.util.HashSet;
import java.util.Set;

public class Example {

    public static void main(String[] args) {
        BagDepo bagDepo = new BagDepo(30);

        Set<Miller> millers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Miller miller = new Miller(bagDepo);
            miller.start();
            millers.add(miller);
        }

        Set<Baker> bakers = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Baker baker = new Baker(bagDepo);
            baker.start();
            bakers.add(baker);
        }

        try {
            for (Miller miller : millers) {
                miller.join();
            }
            for (Baker baker : bakers) {
                baker.join();
            }
        } catch (InterruptedException e) {

        }
    }

}
