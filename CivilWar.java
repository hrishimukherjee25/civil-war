import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CivilWar {
    public static void main() {
        File toRead;
        Scanner reader;

        ArrayList<String> allText = new ArrayList<String>();

        System.out.println(allText);

        try {
            toRead = new File("src/text.txt");
            System.out.println("File created.");
            reader = new Scanner(toRead);
            System.out.println("Reader created.");
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                allText.add(data);
                System.out.println("Extracted text.");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }

        for (String text : allText) {
            System.out.print(text);
        }

        ArrayList<String> allWords = new ArrayList<String>();

        System.out.println();
        System.out.println(allWords);

        // O(n^2)
        for (String text : allText) {
            List<String> words = Arrays.asList(text.split(" "));

            for (String word : words) {
                allWords.add(word);
            }
        }

        System.out.println(allWords);

        // Put in distinct HashMap
        HashMap<String, Integer> distinct = new HashMap<String, Integer>();

        System.out.println(distinct);

        for (String word : allWords) {
            System.out.println(distinct.containsKey(word));
            if (distinct.containsKey(word)) {
                int val = distinct.get(word);
                val++;
                distinct.put(word, val);
            } else {
                distinct.put(word, 0);
            }
        }

        //System.out.println(distinct);

        for (Map.Entry<String, Integer> entry : distinct.entrySet()) {
            String word = entry.getKey();
            Integer count = entry.getValue();

            System.out.println(word + " : " + count);
        }

        HashMap<String, HashMap<String, Integer>> cataloguedIndex =
                new HashMap<>();

        for (Map.Entry<String, Integer> entry : distinct.entrySet()) {
            String word = entry.getKey();
            if (word.length() != 0) {
                String firstLetter = Character.toString(word.charAt(0));

                if (!cataloguedIndex.containsKey(firstLetter)) {
                    cataloguedIndex.put(firstLetter, new HashMap<String, Integer>());
                }

                HashMap<String, Integer> wordCount = cataloguedIndex.get(firstLetter);
                Integer count = entry.getValue();

                if (!wordCount.containsKey(word)) {
                    wordCount.put(word, count);
                }
            }
        }

        for (Map.Entry<String, HashMap<String, Integer>> entry : cataloguedIndex.entrySet()) {
            System.out.println();
            System.out.println(entry.getKey() + ": ");

            for (Map.Entry<String, Integer> entryInner : entry.getValue().entrySet()) {
                System.out.println(entryInner.getKey() + " " + entryInner.getValue());
            }
        }

        System.out.println("Fin.");


    }
}
