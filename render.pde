void setup() {
  size(500, 1000);
  String[] lines = loadStrings("rects.txt");
  //translate(0, height);

 // scale(1, -1);
  for (String s : lines) {
    String[] parts = s.split(" ");
    fill(255);
    int x = Integer.parseInt(parts[1]) * 5;
    int y = Integer.parseInt(parts[2]) * 5;
    int w = Integer.parseInt(parts[3]) * 5;
    int h = Integer.parseInt(parts[4]) * 5;
    rect(x, y, w, h);
    fill(0);
    // textAlign(CENTER);
    text(parts[0], x + (w / 2), y + (h / 2));
  }
}
