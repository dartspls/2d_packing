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

    /**
     * Move the point coordinates by specified amount
     * @param dx X move offset
     * @param dy Y move offset
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
