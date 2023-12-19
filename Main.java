import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Civil War!");
        System.out.print("There are 2 paths currently, which one would you like to choose: ");

        Scanner myObj = new Scanner(System.in);
        String path = myObj.nextLine();

        System.out.println("Congrats! You chose path " + path);

        switch(path) {
            case "1":
                System.out.println("Path 1:");
                EarthtoMoon.main();
                MoontoMars.main();
                // Political Divide // Vote // Supreme Lead // Destroy
                System.out.println("We've come this far.. should we go to Europa? (y / n) ");
                Scanner whatHappensNow = new Scanner(System.in);
                String doWeGoToEuropa = whatHappensNow.nextLine();
                if(doWeGoToEuropa.equals("y")) {
                    MarstoEuropa.main();
                } else if(doWeGoToEuropa.equals("n")) {
                    System.out.println("The group did not choose to go to Europa..");
                    System.out.println("We're not here to stay.."); // Pre-decided
                    MarstoEarth.main();
                    System.out.println("Comms Collected: 1 Will He Dare 2 He Did It");
                    System.out.println("q. Who are these people? (K)");
                    System.out.println("""
                            We already know what's gonn(woah)a happen Hrishi
                            '(ins)0' you wanna play
                            '(ins)1'>like real[exxa--]ly(' we know) this is crazy (4R) good
                            '(ins)2' it's crazy \\/\\/ [can you really, yes! slight(!) affirmation 'woah bro' 'fuck']""");
                    // insert garbage collector lol
                } else if(doWeGoToEuropa.equals("z")) {
                    System.out.println("An undertone overtone of zombus zombie arises.. Ouuu:TW");
                    System.out.println("We're not here to stay!"); // Presidency Campaign
                    System.out.println("Benedict Cumberbatch is saying very good (Hahahaha what..? (K))");
                    MarstoEarth.main();
                } else {
                    System.out.println("An answer to that selection choice does not exist at the moment.");
                }
                break;

            case "2":
                System.out.println("Path 2:");
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
                break;
        }
    }
}