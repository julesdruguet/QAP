import java.util.ArrayList;
import java.util.Random;

public class QAPSolution extends ArrayList<Integer> {

    public QAPSolution(int size) {
        super();
        Random random = new Random();
        int randomInt;
        for (int i = 0; i < size; i++) {
            randomInt = random.nextInt(size);
            while (this.contains(randomInt)) {
                randomInt = random.nextInt(size);
            }
            this.add(randomInt);
        }
    }

    public QAPSolution() {
        super();
    }
}
