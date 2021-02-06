import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.*;

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
        for (int i = 0; i < dimension; i++) {
            firstElements.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                firstElements.get(i).add(sc.next());
            }
        }
        Node node = new Node(colors, firstElements, dimension);
        if (!backtracking(node, dimension))
            System.out.println("No Answer!");

    }

    public static boolean backtracking(Node root, int dimension) {
//        System.out.println(root.getStatus());
//        System.out.println(root.getDomains());
        boolean isFound = false;
        int minSize = 100000;
        int maxSize = 0;
        int indexMinJ = 0;
        int indexMinI = 0;
        int indexMaxJ = 0;
        int indexMaxI = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if ((root.getStatus().get(i).get(j).charAt(0) == '*' || root.getStatus().get(i).get(j).charAt(1) == '#') && root.getDomains().get(i).get(j).size() == 0) {
                    return false;
                }
            }
        }
//        for (int i = 0; i <dimension ; i++) {
//            for (int j = 0; j < dimension; j++) {
//                if (root.getStatus().get(i).get(j).charAt(0)=='1' && root.getStatus().get(i).get(j).charAt(1) == root.getColors().get(root.getColors().size()-1).charAt(0) )
//                    return false;
//            }
//        }
        isFound = true;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (root.getStatus().get(i).get(j).charAt(0) == '*' || root.getStatus().get(i).get(j).charAt(1) == '#')
                    isFound = false;
            }
        }
        if (isFound) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    System.out.print(root.getStatus().get(i).get(j) + " ");
                }
                System.out.println();
            }
            return true;
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (root.getDomains().get(i).get(j).size() != 0 && root.getDomains().get(i).get(j).size() < minSize) {
                    minSize = root.getDomains().get(i).get(j).size();
                    indexMinI = i;
                    indexMinJ = j;
                }
            }
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (root.getDomains().get(i).get(j).size() > maxSize) {
                    maxSize = root.getDomains().get(i).get(j).size();
                    indexMaxI = i;
                    indexMaxJ = j;
                }
            }
        }
        if (minSize <= 3) {
            for (int k = 0; k < root.getDomains().get(indexMinI).get(indexMinJ).size(); k++) {
                if (!checkSurrounding(root.getStatus(), root.getDomains(), indexMinI, indexMinJ, k, root.getColors())) {
//                    System.out.println("fuck");
                    continue;
                }
                ArrayList<ArrayList<String>> newStatus = new ArrayList<>();
                copyStatus(root.getStatus(), newStatus);
                newStatus.get(indexMinI).set(indexMinJ, root.getDomains().get(indexMinI).get(indexMinJ).get(k));
                isFound = backtracking(new Node(root.getColors(), newStatus, dimension), dimension);
                if (isFound)
                    return true;
            }

        } else {
            for (int k = 0; k < root.getDomains().get(indexMaxI).get(indexMaxJ).size(); k++) {
                if (!checkSurrounding(root.getStatus(), root.getDomains(), indexMaxI, indexMaxJ, k, root.getColors())) {
//                    System.out.println("fuck");
                    continue;
                }
                ArrayList<ArrayList<String>> newStatus = new ArrayList<>();
                copyStatus(root.getStatus(), newStatus);
                newStatus.get(indexMaxI).set(indexMaxJ, root.getDomains().get(indexMaxI).get(indexMaxJ).get(k));
                isFound = backtracking(new Node(root.getColors(), newStatus, dimension), dimension);
                if (isFound)
                    return true;
            }
        }
        return false;
    }

    public static void copyStatus(ArrayList<ArrayList<String>> src, ArrayList<ArrayList<String>> dest) {
        for (int i = 0; i < src.size(); i++) {
            dest.add(new ArrayList<>());
            for (int j = 0; j < src.get(i).size(); j++) {
                dest.get(i).add(src.get(i).get(j));
            }
        }
    }

    public static boolean checkSurrounding(ArrayList<ArrayList<String>> status, ArrayList<ArrayList<ArrayList<String>>> domains, int i, int j, int k, ArrayList<String> colors) {
//        System.out.println(status + "/" + i + "/" + j + "/" + domains.get(i).get(j).get(k));
        try {
            if (status.get(i).get(j + 1).charAt(0) != '*' && status.get(i).get(j + 1).charAt(1) != '#') {
                if (Integer.parseInt(status.get(i).get(j + 1).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i).get(j + 1).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
                if (Integer.parseInt(status.get(i).get(j + 1).charAt(0) + "") < Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i).get(j + 1).charAt(1)) < colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
            }
        } catch (Exception e) {
        }
        try {
//            System.out.println(status.get(i).get(j - 1) + "/" + colors.indexOf("" + status.get(i).get(j - 1).charAt(1)));
            if (status.get(i).get(j - 1).charAt(0) != '*' && status.get(i).get(j - 1).charAt(1) != '#') {
                if (Integer.parseInt(status.get(i).get(j - 1).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i).get(j - 1).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
                if (Integer.parseInt(status.get(i).get(j - 1).charAt(0) + "") < Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i).get(j - 1).charAt(1)) < colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
            }
        } catch (Exception e) {
        }
        try {
            if (status.get(i + 1).get(j).charAt(0) != '*' && status.get(i + 1).get(j).charAt(1) != '#') {
                if (Integer.parseInt(status.get(i + 1).get(j).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i + 1).get(j).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
                if (Integer.parseInt(status.get(i + 1).get(j).charAt(0) + "") < Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i + 1).get(j).charAt(1)) < colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
            }
        } catch (Exception e) {
        }
        try {
            if (status.get(i - 1).get(j).charAt(0) != '*' && status.get(i - 1).get(j).charAt(1) != '#') {
                if (Integer.parseInt(status.get(i - 1).get(j).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i - 1).get(j).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
                if (Integer.parseInt(status.get(i - 1).get(j).charAt(0) + "") < Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i - 1).get(j).charAt(1)) < colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
                    return false;
            }
        } catch (Exception e) {
        }
        return true;
    }
}
