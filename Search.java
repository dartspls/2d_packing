import java.util.Random;

public class Search {
    Random rng = new Random();

    public void performSearch(Rectangle[] pieces, int maxIterations) {
        shuffle(pieces);

        Solution bestSol; // best solution encountered so far

        Solution currentSol = BottomLeft.place(pieces); // current solution
        bestSol = currentSol;
        int iterations = 0;
        while (iterations < maxIterations) {
            // generate neighbourhood
            Neighbourhood n = new Neighbourhood(currentSol);
            Solution bestInNeighbourhood = n.next();
            iterations++;
            while (n.hasNext()) {
                // evaluate current solution vs S
                Solution s = n.next();

                if (bestInNeighbourhood.getValue() > s.getValue()) {
                    bestInNeighbourhood = s;
                }
            }

            currentSol = bestInNeighbourhood; // move to best solution in neighbourhood
            // TODO: record tabu

            // check if we found the new best solution overall
            if (bestSol.getValue() > currentSol.getValue()) {
                bestSol = currentSol;
                System.err.println("improvement");
            }
        }
        // stopped making improvements
        System.out.println("Num iterations till local optima found: " + iterations);
        System.out.println("Optimal solution found: " + currentSol.getValue() + "\nID W H X Y");
        currentSol.print();
    }

    private void shuffle(Rectangle[] pieces) {
        for (int i = 0; i < pieces.length / 2; i++) {
            int randIndex = rng.nextInt(pieces.length);
            Rectangle temp = pieces[randIndex];
            pieces[randIndex] = pieces[i];
            pieces[i] = temp;

            if (randIndex % 2 == 0) {
                pieces[i].rotate();
            }
        }
    }
}
