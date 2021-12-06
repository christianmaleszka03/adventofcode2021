public class BingoFeld {
    private int number;
    private boolean marked;

    public BingoFeld(int pNumber, boolean pMarked) {
        this.number = pNumber;
        this.marked = pMarked;
    }

    public BingoFeld(int pNumber) {
        this.number = pNumber;
        this.marked = false;
    }

    public void setNumber(int pNumber) {
        this.number = pNumber;
    }

    public int getNumber() {
        return this.number;
    }

    public void setMark(boolean pMark) {
        this.marked = pMark;
    }

    public boolean isMarked() {
        return this.marked;
    }
}
