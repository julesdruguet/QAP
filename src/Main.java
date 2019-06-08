public class Main {
    public static void main(String[] args) {
        QAP qap = new QAP();
        QAPSolution positionsFinal;
        /* = qap.simulatedAnnealing(100,0.99);
        for (int i = 0; i < positionsFinal.length; i++) {
            System.out.print(positionsFinal[i] + " ; ");
        }
        System.out.println("Recuit simulé - Fitness finale : " + qap.calculateFitness(positionsFinal));
        */
        positionsFinal = qap.tabuSearchBis(10, 50000000);

        System.out.println();
        System.out.println("Positions finales : ");
        for (int i = 0; i < positionsFinal.size(); i++) {
            System.out.print(positionsFinal.get(i) + " ; ");
        }
        System.out.println("\nTabou - Fitness finale : " + qap.calculateFitness(positionsFinal));
    }
}
