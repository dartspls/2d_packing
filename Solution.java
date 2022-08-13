/**
 * Represents a single solution made up of placed pieces
 */
public class Solution {

    Rectangle[] pieces;
    int height;
    public Solution(Rectangle[] pieces, int height) {
        this.pieces = pieces;
        this.height = height;
    }

    /**
     * Get the score value of this solution
     * @return value of this solution, the height of the placed pieces
     */
    public int getValue() {
        return height;
    }

    /**
     * Print the pieces of this solution
     */
    public void print() {
        for (Rectangle r : pieces) {
            System.out.println(r.toString());
        }
    }

    /**
     * Get the pieces that make up the solution
     * @return Array of rectangles in this solution
     */
    public Rectangle[] getPieces() {
        return pieces;
    }
}