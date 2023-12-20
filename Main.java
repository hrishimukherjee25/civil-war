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
        System.out.print("There are 2 paths currently, which one would you like to choose: (1 / 2) ");

        String path = new Scanner(System.in)
                .nextLine();

        System.out.println("Congrats! You chose path " + path);

        switch(path) {
            case "1":
                System.out.println("Path 1:");
                EarthtoMoon.main();
                MoontoMars.main();
                // Political Divide // Vote // Supreme Lead // Destroy // Veto-Tree
                System.out.println("Explorer's Dilemma - Should we go further on to Europa? (y / n / z) ");
                String doWeGoToEuropa = new Scanner(System.in).nextLine();
                if (doWeGoToEuropa.equals("y")) {
                    MarstoEuropa.main();
                } else if (doWeGoToEuropa.equals("n")) {
                    MarstoEarth.main();
                } else if (doWeGoToEuropa.equals("z")) {
                    MarstoEarth.main();
                } else {
                    System.out.println("An answer to that selection choice does not exist at the moment.");
                }
                break;

            case "2":
                System.out.println("Path 2:");
                // A series of concatenated events break out on Earth
                CivilWar.main();
                break;

            case "supreme":
                // the best option, strong interplanetary transport network
                travelToMarsAndBack();
                break;
        }
    }

    public static void travelToMarsAndBack() {
        // Do we want to travel again? Solidifying the triangulate network.
        System.out.println("Would you like to travel to Mars passing by the Moon then return back to Earth?");
        Scanner obj = new Scanner(System.in);
        String answer = null;

        do {
            System.out.println("(y / n)");
            answer = obj.nextLine();

            if (answer.equals("y")) {
                System.out.println("Executing Earth Moon Mars Triangulate Sequence!");
                System.out.println("Safe Travels!");

                EarthtoMarsTriangulate.main();

                System.out.println("\nYou have completed the Earth Moon Mars Triangulate Sequence.");
            } else {
                System.out.println("You have chosen to stay on Earth.");
            }
        } while(answer.equals("y"));
    }
}