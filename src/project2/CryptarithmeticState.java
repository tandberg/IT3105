package project2;

import java.util.*;

public class CryptarithmeticState extends State {

    private List<Character> variables;
    private Set<Integer> domains;

    private Map<Character, Integer> mapping;

    private List<String> adders;
    private String sum;

    public CryptarithmeticState(String sum, String... adders) {

        this.sum = sum;
        this.adders = new ArrayList<String>();

        for(String a : adders) {

            for (int i = 0; i < a.length(); i++) {

            }

            this.adders.add(a);
        }





        mapping = new HashMap<Character, Integer>();

        mapping.put('T', 0);
        mapping.put('W', 0);
        mapping.put('O', 0);
        mapping.put('F', 0);
        mapping.put('U', 0);
        mapping.put('R', 0);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String adder : adders) {
            sb.append(getSpacesOfSum(adder) + adder + "\n");
        }
        sb.append("----\n");
        sb.append(this.sum+ "\n\n");

        for (Character variable : variables) {
            sb.append(variable +": " + mapping.get(variable));
        }

        return sb.toString();
    }


    private String getSpacesOfSum(String adder) {

        String s = "";
        for (int i = 0; i < this.sum.length() - adder.length(); i++) {
            s += " ";

        }
        return s;
    }

    public static void main(String[] args) {
        CryptarithmeticState state = new CryptarithmeticState("FOUR", "TWO", "TWO");

        System.out.println(state);
    }

    @Override
    public double evaluate() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public State copyState() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void randomize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moveRandom() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moveIntelligent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
