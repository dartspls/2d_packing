import java.util.LinkedList;
import java.util.Random;

public class Search {
    Random rng = new Random();

    public void performSearch(Rectangle[] pieces, int maxIterations) {
        shuffle(pieces);
        final int TABU_SIZE = 1000;

        Solution bestSol; // best solution encountered so far

        LinkedList<Integer[]> tabuSwaps = new LinkedList<>();
        LinkedList<Integer> tabuRotates = new LinkedList<>();


        Solution currentSol = BottomLeft.place(pieces); // current solution
        bestSol = currentSol;
        int iterations = 0;
        while (iterations < maxIterations) {
            // generate neighbourhood
            Neighbourhood n = new Neighbourhood(currentSol, tabuSwaps, tabuRotates);
            Solution bestInNeighbourhood = n.next();
            iterations++;
            int c = bestSol.getValue();
            while (n.hasNext()) {
                // evaluate current solution vs S
                Solution s = n.next();

                if(s.isTabu()) {
                    // aspiration criteria
                    if (bestSol.getValue() > s.getValue() && c > s.getValue()) {
                        //System.out.println("is tabu. old best: " + bestSol.getValue() + " tabu: " + s.getValue());
                        bestInNeighbourhood = s;
                        c = s.getValue();
                    }
                }
                else if (bestInNeighbourhood.getValue() > s.getValue()) {
                    bestInNeighbourhood = s;
                }
            }

            currentSol = bestInNeighbourhood; // move to best solution in neighbourhood

            // record tabu
            if(currentSol.tabuSwaps()[0] > -1) {
                tabuSwaps.add(new Integer[] {currentSol.tabuSwaps()[0], currentSol.tabuSwaps()[1]});
            } else if (currentSol.tabuRotates() > -1) {
                tabuRotates.add(currentSol.tabuRotates());
            }

            // delete old tabus if necessary
            if(tabuSwaps.size() > TABU_SIZE) {
                tabuSwaps.remove(tabuSwaps.size() - 1);
                //System.err.println("Removing tabu swap");
            }
            if(tabuRotates.size() > TABU_SIZE) {
                tabuRotates.remove(tabuRotates.size() - 1);
                //System.out.println("Removing tabu rotate");
            }

            // check if we found the new best solution overall
            if (bestSol.getValue() > currentSol.getValue()) {
                bestSol = currentSol;
                //System.out.println("improvement");
            }

        }
        // stopped making improvements
        System.out.println("Num iterations till local optima found: " + iterations);
        System.out.println("Optimal solution found: " + bestSol.getValue() + "\nID X Y W H");
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
