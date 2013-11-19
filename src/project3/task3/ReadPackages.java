package project3.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadPackages {

    public static List<Package> readFile() {

        List<Package> list = new ArrayList<Package>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/pso-packages.txt"));

            String input;
            while((input = br.readLine()) != null) {

                double value = Double.parseDouble(input.split(",")[0]);
                double weight = Double.parseDouble(input.split(",")[1]);

                list.add(new Package(value, weight));

            }


        } catch(Exception e) {
            System.out.println("Cant read file.");
            e.printStackTrace();
        }


        return list;
    }

    public static void main(String[] args) {
        System.out.println(readFile());
    }

}
