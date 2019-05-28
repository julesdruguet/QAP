public class Main {
    public static void main(String[] args) {
        QAP qap = new QAP();
        int[] positionsFinal; /* = qap.simulatedAnnealing(100,0.99);
        for (int i = 0; i < positionsFinal.length; i++) {
            System.out.print(positionsFinal[i] + " ; ");
        }
        System.out.println("Recuit simulé - Fitness finale : " + qap.calculateFitness(positionsFinal));
*/
        positionsFinal = qap.tabouSearch(30, 10000);

        for (int i = 0; i < positionsFinal.length; i++) {
            System.out.print(positionsFinal[i] + " ; ");
        }
        System.out.println("Tabou - Fitness finale : " + qap.calculateFitness(positionsFinal));
    }
}
