import java.util.HashSet;
import java.util.Iterator;

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

    @Override
    public String toString() {
        Iterator<Integer> i = this.iterator();
        StringBuilder str
                = new StringBuilder();
        str.append("Permutation{");
        String prefix = "";
        while (i.hasNext()) {
            str.append(prefix);
            prefix = ", ";
            str.append(i.next());
        }

        str.append("}");

        return str.toString();
    }
}
