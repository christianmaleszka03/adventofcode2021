import java.util.ArrayList;

public class Line {
    private Koordninate start;
    private Koordninate end;
    private Koordninate[] coveredPoints = { start, end };

    public Line(Koordninate pStart, Koordninate pEnd) {
        this.start = pStart;
        this.end = pEnd;
        setupCoveredPoints();
    }

    // GETTER
    public Koordninate getStart() { return this.start; }
    public Koordninate getEnd() { return this.end; }

    public Koordninate[] getCoveredPoints() { return coveredPoints; }

    // PUBLIC METHODS
    public boolean isStraight() {
        return start.isSimilar(end);
    }

    public Koordninate[] overlappingPoints(Line other) {
        ArrayList<Koordninate> temp = new ArrayList<>();

        for(Koordninate koord1 : this.coveredPoints) {
            for(Koordninate koord2 : other.coveredPoints) {
                if(koord1.equals(koord2)) {

                    if(!temp.contains(koord1)) { temp.add(koord1); }
                    break;
                }
            }
        }
        Koordninate[] result = new Koordninate[temp.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }


    public String toString() {
        return ("Line: " + start.toString() + " -> " + end.toString());
    }

    // PRIVATE METHODS
    private void setupCoveredPoints() {
        if(start.equals(end)) { coveredPoints = new Koordninate[0]; }
        if(isStraight()) {
            ArrayList<Koordninate> temp = new ArrayList<>();

            if(start.getX() == end.getX()) {
                int tempX = start.getX();
                int greaterY = Math.max(start.getY(), end.getY());
                int lessY = Math.min(start.getY(), end.getY());
                for(int y = lessY; y <= greaterY; y++) {
                    Koordninate current = new Koordninate(tempX, y);
                    if(!temp.contains(current)) { temp.add(current); }
                }
            } else {
                int tempY = start.getY();
                int greaterX = Math.max(start.getX(), end.getX());
                int lessX = Math.min(start.getX(), end.getX());
                for(int x = lessX; x <= greaterX; x++) {
                    Koordninate current = new Koordninate(x, tempY);
                    temp.add(current);
                }
            }
            Koordninate[] result = new Koordninate[temp.size()];
            for(int i = 0; i < result.length; i++) {
                result[i] = temp.get(i);
            }
            coveredPoints = result;
        } else {
            ArrayList<Koordninate> temp = new ArrayList<>();
            boolean xNegative = (start.getX() > end.getX());
            boolean yNegative = (start.getY() > end.getY());
            int curX = start.getX();
            int curY = start.getY();
            while((curY != end.getY() || curX != end.getX())) {
                temp.add(new Koordninate(curX, curY));
                curX += (xNegative) ? -1 : 1;
                curY += (yNegative) ? -1 : 1;
            }
            temp.add(new Koordninate(curX, curY));
            coveredPoints = temp.toArray(new Koordninate[0]);
        }
    }
}