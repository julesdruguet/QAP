import java.io.File;
import java.util.*;

public class QAP {

    private int[][] distanceMatrix;
    private int[][] weightMatrix;
    private int[] positions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private TaillardParser taillardParser;

    public QAP(String fileName) {
        this.taillardParser = new TaillardParser(new File("taillard/" + fileName));
        taillardParser.parseSize();
        weightMatrix = taillardParser.parseMatrix();
        distanceMatrix = taillardParser.parseMatrix();
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

    public int calculateFitness(ArrayList<Integer> positions) {
        int fitness = 0;
        for (int i = 0; i < this.taillardParser.getSize(); i++) {
            for (int j = i + 1; j < this.taillardParser.getSize(); j++) {
                fitness += weightMatrix[i][j] * distanceMatrix[positions.get(i)][positions.get(j)];
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
        for (int i = 0; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                fitnessX = calculateFitness(this.positions);
                positionsTemp = permuteRandom();
                delta = calculateFitness(positionsTemp) - fitnessX;
                if (delta <= 0) {
                    this.positions = positionsTemp;
                    if (fitnessX < fitnessMin) {
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
        int fitnessX;
        int fitness;
        int delta;
        int[] permutation = new int[2];

        for (int i = 0; i < maxIter; i++) {
            fitnessX = Integer.MAX_VALUE;
            neighboorhood = this.getNeighborhood(positionsX);
            // on retire les élements de la liste Tabou du voisinage
            for (int j = 0; j < tabouList.size(); j++) {
                if (tabouList.get(j) != null) {
                    neighboorhood[tabouList.get(j)[0]][tabouList.get(j)[1]] = null;
                }
            }

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
            }
            positionsX = positionsTemp.clone();

        }
        return positionsMin;
    }

    public QAPSolution tabuSearchBis(int tabuSize, int maxIter) {
        QAPSolution bestSolution = new QAPSolution(this.taillardParser.getSize());
        int bestFitness = calculateFitness(bestSolution);
        int candidateFitness;
        ArrayList<Permutation> tabuList = new ArrayList<>();
        ArrayList<Map.Entry<Permutation, QAPSolution>> candidateList = new ArrayList<>();
        Map.Entry<Permutation, QAPSolution> candidateSolution;

        int iterations = 0;
        while (iterations < maxIter) {
            candidateList.clear();

            for (Map.Entry<Permutation, QAPSolution> candidate : this.getNeighborhood(bestSolution).entrySet()) {
                if (!tabuList.contains(candidate.getKey())) {
                    candidateList.add(candidate);
                }
            }

            candidateSolution = getBestCandidate(candidateList);

            if ((candidateFitness = calculateFitness(candidateSolution.getValue())) < bestFitness) {
                bestFitness = candidateFitness;
                bestSolution = candidateSolution.getValue();
                tabuList.add(candidateSolution.getKey());
                while (tabuList.size() > tabuSize) {
                    //System.out.println("Liste tabou pleine, on retire la permutation suivante :");
                    //System.out.println(tabuList.get(0));
                    tabuList.remove(0);
                }
            }
            iterations++;
        }
/*        System.out.println("Liste tabou finale :");
        System.out.println(Arrays.toString(tabuList.toArray()));
*/

        return bestSolution;
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

    public int[][][] getNeighborhood(int[] positionsX) {
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

    public HashMap<Permutation, QAPSolution> getNeighborhood(QAPSolution currentSolution) {
        int solutionSize = currentSolution.size();
        HashMap<Permutation, QAPSolution> neighbourhood = new HashMap<>();
        QAPSolution neighbour;
        for (int i = 0; i < solutionSize; i++) {
            for (int j = i + 1; j < solutionSize; j++) {
                if (distanceMatrix[i][j] < 20) {
                    neighbour = (QAPSolution) currentSolution.clone();
                    Collections.swap(neighbour, i, j);
                    neighbourhood.put(new Permutation(i, j), neighbour);
                }
            }
        }
        return neighbourhood;
    }

    public Map.Entry<Permutation, QAPSolution> getBestCandidate(ArrayList<Map.Entry<Permutation, QAPSolution>> candidateList) {
        Map.Entry<Permutation, QAPSolution> bestCandidate = null;
        int bestFitness = Integer.MAX_VALUE;
        int candidateFitness;

        for (Map.Entry<Permutation, QAPSolution> candidate : candidateList) {
            if ((candidateFitness = calculateFitness(candidate.getValue())) < bestFitness) {
                bestFitness = candidateFitness;
                bestCandidate = candidate;
            }
        }

        return bestCandidate;
    }


}
