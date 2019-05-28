import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class QAP {

    private int[][] distanceMatrix;
    private int[][] weightMatrix;
    //private int[] positions = {7,0,5,1,10,9,2,4,8,6,11,3};
    private int[] positions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private TaillardParser taillardParser;

    public QAP() {
        this.taillardParser = new TaillardParser(new File("taillard/tai12a.dat"));
        taillardParser.parseSize();
        //this.positions = new int[taillardParser.getSize()];
        weightMatrix = taillardParser.parseMatrix();
        distanceMatrix = taillardParser.parseMatrix();

        /*
        for (int i = 0; i < this.taillardParser.getSize(); i++) {
            this.positions[i] = i;
        }
        */
        /*
        System.out.println("Poids : ");
        for (int i = 0; i < this.taillardParser.getSize(); i++) {
            for( int j = 0; j < this.taillardParser.getSize(); j++) {
                System.out.print(weightMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Distances : ");
        for (int i = 0; i < this.taillardParser.getSize(); i++) {
            for( int j = 0; j < this.taillardParser.getSize(); j++) {
                System.out.print(distanceMatrix[i][j] + " ");
            }
            System.out.println();
        }
        */
    }

    public int calculateFitness(int[] positions) {
        int fitness = 0;
        for (int i = 0; i < this.taillardParser.getSize(); i++) {
            for (int j = i + 1; j < this.taillardParser.getSize(); j++) {
                fitness += weightMatrix[i][j] * distanceMatrix[positions[i]][positions[j]];
            }
        }
        return fitness;
    }

    public int[] simulatedAnnealing(double tk, double coolingDown) {
        // voisinage : permutation de 2 positions
        int n1 = 50000;
        int n2 = 400;
        int fitnessX;
        int[] positionsMin = this.positions.clone();
        int fitnessMin = calculateFitness(positionsMin);
        int[] positionsTemp;
        int delta;
        System.out.println("Fitness min initiale : " + fitnessMin);
        for (int i = 0; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                fitnessX = calculateFitness(this.positions);
                positionsTemp = permuteRandom();
                delta = calculateFitness(positionsTemp) - fitnessX;
                if (delta <= 0) {
                    this.positions = positionsTemp;
                    if (fitnessX < fitnessMin) {
                        System.out.println(fitnessX);
                        positionsMin = this.positions.clone();
                        fitnessMin = calculateFitness(positionsMin);
                    }
                } else {
                    double p = Math.random();
                    if (p <= Math.exp((-delta) / tk)) {
                        this.positions = positionsTemp;
                    }
                }
            }
            tk = tk * coolingDown;
        }
        return positionsMin;
    }

    public int[] tabouSearch(int tabouSize, int maxIter) {
        int[] positionsMin = this.positions.clone();
        int[] positionsX = this.positions.clone();
        int fitnessMin = calculateFitness(positionsMin);
        ArrayList<int[]> tabouList = new ArrayList<>();
        int[][][] neighboorhood;
        int[] positionsTemp = this.positions.clone();
        int fitnessX = Integer.MAX_VALUE;
        int fitness;
        int delta;
        int[] permutation = new int[2];

        for (int i = 0; i < maxIter; i++) {
            fitnessX = Integer.MAX_VALUE;
            neighboorhood = this.getNeighboorhood(positionsX);
            // on retire les élements de la liste Tabou du voisinage
            for (int j = 0; j < tabouList.size(); j++) {
                System.out.println(tabouList.get(0)[0] + " " + tabouList.get(0)[1]);
                if (tabouList.get(j) != null) {
                    neighboorhood[tabouList.get(j)[0]][tabouList.get(j)[1]] = null;
                }
            }
            System.out.println("______");

            for (int j = 0; j < this.taillardParser.getSize(); j++) {
                for (int k = 0; k < this.taillardParser.getSize(); k++) {
                    if (neighboorhood[j][k] != null && j != k) {
                        fitness = calculateFitness(neighboorhood[j][k]);
                        if (fitnessX > fitness) {
                            fitnessX = fitness;
                            positionsTemp = neighboorhood[j][k].clone();
                            permutation[0] = k;
                            permutation[1] = j;
                        }
                    }
                }
            }

            delta = fitnessX - calculateFitness(positionsX);

            if (delta >= 0) {
                if (tabouList.size() >= tabouSize) {
                    tabouList.remove(0);
                    tabouList.remove(0);
                }

                tabouList.add(permutation);
                int temp = permutation[0];
                permutation[0] = permutation[1];
                permutation[1] = temp;
                tabouList.add(permutation);

            }

            if (fitnessX < fitnessMin) {
                positionsMin = positionsTemp.clone();
                fitnessMin = fitnessX;
                for (int z = 0; z < positionsX.length; z++) {
                    System.out.print(positionsX[z] + " ; ");
                }
                System.out.println();
            }
            positionsX = positionsTemp.clone();

        }
        return positionsMin;
    }

    public int[] permuteRandom() {
        int[] positionsTemp = positions;
        Random rand = new Random();
        int pos1 = rand.nextInt(taillardParser.getSize());
        int pos2 = rand.nextInt(taillardParser.getSize());
        while (pos1 == pos2) {
            pos2 = rand.nextInt(taillardParser.getSize());
        }
        int temp = positionsTemp[pos2];
        positionsTemp[pos2] = positionsTemp[pos1];
        positionsTemp[pos1] = temp;

        return positionsTemp;
    }

    public int[][][] getNeighboorhood(int[] positionsX) {
        int taillardParserSize = this.taillardParser.getSize();
        int[][][] neighboorhood = new int[taillardParserSize][taillardParserSize][taillardParserSize];
        int temp;
        for (int i = 0; i < taillardParserSize; i++) {
            for (int j = 0; j < taillardParserSize; j++) {
                neighboorhood[i][j] = positionsX.clone();
                temp = neighboorhood[i][j][j];
                neighboorhood[i][j][j] = neighboorhood[i][j][i];
                neighboorhood[i][j][i] = temp;
            }
        }
        return neighboorhood;
    }

    public int[] getPositions() {
        return positions;
    }

}
