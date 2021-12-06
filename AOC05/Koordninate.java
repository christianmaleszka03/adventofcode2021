import java.util.Objects;

public class Koordninate implements Comparable {
    private int x;
    private int y;

    public Koordninate(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }

    public boolean isSimilar(Koordninate other) {
        return (this.x == other.getX() || this.y == other.getY());
    }

    public String toString() {
        return ("(" + x + "," + y + ")");
    }

    @Override
    public int compareTo(Object o) {
        // TODO
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Koordninate that = (Koordninate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
