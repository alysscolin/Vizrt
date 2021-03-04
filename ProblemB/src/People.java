import java.util.ArrayList;

public class People {
    String name;  //candidate name
    ArrayList<Integer> son = new ArrayList<>();  //a person might have several sons
    double[] blood = new double[2]; //0: father, 1: mother
    int farther;
    int mother;

    public People() {
        blood[0] = 0;
        blood[1] = 0;
        farther = 0;
        mother = 0;
    }
}
