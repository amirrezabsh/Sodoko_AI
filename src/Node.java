import javax.crypto.spec.PSource;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;

public class Node {
    ArrayList<String> colors = new ArrayList<>();
    ArrayList<ArrayList<String>> availableNum = new ArrayList<>();
    ArrayList<ArrayList<String>> status = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<String>>> domains = new ArrayList<>();
    int dimension;

    public ArrayList<ArrayList<String>> getStatus() {
        return status;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getDomains() {
        return domains;
    }

    public Node(ArrayList<String> colors, ArrayList<ArrayList<String>> status, int dimension) {
        this.colors = colors;
        copyStatus(status, this.status);
        this.dimension = dimension;
        this.domains = domainCreator(this.status, this.colors);
        domainChecker(this.domains, this.status, this.dimension, this.colors);
    }

    public static ArrayList<ArrayList<ArrayList<String>>> domainCreator(ArrayList<ArrayList<String>> status, ArrayList<String> colors) {
        ArrayList<ArrayList<ArrayList<String>>> domains = new ArrayList<>();
        for (int i = 0; i < status.size(); i++) {
            domains.add(new ArrayList<>());
            for (int j = 0; j < status.size(); j++) {
                domains.get(i).add(new ArrayList<>());
            }
        }
        for (int i = 0; i < status.size(); i++) {
            for (int j = 0; j < status.size(); j++) {
                if (status.get(i).get(j).charAt(0) == '*' && status.get(i).get(j).charAt(1) == '#') {
                    for (int k = 0; k < colors.size(); k++) {
                        for (int l = 0; l < status.size(); l++) {
                            domains.get(i).get(j).add((l + 1) + "" + colors.get(k));
                        }
                    }
                } else if (status.get(i).get(j).charAt(0) != '*' && status.get(i).get(j).charAt(1) == '#') {
                    for (int k = 0; k < colors.size(); k++) {
                        domains.get(i).get(j).add(status.get(i).get(j).charAt(0) + "" + colors.get(k));
                    }
                } else if (status.get(i).get(j).charAt(0) == '*' && status.get(i).get(j).charAt(1) != '#') {
                    for (int k = 0; k < status.size(); k++) {
                        domains.get(i).get(j).add((k + 1) + "" + status.get(i).get(j).charAt(1));
                    }
                }
            }
        }
        return domains;
    }

    public static void domainChecker(ArrayList<ArrayList<ArrayList<String>>> domains, ArrayList<ArrayList<String>> status, int dimension, ArrayList<String> colors) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                ArrayList<String> delete = new ArrayList<>();
                for (int k = 0; k < dimension; k++) {
                    for (int l = 0; l < domains.get(i).get(j).size(); l++) {
                        numChecker(domains, status, i, j, delete, k, l, status.get(i));
                        numChecker(domains, status, i, j, delete, j, l, status.get(k));
                    }
                }
                for (int k = 0; k < domains.get(i).get(j).size(); k++) {
                    try {
                        if (status.get(i).get(j + 1).charAt(1) == domains.get(i).get(j).get(k).charAt(1)) {
                            if (!delete.contains(domains.get(i).get(j).get(k)))
                                delete.add(domains.get(i).get(j).get(k));
                        }
//                        if (status.get(i).get(j + 1).charAt(0) != '*' && status.get(i).get(j + 1).charAt(1) != '#')
//                            if (Integer.parseInt(status.get(i).get(j + 1).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i).get(j + 1).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
//                                if (!delete.contains(domains.get(i).get(j).get(k)))
//                                    delete.add(domains.get(i).get(j).get(k));
                    } catch (Exception e) {
                    }
                    try {
                        if (status.get(i).get(j - 1).charAt(1) == domains.get(i).get(j).get(k).charAt(1)) {
                            if (!delete.contains(domains.get(i).get(j).get(k)))
                                delete.add(domains.get(i).get(j).get(k));
                        }
//                        if (status.get(i).get(j - 1).charAt(0) != '*' && status.get(i).get(j - 1).charAt(1) != '#')
//                            if (Integer.parseInt(status.get(i).get(j - 1).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i).get(j - 1).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
//                                if (!delete.contains(domains.get(i).get(j).get(k)))
//                                    delete.add(domains.get(i).get(j).get(k));
                    } catch (Exception e) {
                    }
                    try {
                        if (status.get(i + 1).get(j).charAt(1) == domains.get(i).get(j).get(k).charAt(1)) {
                            if (!delete.contains(domains.get(i).get(j).get(k)))
                                delete.add(domains.get(i).get(j).get(k));
                        }
//                        if (status.get(i + 1).get(j).charAt(0) != '*' && status.get(i).get(j + 1).charAt(1) != '#')
//                            if (Integer.parseInt(status.get(i + 1).get(j).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i + 1).get(j).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
//                                if (!delete.contains(domains.get(i).get(j).get(k)))
//                                    delete.add(domains.get(i).get(j).get(k));
                    } catch (Exception e) {
                    }
                    try {
                        if (status.get(i - 1).get(j).charAt(1) == domains.get(i).get(j).get(k).charAt(1)) {
                            if (!delete.contains(domains.get(i).get(j).get(k)))
                                delete.add(domains.get(i).get(j).get(k));
                        }
//                        if (status.get(i - 1).get(j).charAt(0) != '*' && status.get(i).get(j - 1).charAt(1) != '#')
//                            if (Integer.parseInt(status.get(i - 1).get(j).charAt(0) + "") > Integer.parseInt(domains.get(i).get(j).get(k).charAt(0) + "") && colors.indexOf("" + status.get(i - 1).get(j).charAt(1)) > colors.indexOf("" + domains.get(i).get(j).get(k).charAt(1)))
//                                if (!delete.contains(domains.get(i).get(j).get(k)))
//                                    delete.add(domains.get(i).get(j).get(k));
                    } catch (Exception e) {
                    }
                }
                for (int k = 0; k < delete.size(); k++) {
                    if (domains.get(i).get(j).contains(delete.get(k)))
                        domains.get(i).get(j).remove(delete.get(k));
                }
            }
        }
    }

    private static void numChecker(ArrayList<ArrayList<ArrayList<String>>> domains, ArrayList<ArrayList<String>> status, int i, int j, ArrayList<String> delete, int k, int l, ArrayList<String> strings) {
        if (!status.get(i).get(j).equals(strings.get(k))) {
            if (domains.get(i).get(j).get(l).charAt(0) == strings.get(k).charAt(0)) {
                if (!delete.contains(domains.get(i).get(j).get(l)))
                    delete.add(domains.get(i).get(j).get(l));
            }
        }
    }

    private static void copyStatus(ArrayList<ArrayList<String>> src, ArrayList<ArrayList<String>> dest) {
        for (int i = 0; i < src.size(); i++) {
            dest.add(new ArrayList<>());
            for (int j = 0; j < src.get(i).size(); j++) {
                dest.get(i).add(src.get(i).get(j));
            }
        }
    }
}
