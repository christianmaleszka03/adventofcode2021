import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try{
            System.out.println(challenge1(inputAsArr()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // In this state this method solves challenge 2
    // To solve challenge 1 you just need to prevent non-straigh line form entering the 'allLines'-ArrayList :)
    static int challenge1(String[] inputArr) {
        ArrayList<Line> allLines = new ArrayList<>();
        for(String currentLine : inputArr) {
            String[] currentValues = currentLine.split(" -> ");
            String[] startValues = currentValues[0].split(",");
            String[] endValues = currentValues[1].split(",");
            Line newOne = new Line(
                    new Koordninate(Integer.parseInt(startValues[0]),Integer.parseInt(startValues[1])),
                    new Koordninate(Integer.parseInt(endValues[0]),Integer.parseInt(endValues[1]))
            );
            allLines.add(newOne);
        }
        Set<Koordninate> coveredTwice = new HashSet<>();
        for(Line line1 : allLines) {
            for(Line line2 : allLines) {
                if(!line1.equals(line2)) {
                    for(Koordninate currentKoord : line1.overlappingPoints(line2)) {
                        if(!coveredTwice.contains(currentKoord)) {
                            coveredTwice.add(currentKoord);
                        }
                    }
                }
            }
        }
        return coveredTwice.size();
    }

    public static String[] inputAsArr() throws FileNotFoundException {
        File input = new File("/Users/christianmaleszka/Documents/02 - Development/AOC2021/AOC5/src/input.txt");
        Scanner scanner = new Scanner(input);
        String inputAsString = "";
        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            inputAsString = (!inputAsString.isEmpty()) ? (inputAsString + ":" + currentLine) : (inputAsString + currentLine);
        }
        return inputAsString.split(":");
    }
}
