package dia.upm.cconvexo.android.model;

/**
 * Created by ivan on 17/09/13.
 */
public class Point {
    private int x;
    private int y;

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
