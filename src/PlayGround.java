import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.pow;

public class PlayGround {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int colorsCount = sc.nextInt();
        int dimension = sc.nextInt();
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<String> firstElements = new ArrayList<>();
        for (int i = 0; i < colorsCount; i++) {
            colors.add(sc.next().trim());
        }
        for (int i = 0; i <pow(dimension,2) ; i++) {
            firstElements.add(sc.next().trim());
        }
        System.out.println(colors);
        System.out.println(dimension);
        System.out.println(pow(dimension,2));
        System.out.println(firstElements);
        System.out.println(colors);
    }


}
