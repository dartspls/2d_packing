public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the X position of the point
     * @return X coordinate of the point
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y position of the point
     * @return Y coordinate of point
     */
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
