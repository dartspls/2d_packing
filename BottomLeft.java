public class BottomLeft {
    private static final int WIDTH = 100;

    // public static Solution place_old(Rectangle[] incommingPieces) {
    //     Rectangle[] placed = new Rectangle[incommingPieces.length];
    //     int height = 0; // current height of placed blocks. New blocks placed at height + rect.height
    //     int curRectangle = 0; // index for rectangle currently being placed

    //     for (Rectangle rect : incommingPieces) {
    //         rect.place(WIDTH - rect.getWidth(), height + 1);
    //         placed[curRectangle] = rect;
    //         // sanity check
    //         if (!collisionFree(placed, curRectangle)) {
    //             System.out.println("Collision immediately after placing with placed pieces:");
    //             for (Rectangle r : placed) {
    //                 r.toString();
    //             }
    //         }

    //         boolean finalPos = false;
    //         while (!finalPos) {
    //             boolean canMoveDown = true;
    //             boolean canMoveLeft = true;
    //             while (canMoveDown) {
    //                 canMoveDown = tryMove(placed, curRectangle, 0, -1);
    //             }

    //             while (canMoveLeft) {
    //                 canMoveLeft = tryMove(placed, curRectangle, -1, 0);
    //             }
    //             finalPos = lockedInPlace(placed, curRectangle);
    //         }
    //         if (rect.getY() + rect.getHeight() > height) {
    //             // new max height
    //             height = rect.getY() + rect.getHeight();
    //         }
    //         curRectangle++;
    //     }
    //     Solution result = new Solution(placed, height);
    //     return result;
    // }

    public static Solution place(Rectangle[] incommingPieces) {
        Rectangle[] placed = new Rectangle[incommingPieces.length];
        int height = 0;
        int curRectangle = 0;

        for(Rectangle r : incommingPieces) {
            r.place(WIDTH - r.getWidth(), height + 100);
            while(true) {
                int nearestY = 0;
                int minDist = 999999999;
                for (Rectangle r2 : placed) {
                    if(r2 == null) continue;
                    if (overlappingRanges(r.getX(), r.getX() + r.getWidth(), r2.getX(), r2.getX() +r2.getWidth())) {
                        int dist = (r.getY() + r.getHeight()) - (r2.getY() + r2.getHeight());
                        if (dist >= 0 && dist < minDist) {
                            minDist = dist;
                            nearestY = r2.getY() + r2.getHeight();
                        }
                    }
                }
                if (r.getY() == nearestY) break; // we've already gone down as far as we can from earlier loop, exit
                r.setY(nearestY);

                int nearestX = 0;
                minDist = WIDTH + 1; // material is always 100 width
                for(Rectangle r2 : placed) {
                    if(r2 == null) continue;
                    if(overlappingRanges(r.getY(), r.getY() + r.getHeight(), r2.getY(), r2.getY() + r2.getHeight())) {
                        int dist = r.getX() - (r2.getX() + r2.getWidth());
                        if (dist >= 0 && dist < minDist) {
                            minDist = dist;
                            nearestX = r2.getX() + r2.getWidth();
                        }
                    }
                }
                if(r.getX() + r.getWidth() == nearestX) break; // already hit the bottom before moving, end
                r.setX(nearestX);
            }
            placed[curRectangle] = r;
            if (r.getY() + r.getHeight() > height) height = r.getY() + r.getHeight();
            curRectangle ++;
        }
        Solution result = new Solution(placed, height);
        return result;
    }


    /**
     * Check if two horizontal or vertical lines overlap
     * @param x1 Start of line 1
     * @param y1 End of line 1
     * @param x2 Start of line 2
     * @param y2 End of line 2
     * @return true if the two lines overlap
     */
    private static boolean overlappingRanges(int x1, int y1, int x2, int y2) {
        if ((x1 < y2) && (x2 < y1)) {
            return true;
        } 
        return false; 
    }

    // horribly innefficient lol

    // private static boolean tryMove(Rectangle[] pieces, int rIndex, int x, int y) {
    //     pieces[rIndex].setPos(x, y);
    //     if (collisionFree(pieces, rIndex)) {
    //         return true;
    //     } else {
    //         pieces[rIndex].setPos(-x, -y); // undo move
    //         return false;
    //     }
    // }

    // private static boolean lockedInPlace(Rectangle[] pieces, int rIndex) {
    //     boolean leftLocked = false;
    //     boolean downLocked = false;

    //     pieces[rIndex].setPos(0, -1); // move left
    //     leftLocked = !collisionFree(pieces, rIndex);
    //     pieces[rIndex].setPos(0, 1);

    //     pieces[rIndex].setPos(-1, 0); // move down
    //     downLocked = !collisionFree(pieces, rIndex);
    //     pieces[rIndex].setPos(1, 0);

    //     return leftLocked && downLocked;
    // }

    // private static boolean collisionFree(Rectangle[] pieces, int cIndex) {

    //     // collision code adapted from
    //     // https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
    //     Rectangle r1 = pieces[cIndex];

    //     // check against edges of material
    //     if (r1.getX() < 0 || r1.getX() + r1.getWidth() > WIDTH || r1.getY() < 0) {
    //         return false;
    //     }

    //     for (int i = 0; i < cIndex; i++) {
    //         Rectangle r2 = pieces[i];

    //         if (r1.getX() < r2.getX() + r2.getWidth() &&
    //                 r1.getX() + r1.getWidth() > r2.getX() &&
    //                 r1.getY() < r2.getY() + r2.getHeight() &&
    //                 r1.getY() + r1.getHeight() > r2.getY()) {
    //             // we have a collision
    //             return false;
    //         }
    //     }
    //     return true;
    // }
}
