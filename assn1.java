import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * assn1
 */
public class assn1 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No args");
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

                rects.add(new Rectangle(Integer.parseInt(parts[0]), 0, 0, Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }

            reader.close();

            // at this point we probably have an array list of rectangles
            rects.stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}