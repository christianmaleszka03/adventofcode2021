import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(challenge2(fileToArray()));
    }

    public static int challenge1(String[] input) {
        int counter = 0;
        int previous = Integer.MAX_VALUE;
        for(String cur : input) {
            System.out.println(cur);
            if(!cur.equals("")) {
                int currentInt = Integer.parseInt(cur);
                if(currentInt > previous) { counter++; }
                previous = currentInt;
            }
        }
        return counter;
    }

    public static int challenge2(String[] input) {
        int counter = 0;
        int previousSum = Integer.MAX_VALUE;
        for(int i = 0; i < input.length-2; i++) {
            if(!input[i].equals("")) {
                int first = Integer.parseInt(input[i]);
                int second = Integer.parseInt(input[i+1]);
                int third = Integer.parseInt(input[i+2]);

                if((first + second + third) > previousSum) { counter++; }
                previousSum = first + second + third;
            }
        }
        return counter;
    }

    public static String[] fileToArray() {
        try {
            File input = new File("/Users/christianmaleszka/Documents/02 - Development/AOC2021/AOC202101/src/input.txt");
            Scanner scanner = new Scanner(input);
            String inputAsString = "";
            while(scanner.hasNextLine()) {
                String current = scanner.nextLine();
                inputAsString = inputAsString + ":" + current;
            }
            return inputAsString.split(":");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}
