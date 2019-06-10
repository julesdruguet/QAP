import java.io.*;

public class TaillardParser {

    private File file;
    private BufferedReader br;
    private int size;

    public TaillardParser(File file) {
        this.file = file;
        try {
            this.br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int[][] parseMatrix() {
        int[][] matrix = new int[size][size];
        try {
            for (int i = 0; i < size; i++) {
                String line = br.readLine().trim().replaceAll(" +", " ");
                String[] str = line.split(" ");
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = Integer.valueOf(str[j]);
                }
            }
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public int parseSize() {
        try {
            String sizeStr = br.readLine();
            br.readLine();
            size = Integer.parseInt(sizeStr.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
