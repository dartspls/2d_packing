class Neighbourhood {
    private Solution[] solutions;
    private int iter = 0;

    public Neighbourhood(Solution sol) {
        int solSize = sol.getSize();
        int neighbourhoodSize = (solSize / 2) * (1 + solSize);
        solutions = new Solution[neighbourhoodSize];
        System.err.println("Solution size: " + solSize + " Neighbourhood size: " + neighbourhoodSize);
        
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
        }
    }

    public boolean hasNext() {
        return iter < solutions.length;
    }

    public Solution next() {
        iter ++;
        return solutions[iter - 1];
    }
}