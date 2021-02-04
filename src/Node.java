import javax.crypto.spec.PSource;
import java.util.ArrayList;

public class Node {
    ArrayList<String> colors = new ArrayList<>();
    ArrayList<ArrayList<String>> status = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<String>>> domains = new ArrayList<>();
    int dimension;
    public Node(ArrayList<String> colors,ArrayList<ArrayList<String>> status,int dimension) {
        this.colors = colors;
        this.status = status;
        this.dimension = dimension;
        this.domains = domainCreator(this.status,this.colors);
    }
    public static ArrayList<ArrayList<ArrayList<String>>> domainCreator (ArrayList<ArrayList<String>> status,ArrayList<String> colors){
        ArrayList<ArrayList<ArrayList<String>>> domains = new ArrayList<>();
        for (int i = 0; i <status.size() ; i++) {
            domains.add(new ArrayList<>());
            for (int j = 0; j <status.size() ; j++) {
                domains.get(i).add(new ArrayList<>());
            }
        }
        for (int i = 0; i <status.size() ; i++) {
            for (int j = 0; j < status.size(); j++) {
                if (status.get(i).get(j).charAt(0) == '*' && status.get(i).get(j).charAt(1) == '#'){
                    for (int k = 0; k < colors.size() ; k++) {
                        for (int l = 0; l < status.size(); l++) {
                            domains.get(i).get(j).add((l+1)+""+colors.get(k));
                        }
                    }
                }
                else if (status.get(i).get(j).charAt(0) != '*' && status.get(i).get(j).charAt(1) == '#'){
                    for (int k = 0; k < colors.size(); k++) {
                        domains.get(i).get(j).add(status.get(i).get(j).charAt(0)+""+colors.get(k));
                    }
                }
                else if (status.get(i).get(j).charAt(0) == '*' && status.get(i).get(j).charAt(1) != '#'){
                    for (int k = 0; k < status.size(); k++) {
                        domains.get(i).get(j).add(k + "" + status.get(i).get(j).charAt(1));
                    }
                }
            }
        }
        return domains;
    }
    public static void domainChecker(ArrayList<ArrayList<ArrayList<String>>> domains,ArrayList<ArrayList<String>> status,int dimension){
        for (int i = 0; i < dimension ; i++) {
            for (int j = 0; j < dimension ; j++) {
                try{
                    if (status.get(i).get(j).charAt(0) == status.get(i).get(j-1).charAt(0)){
                        for (int k = 0; k < domains.get(i).get(j).size(); k++) {
                            if (status.get(i).get(j).charAt(0) == domains.get(i).get(j).get(k).charAt(0))
                                domains.get(i).get(j).remove(k);
                        }
                    }
                }
                catch (Exception e){
                }
            }
        }
    }
}
