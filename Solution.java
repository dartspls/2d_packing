/**
 * Represents a single solution made up of placed pieces
 */
public class Solution {
    private int[] tabuSwaps;
    private int tabuRotates;
    private boolean tabu = false;

    Rectangle[] pieces;
    int height;

    public Solution(Rectangle[] pieces, int height) {
        this.pieces = pieces;
        this.height = height;
        tabuSwaps = new int[] {-1, -1};
        tabuRotates = -1;
    }

    /**
     * Get the score value of this solution
     * 
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
     * 
     * @return Array of rectangles in this solution
     */
    public Rectangle[] getPieces() {
        return pieces;
    }

    public int getSize() {
        return pieces.length;
    }

    public int[] tabuSwaps() {
        return tabuSwaps;
    }

    public void setTabuSwaps(int one, int two) {
        tabuSwaps[0] = one;
        tabuSwaps[1] = two;
    }

    public int tabuRotates() {
        return tabuRotates;
    }

    public void setTabuRotates(int i) {
        tabuRotates = i;
    }

    public boolean isTabu() {
        return tabu;
    }

    public void setTabu(boolean t) {
        tabu = t;
    }
}