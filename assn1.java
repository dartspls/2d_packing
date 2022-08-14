import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * 2D Packing problem assignment for COMPX556
 * Daniel Shepherd
 * 2022
 */
public class assn1 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: <filename> <iteration count>");
            System.out.print("Expected file input format below:" + // java can't do multiline strings??
                             "\n<id>,<width>,<height>" +
                             "\n..." +
                             "\n<area sum of objects>\n");
            return;
        }

        ArrayList<Rectangle> rects = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = "";


            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 1) {
                    // stop? do we care about area idk
                    break;
                }

                rects.add(new Rectangle(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }
            reader.close();

            // at this point we probably have an array list of rectangles
            //rects.stream().forEach(System.out::println);
            Rectangle[] res = new Rectangle[rects.size()];
            rects.toArray(res);
            //System.out.println("Score: " + BottomLeft.place(res).getValue());
            System.err.println("Local search");
            System.out.println(args[0]);
            new Search().performSearch(res, Integer.parseInt(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}