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
//        backtracking(node,dimension);
    }
    public static void backtracking(Node root,int dimension){
        boolean isFound = false;
        boolean isSize = false;
        int size = 1;
        ArrayList<ArrayList<ArrayList<String>>> domain = root.getDomains();
        ArrayList<ArrayList<String>> status = root.getStatus();
        System.out.println(status);
        for (int i = 0; i < dimension ; i++) {
            for (int j = 0; j < dimension; j++) {
                for (int k = 0; k < domain.get(i).get(j).size(); k++) {
                    if (status.get(i).get(j).charAt(0) == '*' || status.get(i).get(j).charAt(1) == '#' && domain.get(i).get(j).size()==0) {
                        isFound = true;
                        break;
                    }
                }
                if (isFound)
                    break;
            }
            if (isFound)
                break;
        }
        for (int i = 0; i <dimension ; i++) {
            for (int j = 0; j <dimension ; j++) {
                if (domain.get(i).get(j).size()!= 0){
                    isSize = true;
                    isFound =false;
                    break;
                }
                else
                    isFound = true;
            }
            if (isSize)
                break;
        }
        while (!isFound){
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (domain.get(i).get(j).size()==size){
                        for (int k = 0; k <domain.get(i).get(j).size() ; k++) {
                            status.get(i).set(j,domain.get(i).get(j).get(k));
                            backtracking(new Node(root.getColors(),status,dimension),dimension);
                        }
                    }

                }
            }
//            size++;
        }
        if (isFound){
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (status.get(i).get(j).contains("*") || status.get(i).get(j).contains("#")){
                        System.out.println("No Answer!");
                        return;
                    }
                }
            }
            System.out.println(status);
        }
    }


}
