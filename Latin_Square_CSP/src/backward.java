import java.util.*;

public class backward{
    private final int DEFAULT_VALUE = 0;
    private final int N;     // domain 1-N
    private int numberOfRecur;
    private int numberOfReturns;
    private ArrayList<Integer> board; // variable

    public backward(int n) {
        this.N = n;
        this.numberOfRecur = 0;
        this.numberOfReturns = 0;
        this.board = initialiseEmptyBoard();
    }

    public int getNumberOfRecur() {
        return numberOfRecur;
    }

    public int getNumberOfReturns() {
        return numberOfReturns;
    }
    //initialize
    private ArrayList<Integer> initialiseEmptyBoard() {
        ArrayList<Integer> board = new ArrayList<Integer>(N*N); 
        for(int i = 0; i < N*N; i++){
            board.add(DEFAULT_VALUE);
        }
        return board;
    }
    // 
    private boolean isSafe(List<Integer> board) {
        HashSet<Integer> values = new HashSet<>(board);  
        if (values.size() == 1 && values.contains(0)) { 
            return true;
        }
        boolean isOk = true;
        for (int i = 0; i < N && isOk; i++) {
            List<Integer> tmpRow = board.subList(i * N, N * (i + 1));
            if (!uniqueArrayValuesNoDefault(tmpRow)) {
                isOk = false;
            }
            List<Integer> tmpCol = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                tmpCol.add(board.get(i + N * j));
            }
            if (!uniqueArrayValuesNoDefault(tmpCol)) {
                isOk = false;
            }
        }
        return isOk;
    }

    private boolean uniqueArrayValuesNoDefault(List<Integer> list) {
        ArrayList<Integer> parsedlist = new ArrayList<Integer>();
        for(int i = 0; i < N; i++) {
            if(!list.get(i).equals(DEFAULT_VALUE)) { //check if we get the first value from the array
                parsedlist.add(list.get(i));
            }
        }
        return new HashSet<>(parsedlist).size() == parsedlist.size(); //new set of values 
    }

    public void go() {
        goRecoursive(board);
    }

    private void goRecoursive(ArrayList<Integer> board) { //applying constraints
        numberOfRecur++;   // counter for the operations 
        if (isSafe(board)) {  // if the size is valid 
            if (board.contains(0))        
            {          for( int i = 1; i <= N; i++){
                    ArrayList<Integer> newSolution = putNextVal(board, i); 
                    goRecoursive(newSolution);                }
            }
        }
        else {
            numberOfReturns++;
        }
    }

    private ArrayList<Integer> putNextVal(ArrayList<Integer> board, int val) {
        int index = board.indexOf(0);  
        ArrayList<Integer> newBoard = new ArrayList<>(board);
        newBoard.set(index, val);
        return newBoard;
    }

    public void print(ArrayList<?> board) {
        for(int i = 1; i<= board.size(); i++) {
            System.out.print(board.get(i-1) +" ");
            if(i%N == 0){
                System.out.println();
            }
        }
        System.out.println();
    }
}
