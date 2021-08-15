import java.io.FileNotFoundException;

public class LatinMain {
    public static final int MAX_SIZE = 4;  // print until this size


    public static void main(String [] args) throws FileNotFoundException {
        for (int i = 2; i<=MAX_SIZE ; i++){     // generating squares
            LatinSquare latinSquare = new LatinSquare(i);
            latinSquare.initSquere();
            latinSquare.run(); //printing squares
            latinSquare.printResearches();
    }
   }
}
