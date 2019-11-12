import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ForesterMain {
    public static void main(String[] args) {
        final String FILE_NAME = "forest.txt";
        File file = new File(FILE_NAME);
        char[] forest = new char[(int) file.length()];

        try {
            FileReader fr = new FileReader(file);
            fr.read(forest);
            Counter m1 = new Counter(forest);
            m1.info();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Counter {
    private char[] input;

    public Counter(char[] input) {
        this.input = input;
    }

    public void info() {
        String str = new String(input);
        String[] tmp = str.split("\r\n |\r\n| ");
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < tmp.length; i++) {
            Integer current = hm.get(Integer.valueOf(tmp[i]));
            hm.put(Integer.valueOf(tmp[i]), current == null ? 1 : current + 1);
        }

        System.out.println("Лесник обошёл лес и подсчитал все деревья:");
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            System.out.printf("\t%s (%d) - %d\r\n", getTreeName(entry.getKey()), entry.getKey(), entry.getValue());
        }
    }

    private String getTreeName(int i) {
        String name = null;
        switch (i) {
            case 1 : name = "Ёлка"; break;
            case 2 : name = "Берёза"; break;
            case 3 : name = "Лиственница"; break;
            case 4 : name = "Сосна"; break;
            case 5 : name = "Осина"; break;
            default: name = "Какой-то кустик";
        }
        return name;
    }
}