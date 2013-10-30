package project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {


    public static List<Coordinate> readInput() {
        List<Coordinate> particles = new ArrayList<Coordinate>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/pso-packages.txt"));

            String line;
            while((line = br.readLine()) != null) {
                String[] tmp = line.split(",");
                particles.add(new Coordinate(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1])));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return particles;
    }

    public static void main(String[] args) {
        List<Coordinate> a = readInput();
        System.out.println(a);
    }
}
