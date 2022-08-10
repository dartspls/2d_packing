public class BottomLeft {
    public static int place(Rectangle[] pieces) {
        Rectangle[] placed = new Rectangle[pieces.length];
        int height = 0; // current height of placed blocks. New blocks placed at height + rect.height
        int curRectangle = 0; // index for rectangle currently being placed



    }

    private static boolean checkCollisions(Rectangle[] pieces, int cIndex) {

        /*
        rect1.x < rect2.x + rect2.w &&
        rect1.x + rect1.w > rect2.x &&
        rect1.y < rect2.y + rect2.h &&
        rect1.h + rect1.y > rect2.y
         */
        Rectangle r1 = pieces[cIndex];
        for (int i = 0; i < cIndex; i ++) {
           Rectangle r2 = pieces[i];
           if (r1.getX() < r2.getX() + r2.getWidth() &&
                r1.getX() + r1.getWidth() > r2.getX() &&
                r1.getY() < r2.getY() + r2.getHeight() &&
                r1.getY() + r1.getHeight() > r2.getY()) {

           }
        }
    }
}
