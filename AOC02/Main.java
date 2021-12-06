import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(challenge2(inputAsArr()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int challenge1(String[] inputArr) throws IllegalArgumentException {
        int horizontalPos = 0, depth = 0;
        for(String currentLine : inputArr) {
            if(!currentLine.isEmpty()) {
                String[] parts = currentLine.split(" ");
                if(parts.length != 2 || !isStringInt(parts[1])) { throw new IllegalArgumentException(); }
                switch (parts[0]) {
                    case "forward" -> horizontalPos += Integer.parseInt(parts[1]);
                    case "up" -> depth -= Integer.parseInt(parts[1]);
                    case "down" -> depth += Integer.parseInt(parts[1]);
                    default -> throw new IllegalArgumentException();
                }
            }
        }
        return depth * horizontalPos;
    }

    public static int challenge2(String[] inputArr) throws IllegalArgumentException {
        int horizontalPos = 0, depth = 0, aim = 0;
        for(String currentLine: inputArr) {
            if(!currentLine.isEmpty()) {
                String[] parts = currentLine.split(" ");
                if(parts.length != 2 || !isStringInt(parts[1])) { throw new IllegalArgumentException(); }
                switch(parts[0]) {
                    case "forward" -> {
                        horizontalPos += Integer.parseInt(parts[1]);
                        depth += aim * Integer.parseInt(parts[1]);
                    }
                    case "up" -> aim -= Integer.parseInt(parts[1]);
                    case "down" -> aim += Integer.parseInt(parts[1]);
                    default -> throw new IllegalArgumentException();
                }
            }
        }
        return depth * horizontalPos;
    }

    public static boolean isStringInt(String str) {
        try {
            Integer.parseInt(str);
        } catch(NumberFormatException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public static String[] inputAsArr() throws FileNotFoundException {
        File input = new File("/Users/christianmaleszka/Documents/02 - Development/AOC2021/AOC02/src/input.txt");
        Scanner scanner = new Scanner(input);
        String inputAsString = "";
        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            inputAsString = (!inputAsString.isEmpty()) ? (inputAsString + ":" + currentLine) : (inputAsString + currentLine);
        }
        return inputAsString.split(":");
    }
}
