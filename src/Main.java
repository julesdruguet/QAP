import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        QAP qap = new QAP();
        QAPSolution positionsFinal;
        /* = qap.simulatedAnnealing(100,0.99);
        for (int i = 0; i < positionsFinal.length; i++) {
            System.out.print(positionsFinal[i] + " ; ");
        }
        System.out.println("Recuit simulé - Fitness finale : " + qap.calculateFitness(positionsFinal));
        */
        positionsFinal = qap.tabuSearchBis(10, 500);

        System.out.println();
        System.out.println("Positions finales : ");
        for (int i = 0; i < positionsFinal.size(); i++) {
            System.out.print(positionsFinal.get(i) + " ; ");
        }
        System.out.println("\nTabou - Fitness finale : " + qap.calculateFitness(positionsFinal));
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("\nDurée d'exécution : " + durationMs + "ms");
        } else {
            Date d = new Date((durationMs / 1000) * 1000L);
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            String time = df.format(d);
            System.out.println("Durée d'exécution : " + time);
        }
    }
}
