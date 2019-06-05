import java.util.HashSet;

/**
 * Liste de 0 et 1
 * 0 : pas de permutation à cet indice
 * 1 : permutation à cet indice
 */
public class Permutation extends HashSet<Integer> {
    public Permutation(int index1, int index2) {
        this.add(index1);
        this.add(index2);
    }
}
