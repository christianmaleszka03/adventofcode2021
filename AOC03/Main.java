import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
        int[][] counter = new int[12][2];

        // fill the counter array
        for(String currentLine : inputArr) {
            if(!currentLine.isEmpty()) {
                if(!isBinary(currentLine)) { throw new IllegalArgumentException(); }
                char[] bits = currentLine.toCharArray();
                for(int i = 0; i < bits.length; i++) {
                    char currentBit= bits[i];
                    if(currentBit == '1') {
                        counter[i][1] = counter[i][1] + 1;
                    } else {
                        counter[i][0] = counter[i][0] + 1;
                    }
                }
            }
        }

        //calculate gamma / epsilon rate
        String gammaRate = "";
        String epsilonRate = "";
        for(int i = 0; i < counter.length; i++) {
            int[] currentCounter = counter[i];
            if(currentCounter[0] < currentCounter[1]) {
                gammaRate = gammaRate + '1';
                epsilonRate = epsilonRate + '0';
            } else {
                gammaRate = gammaRate + '0';
                epsilonRate = epsilonRate + '1';
            }
        }
        return (binaryToDecimal(gammaRate) * binaryToDecimal(epsilonRate));
    }

    public static int challenge2(String[] inputArr) throws IllegalArgumentException {
        if(inputArr.length == 0) { throw new IllegalArgumentException(); }
        String oxGenRating = "";
        String co2scrubRating = "";

        // Oxigen
        ArrayList<String> remainingBitCodesOx =  new ArrayList<>(List.of(inputArr));
        int currentIndex = 0;

        while(currentIndex < remainingBitCodesOx.get(0).length()) {
            ArrayList<String> zeroAtIndex = new ArrayList<>();
            ArrayList<String> oneAtIndex = new ArrayList<>();

            for(int i = 0; i < remainingBitCodesOx.size(); i++) {
                String currentBitCode = remainingBitCodesOx.get(i);
                if(!isBinary(currentBitCode)) { throw new IllegalArgumentException(); };
                if(currentBitCode.charAt(currentIndex) == '0') {
                    zeroAtIndex.add(currentBitCode);
                } else {
                    oneAtIndex.add(currentBitCode);
                }
            }
            if(oneAtIndex.size() >= zeroAtIndex.size()) {
                remainingBitCodesOx = oneAtIndex;
            } else {
                remainingBitCodesOx = zeroAtIndex;
            }
            if(remainingBitCodesOx.size() == 1) {
                oxGenRating = remainingBitCodesOx.get(0);
                break;
            }
            currentIndex++;
        }
        if(oxGenRating.equals("")) { throw new IllegalArgumentException(); }

        // CO2
        ArrayList<String> remainingBitCodesCO2 =  new ArrayList<>(List.of(inputArr));
        currentIndex = 0;

        while(currentIndex < remainingBitCodesCO2.get(0).length()) {
            ArrayList<String> zeroAtIndex = new ArrayList<>();
            ArrayList<String> oneAtIndex = new ArrayList<>();

            for(int i = 0; i < remainingBitCodesCO2.size(); i++) {
                String currentBitCode = remainingBitCodesCO2.get(i);
                if(!isBinary(currentBitCode)) { throw new IllegalArgumentException(); };
                if(currentBitCode.charAt(currentIndex) == '0') {
                    zeroAtIndex.add(currentBitCode);
                } else {
                    oneAtIndex.add(currentBitCode);
                }
            }
            if(oneAtIndex.size() >= zeroAtIndex.size()) {
                remainingBitCodesCO2 = zeroAtIndex;
            } else {
                remainingBitCodesCO2 = oneAtIndex;
            }
            if(remainingBitCodesCO2.size() == 1) {
                co2scrubRating = remainingBitCodesCO2.get(0);
                break;
            }
            currentIndex++;
        }
        if(co2scrubRating.equals("")) { throw new IllegalArgumentException(); }
        return (binaryToDecimal(co2scrubRating) * binaryToDecimal(oxGenRating));
    }

    private static int binaryToDecimal(String binaryString) throws IllegalArgumentException {
        if(!isBinary(binaryString)) { throw new IllegalArgumentException(); }
        int decimal = 0;
        for(int i = 0; i < binaryString.length(); i++) {
            char currentBit = binaryString.charAt(binaryString.length() - 1 - i);
            decimal += Integer.parseInt(String.valueOf(currentBit)) * Math.pow(2, i);
        }
        return decimal;
    }

    private static boolean isBinary(String input) {
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) != '0' && input.charAt(i) != '1') { return false; }
        }
        return true;
    }

    public static String[] inputAsArr() throws FileNotFoundException {
        File input = new File("/Users/christianmaleszka/Documents/02 - Development/AOC2021/AOC03/src/input.txt");
        Scanner scanner = new Scanner(input);
        String inputAsString = "";
        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if(currentLine != "") {
                inputAsString = (!inputAsString.isEmpty()) ? (inputAsString + ":" + currentLine) : (inputAsString + currentLine);
            }
        }
        return inputAsString.split(":");
    }
}
