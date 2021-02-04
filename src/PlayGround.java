import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.pow;

public class PlayGround {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int colorsCount = sc.nextInt();
        int dimension = sc.nextInt();
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<ArrayList<String>> firstElements = new ArrayList<>();
        for (int i = 0; i < colorsCount; i++) {
            colors.add(sc.next().trim());
        }
        for (int i = 0; i <dimension ; i++) {
            firstElements.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                firstElements.get(i).add(sc.next());
            }
        }
        Node node = new Node(colors,firstElements,dimension);
    }


}
