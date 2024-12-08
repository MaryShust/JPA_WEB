package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Points {
    @Id
    @GeneratedValue
    private Long id;

    private double x;
    private double y;
    private double r;
    private boolean result;
    private long user_id;

    public Long getId() {
        return id;
    }
    public double getR() {
        return r;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public boolean getResult() {
        return result;
    }
    public long getUser_id() {
        return user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setR(double r) {
        this.r = r;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "[" + getId() + ", " + getX() + ", " + getY() + ", " + getR()  + ", " + getResult() + "]";
    }
}

//@Entity
//@Table(name = "POINTS")
//public class Point {
//
//    public Point() {}
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
//    private Long id;
//
//    @Column(name = "x")
//    private Double x;
//
//    @Column(name = "y")
//    private Double y;
//
//    @Column(name = "r")
//    private Double r;
//
//    @ManyToOne
//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
//    private User owner;
//
//    public User getOwner() {
//        return owner;
//    }
//
//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
//
//    public Double getX() {
//        return x;
//    }
//
//
//    private boolean result;
//
//    public boolean isResult() {
//        return result;
//    }
//
//    public void setResult(boolean result) {
//        this.result = result;
//    }
//
//    public void check() {
//        if ((x == null) || (y == null) || (r == null))
//            throw new NullPointerException();
//        this.result = (x >= 0 && y <= 0 && y >= 2 * x - r) ||
//                (x <= 0 && y >= 0 && x >= -r && y <= r / 2) ||
//                (x <= 0 && y <= 0 && x * x + y * y <= r * r);
//    }
//
//    public void setX(Double x) {
//        this.x = x;
//    }
//
//    public Double getY() {
//        return y;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setY(Double y) {
//        this.y = y;
//    }
//
//    public Double getR() {
//        return r;
//    }
//
//    public void setR(Double r) {
//        this.r = r;
//    }
//
//}