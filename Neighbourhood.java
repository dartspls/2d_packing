import java.util.LinkedList;

class Neighbourhood {
    private Solution[] solutions;
    private int iter = 0;

    private LinkedList<Integer[]> tabuSwaps;
    private LinkedList<Integer> tabuRotates;

    public Neighbourhood(Solution sol, LinkedList<Integer[]> tabuSwaps, LinkedList<Integer> tabuRotates) {
        this.tabuSwaps = tabuSwaps;
        this.tabuRotates = tabuRotates;
        int solSize = sol.getSize();
        int neighbourhoodSize = (solSize / 2) * (1 + solSize);
        solutions = new Solution[neighbourhoodSize];
        //System.err.println("Solution size: " + solSize + " Neighbourhood size: " + neighbourhoodSize);
        
        // generate all solutions
        swaps(sol);
        rotates(sol);
    }

    public void swaps(Solution sol) {
        int solSize = sol.getSize();
        int index = solSize;
        for (int i = 0; i < solSize - 1; i ++) {
            for (int j = i + 1; j < solSize; j ++) {
                Rectangle[] pieces = sol.getPieces().clone();
                Rectangle temp = pieces[j];
                pieces[j] = pieces[i];
                pieces[i] = temp;
                solutions[index] = BottomLeft.place(pieces);
                solutions[index].setTabuSwaps(i, j);

                if(findTabuSwap(i, j)) {
                    solutions[index].setTabu(true);
                }
                index ++; // i think this can just be i + j - 1
            }
        }
    }

    public void rotates(Solution sol) {
        int size = sol.getSize();
        for(int i = 0; i < size; i ++) {
            Rectangle[] pieces = sol.getPieces().clone();
            pieces[i].rotate();
            solutions[i] = BottomLeft.place(pieces);
            solutions[i].setTabuRotates(i);
            if(findTabuRotate(i)) {
                solutions[i].setTabu(true);
            }
        }
    }

    public boolean hasNext() {
        return iter < solutions.length;
    }

    public Solution next() {
        iter ++;
        return solutions[iter - 1];
    }

    /**
     * Check if a swap is in the tabu list
     * @param x index of one item
     * @param y index of the other item
     * @return if either swap appears in the tabu list
     */
    private boolean findTabuSwap(int x, int y) {
        for(Integer[] ia : tabuSwaps) {
            if(ia[0] == x || ia[0] == y || ia[1] == x || ia[1] == y) {
                //System.out.println("Tabu swap detected");
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a rotate is in the tabu list
     * @param x index of item rotated
     * @return if the rotate is in the tabu list
     */
    private boolean findTabuRotate(int x) {
        for (Integer i : tabuRotates) {
            if (i == x) {
                //System.out.println("Tabu rotate detected");
                return true;
            }
        }
        return false;
    }
}