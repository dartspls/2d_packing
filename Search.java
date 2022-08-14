import java.util.Random;

public class Search {
    Random rng = new Random();
    public void performSearch(Rectangle[] pieces) {
        shuffle(pieces);
        Solution bestSol = BottomLeft.place(pieces);
        boolean improvementMade = true;
        int iterations = 0;
        while(improvementMade) {
            improvementMade = false;
            // generate neighbourhood
            Neighbourhood n = new Neighbourhood(bestSol);
            
            iterations ++;
            while (n.hasNext()) {
               
                // evaluate bestSol vs S
                Solution s = n.next();
                // System.out.println(bestSol.getSize() + " " + s.getSize());
                if(bestSol.getValue() > s.getValue()) {
                    System.err.println("improvement");
                    bestSol = s;
                    improvementMade = true;
                }
                
            }
            if(iterations >= 10000) {
                break;
            }
            
        }
        // stopped making improvements
        System.out.println("Num iterations till local optima found: " + iterations);
        System.out.println("Optimal solution found: " + bestSol.getValue());
        bestSol.print();
    }

    private void shuffle(Rectangle[] pieces) {
        for (int i = 0; i < pieces.length / 2; i ++) {
            int randIndex = rng.nextInt(pieces.length);
            Rectangle temp = pieces[randIndex];
            pieces[randIndex] = pieces[i];
            pieces[i] = temp;
        }
    }
}
