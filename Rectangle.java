public class Rectangle {

    private int id;
    private int width;
    private int height;
    private Point pos;

    public Rectangle(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
        pos = new Point(0, 0);
    }

    public void place(int x, int y) {
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

    public int getId() {
        return id;
    }

    public void setX(int x) {
        pos.setX(x);
    }

    public void setY(int y) {
        pos.setY(y);
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
        // return id + " " + width + " " + height + " " + pos.getX() + " " + pos.getY();
        return id + " " + pos.getX() + " " + pos.getY() + " " + width + " " + height;
    }
}