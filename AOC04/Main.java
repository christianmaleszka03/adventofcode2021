import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static private ArrayList<BingoSchein> bingoScheine = new ArrayList<>();
    static private int[] randoms;

    public static void main(String[] args) {
        try {
            String[] inputArr = inputAsArr();
            setUpRandoms(inputArr);
            setUpScheine(inputArr);
            System.out.println(challenge2());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int challenge1() {
        for(int currentRandomNumber : randoms) {
            Iterator<BingoSchein> it = bingoScheine.iterator();
            while(it.hasNext()) {
                BingoSchein currentSchein = it.next();
                if(currentSchein.markNumber(currentRandomNumber) && currentSchein.isWinner()) {
                    return currentSchein.calculateScore(currentRandomNumber);
                }
            }
        }
        return -1;
    }

    public static int challenge2() {
        for(int currentRandomNumber : randoms) {
            Iterator<BingoSchein> it = bingoScheine.iterator();
            while(it.hasNext()) {
                BingoSchein currentSchein = it.next();
                if(currentSchein.markNumber(currentRandomNumber) && currentSchein.isWinner()) {
                    if(bingoScheine.size() == 1) { return currentSchein.calculateScore(currentRandomNumber); }
                    bingoScheine.remove(currentSchein);
                    it = bingoScheine.iterator();
                }
            }
        }
        return -1;
    }

    private static void setUpRandoms(String[] inputArr) {
        String[] randomsAsStrings = inputArr[0].split(",");
        randoms = new int[randomsAsStrings.length];
        for(int i = 0; i < randomsAsStrings.length; i++) {
            randoms[i] = Integer.parseInt(randomsAsStrings[i]);
        }
    }

    private static void setUpScheine(String[] inputArr) {
        for(int i = 2; i < inputArr.length; i = i+6) {
            BingoSchein current = new BingoSchein(Arrays.copyOfRange(inputArr, i, i+5));
            bingoScheine.add(current);
        }
    }
    public static String[] inputAsArr() throws FileNotFoundException {
        File input = new File("/Users/christianmaleszka/Documents/02 - Development/AOC2021/AOC04/src/input.txt");
        Scanner scanner = new Scanner(input);
        String inputAsString = "";
        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
                inputAsString = (!inputAsString.isEmpty()) ? (inputAsString + ":" + currentLine) : (inputAsString + currentLine);
        }
        return inputAsString.split(":");
    }
}
