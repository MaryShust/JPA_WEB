package ejb;

public class CheckerPoint {

    public boolean validation(double x, double y, int r) {
        return inRect(x, y, r) || inTriangle(x, y, r) || inCircle(x, y, r);
    }

    private boolean inRect(double x, double y, double r) {
        return x >= 0 && y <= 0 && x <= r && y >= -r;
    }

    private boolean inTriangle(double x, double y, double r) {
        return x <= 0 && y >= 0 && x >= -r && y <= r/2 && y - x/2 - r/2 <= 0;
    }

    private boolean inCircle(double x, double y, double r) {
        return x >= 0 && y >= 0 && x <= r/2 && y <= r/2 && (Math.pow(x, 2) + Math.pow(y, 2) - Math.pow(r/2, 2) <= 0);
    }
}
