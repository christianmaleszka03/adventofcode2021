public class BingoSchein {
    BingoFeld[][] felder;

    public BingoSchein(String[] stringArr) {
        felder = setUpWithStringArr(stringArr);
    }

    public BingoFeld[][] setUpWithStringArr(String[] stringArr) throws IllegalArgumentException {
        BingoFeld[][] result = new BingoFeld[5][5];
        for(int i = 0; i < stringArr.length; i++) {
            String[] numbers = stringArr[i].trim().replaceAll(" +", " ").split(" ");
            for(int j = 0; j < numbers.length; j++) {
                System.out.println('"' + numbers[j] + '"');
                result[i][j] = new BingoFeld(Integer.parseInt(numbers[j]));
            }
        }
        System.out.println("--SETUP DONE--");
        return result;
    }

    public boolean markNumber(int number) {
        boolean foundNumber = false;
        for(BingoFeld[] currentLine : felder) {
            for(BingoFeld currentFeld : currentLine) {
                if(currentFeld.getNumber() == number) {
                    currentFeld.setMark(true);
                    foundNumber = true;
                }
            }
        }
        return foundNumber;
    }

    public int calculateScore(int currentRandomNumber) {
        int res = 0;
        // sum of all unmarked numbers
        for(BingoFeld[] currentLine : felder) {
            for(BingoFeld currentFeld : currentLine) {
                if(!currentFeld.isMarked()) { res += currentFeld.getNumber(); }
            }
        }
        return (res * currentRandomNumber);
    }

    public void printOut() {
        System.out.println("-----------------------");
        System.out.println("BINGOFELD:");
        for(int i = 0; i < felder.length; i++) {
            System.out.print("Zeile " + (i+1) + " ");
            for(int j = 0; j < felder[i].length; j++) {
                System.out.print("[" + felder[i][j].getNumber() + ":" + felder[i][j].isMarked() + "]");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    public boolean isWinner() {
        if(felder.length < 1) { return false; }
        // horizontal
        return isWinnerHorizontal() || isWinnerVertical();

    }

    private boolean isWinnerHorizontal() {
        for(BingoFeld[] currentLine : felder) {
            boolean res = true;
            for(BingoFeld currentFeld : currentLine) {
                if(!currentFeld.isMarked()) { res = false; }
            }
            if(res) { return true; }
        }
        return false;
    }
    private boolean isWinnerVertical() {

        for(int column = 0; column <= 4; column++) {
            boolean res = true;
            System.out.println("COLUMN: " + column);
            for(BingoFeld[] currentLine : felder) {
                System.out.println(currentLine[column].getNumber() + ": " + currentLine[column].isMarked());
                if(!currentLine[column].isMarked()) { res = false; }
            }
            if(res) { return true; }
        }
        return false;
    }
}
