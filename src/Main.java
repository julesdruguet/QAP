import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long startTime;
        long endTime;
        long durationMs;

        ////////// PARTIE RECUIT //////////
        int[] positionsFinal;

        System.out.println("//////// RECUIT SIMULE ////////\n");
        //// Taillard 12 - Etude 1 ////
        startTime = System.nanoTime();
        QAP qap1 = new QAP("tai12a.dat");
        positionsFinal = qap1.simulatedAnnealing(100,0.99);
        System.out.println("Taillard 12 - Etude 1\nFitness finale : " + qap1.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap1 = null; // on libère l'adresse pour le garbage collector

        //// Taillard 12 - Etude 2 ////
        startTime = System.nanoTime();
        QAP qap2 = new QAP("tai12a.dat");
        positionsFinal = qap2.simulatedAnnealing(60,0.75);
        System.out.println("Taillard 12 - Etude 2\nFitness finale : " + qap2.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap2 = null;

        //// Taillard 20 - Etude 1 ////
        startTime = System.nanoTime();
        QAP qap3 = new QAP("tai20a.dat");
        positionsFinal = qap3.simulatedAnnealing(100,0.99);
        System.out.println("Taillard 20 - Etude 1\nFitness finale : " + qap3.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap3 = null;

        //// Taillard 20 - Etude 2 ////
        startTime = System.nanoTime();
        QAP qap4 = new QAP("tai20a.dat");
        positionsFinal = qap4.simulatedAnnealing(60,0.75);
        System.out.println("Taillard 20 - Etude 2\nFitness finale : " + qap4.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap4 = null;

        //// Taillard 30 - Etude 1 ////
        startTime = System.nanoTime();
        QAP qap5 = new QAP("tai30a.dat");
        positionsFinal = qap5.simulatedAnnealing(100,0.95);
        System.out.println("Taillard 30 - Etude 1\nFitness finale : " + qap5.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap5 = null;

        //// Taillard 40 - Etude 1 ////
        startTime = System.nanoTime();
        QAP qap6 = new QAP("tai40a.dat");
        positionsFinal = qap6.simulatedAnnealing(100,0.95);
        System.out.println("Taillard 40 - Etude 1\nFitness finale : " + qap6.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap6 = null;

        //// Taillard 60 - Etude 1 ////
        startTime = System.nanoTime();
        QAP qap7 = new QAP("tai60a.dat");
        positionsFinal = qap7.simulatedAnnealing(100,0.95);
        System.out.println("Taillard 60 - Etude 1\nFitness finale : " + qap7.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap7 = null;

        //// Taillard 80 - Etude 1 ////
        startTime = System.nanoTime();
        QAP qap8 = new QAP("tai80a.dat");
        positionsFinal = qap8.simulatedAnnealing(100,0.95);
        System.out.println("Taillard 80 - Etude 1\nFitness finale : " + qap8.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            String time = msToTime(durationMs);
            System.out.println("Durée d'exécution : " + time);
        }
        qap8 = null;

        ////////// PARTIE TABOU //////////
        /*
        startTime = System.nanoTime();
        QAPSolution positionsFinal = qap.tabuSearchBis(10, 500);

        System.out.println();
        System.out.println("Positions finales : ");
        for (int i = 0; i < positionsFinal.size(); i++) {
            System.out.print(positionsFinal.get(i) + " ; ");
        }
        System.out.println("\nTabou - Fitness finale : " + qap.calculateFitness(positionsFinal));
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1000000;
        if (durationMs < 1000) {
            System.out.println("Durée d'exécution : " + durationMs + "ms");
        } else {
            Date d = new Date((durationMs / 1000) * 1000L);
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            String time = df.format(d);
            System.out.println("Durée d'exécution : " + time);
        }
        */

    }

    public static String msToTime(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        return hms;
    }
}
