public class Rectangle {

    private int id;
    private int width;
    private int height;
    private Point pos;

    public Rectangle(int id, int x, int y, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
        pos = new Point(x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return pos.getX();
    }

    public int getY() {
        return pos.getY();
    }

    /**
     * Get the coordinates of the specified corner
     * @param c Corner number, 0 bottom left, 1 bottom right, 2 top right, 3 top left
     * @return Point with (x,y) coordinates of the specified corner
     */
    public Point getCorner(int c) {
        switch (c) {
            case 0:
                return pos;
            case 1:
                return new Point(pos.getX() + width, pos.getY());
            case 2:
                return new Point(pos.getX() + width, pos.getY() + height);
            case 3:
                return new Point(pos.getX(), pos.getY() + height);
            default:
                return pos;
        }
    }

    /**
     * Rotate the rectangle 90 degrees
     */
    public void rotate() {
        int temp = height;
        height = width;
        width = temp;
    }

    public String toString() {
        return id + " " + width + " " + height + " " + pos.getX() + " " + pos.getY();
    }
}