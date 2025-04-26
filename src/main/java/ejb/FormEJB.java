package ejb;

import db.PointsDao;
import jakarta.ejb.Stateless;
import model.Points;

import java.util.List;

@Stateless
public class FormEJB {

    PointsDao pointsDao = new PointsDao();

    public Long getCount(Long userId) {
        return pointsDao.getCount(userId);
    }

    public List<Points> getAllByUser(Long userId, int first, int rows) {
        return pointsDao.getLastPoints(userId, first, rows);
    }

    public Points addNewElement(double x, double y, int r, long userId) {
        Points point = new Points();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setResult(validation(x, y, r));
        point.setUser_id(userId);
        pointsDao.createPoint(point);
        return point;
    }

    private boolean validation(double x, double y, int r) {
        return inRect(x, y, r) || inTriangle(x, y, r) || inCircle(x, y, r);
    }

    private boolean inRect(double x, double y, double r) {
        return x >= 1 && y <= 0 && x <= r && y >= -r;
    }

    private boolean inTriangle(double x, double y, double r) {
        return x <= 0 && y >= 0 && x >= -r && y <= r/2 && y - x/2 - r/2 <= 0;
    }

    private boolean inCircle(double x, double y, double r) {
        return x >= 0 && y >= 0 && x <= r/2 && y <= r/2 && (Math.pow(x, 2) + Math.pow(y, 2) - Math.pow(r/2, 2) <= 0);
    }
}