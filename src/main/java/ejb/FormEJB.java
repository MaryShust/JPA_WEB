package ejb;

import db.PointsDao;
import jakarta.ejb.Stateless;
import model.Points;
import java.util.List;
import static beans.MBeanManager.getAttemptsMBean;
import static beans.MBeanManager.getMissRatioMBean;

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
        CheckerPoint checkerPoint = new CheckerPoint();
        Points point = new Points();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        boolean resultCheck = checkerPoint.validation(x, y, r);
        getMissRatioMBean().updateData(resultCheck);
        getAttemptsMBean().updateData(resultCheck);
        point.setResult(resultCheck);
        point.setUser_id(userId);
        pointsDao.createPoint(point);
        return point;
    }
}