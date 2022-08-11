public class BottomLeft {
    private static final int WIDTH = 100;
    public static int place(Rectangle[] incommingPieces) {
        Rectangle[] placed = new Rectangle[incommingPieces.length];
        int height = 0; // current height of placed blocks. New blocks placed at height + rect.height
        int curRectangle = 0; // index for rectangle currently being placed

        for (Rectangle rect : incommingPieces) {
            rect.place(WIDTH - rect.getWidth(), height + 1);
            placed[curRectangle] = rect;
            // sanity check
            if(!collisionFree(placed, curRectangle)) {
                System.out.println("Collision immediately after placing with placed pieces:");
                for(Rectangle r : placed) {
                    r.toString();
                }
            }

            boolean finalPos = false;
            while(!finalPos) {
                boolean canMoveDown = true;
                boolean canMoveLeft = true;
                while (canMoveDown) {
                    canMoveDown = tryMove(placed, curRectangle, 0, -1);
                }

                while(canMoveLeft) {
                    canMoveLeft = tryMove(placed, curRectangle, -1, 0);
                }
                finalPos = lockedInPlace(placed, curRectangle);
            }
            if(rect.getY() + rect.getHeight() > height) {
                // new max height
                height = rect.getY() + rect.getHeight();
            }
            curRectangle++;
        }
        return height;
    }

    private static boolean tryMove(Rectangle[] pieces, int rIndex, int x, int y) {
        pieces[rIndex].move(x, y);
        if (collisionFree(pieces, rIndex)) {
            return true;
        } else {
            pieces[rIndex].move(-x, -y); // undo move
            return false;
        }
    }

    private static boolean lockedInPlace(Rectangle[] pieces, int rIndex) {
        boolean leftLocked = false;
        boolean downLocked = false;

        pieces[rIndex].move(0, -1); // move left
        leftLocked = !collisionFree(pieces, rIndex);
        pieces[rIndex].move(0, 1);

        pieces[rIndex].move(-1, 0); // move down
        downLocked = !collisionFree(pieces, rIndex);
        pieces[rIndex].move(1, 0);

        return leftLocked && downLocked;
    }

    private static boolean collisionFree(Rectangle[] pieces, int cIndex) {

        // collision code adapted from 
        // https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
        Rectangle r1 = pieces[cIndex];

        // check against edges of material
        if (r1.getX() < 0 || r1.getX() + r1.getWidth() > WIDTH || r1.getY() < 0) {
            return false;
        }

        for (int i = 0; i < cIndex; i ++) {
           Rectangle r2 = pieces[i];
           if (r1.getX() < r2.getX() + r2.getWidth() &&
                r1.getX() + r1.getWidth() > r2.getX() &&
                r1.getY() < r2.getY() + r2.getHeight() &&
                r1.getY() + r1.getHeight() > r2.getY()) {
                // we have a collision
                return false;
           }
        }
        return true;
    }
}
