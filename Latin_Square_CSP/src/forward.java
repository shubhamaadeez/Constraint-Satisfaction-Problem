import java.util.*;

public class forward {
    private final int DEFAULT_VALUE = 0;
    private final int N;
    private int numberOfRecur;
    private int numberOfReturns;
    private ArrayList<Integer> board;

    public forward(int n) {
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

    private ArrayList<Integer> initialiseEmptyBoard() {
        ArrayList<Integer> board = new ArrayList<Integer>(N*N);
        for(int i = 0; i < N*N; i++){
            board.add(DEFAULT_VALUE);
        }
        return board;
    }

    private ArrayList<Integer> initialiseAllValues() {
        ArrayList<Integer> board = new ArrayList<Integer>(N);
        for(int i = 1; i <= N; i++){     // entering values to the board array from 1 to N
            board.add(i);
        }
        return board;
    }

    List<List<Integer>> initialiseDomains() {
        List<List<Integer>> domains = new ArrayList<>();
        for (int i = 1; i <= N * N; i++) {
            domains.add(initialiseAllValues());    
        }
        return domains;
    }

    public void go() {
        goRecoursive(board, initialiseDomains(), -1);
    }

    private void goRecoursive(ArrayList<Integer> board, List<List<Integer>> domains, int index) {
        numberOfRecur++;
        if (board.contains(0)) {
            List<List<Integer>> newDomain = getDomains(board, domains, index);
            if (!isEmptyDomain(board, newDomain)) {
            	int indexOf0 = board.indexOf(0);
                List<Integer> domainList = newDomain.get(indexOf0);
                for(int i = 0; i < domainList.size(); i++){
                    ArrayList<Integer> newSolution = putNextVal(board, domainList.get(i), indexOf0);
                    goRecoursive(newSolution, newDomain, indexOf0);
                }
            }
            else {
                numberOfReturns++;
            }
        }
    }

    private ArrayList<Integer> putNextVal(ArrayList<Integer> board, int domain, int indexOf0) {
        ArrayList<Integer> newBoard = new ArrayList<Integer>(board);
        newBoard.set(indexOf0, domain);
        return newBoard;
    }

    private boolean isEmptyDomain(List<Integer> board, List<List<Integer>> domains) {
        for (int i = 0; i < domains.size(); i++) {
            if (board.get(i) == 0 && domains.get(i).size() == 0) {
                return true;
            }
        }
        return false;
    }

    List<List<Integer>> getDomains(List<Integer> board, List<List<Integer>> domains, int lastValIndex) {
    	HashSet<Integer> values = new HashSet<>(board);
        if (values.size() == 1 && values.contains(0)) { 
            return domains;
        }
       
        List<List<Integer>> copyDomains = new ArrayList<>();
        for(int i = 0; i < domains.size(); i++) {
            copyDomains.add(new ArrayList<>(domains.get(i)));
        }
        //delete whole domain for lastValueIndex
        copyDomains.get(lastValIndex).clear();
        //row
        Integer lastValue = board.get(lastValIndex);
        int firstIndexOfRow = lastValIndex - lastValIndex % N;
        int firstIndexOfNextRow = N - lastValIndex % N + lastValIndex;
        List<List<Integer>> domainsRow = copyDomains.subList(firstIndexOfRow, firstIndexOfNextRow);
        for(int i = 0; i < domainsRow.size(); i++) {
            domainsRow.get(i).remove(lastValue);
        }
        //col
        for (int i = 0; i < N; i++) {
            int columnVariableIndex = lastValIndex % N + i * N;
            copyDomains.get(columnVariableIndex).remove(lastValue);
        }
        return copyDomains;
    }

    List<List<Integer>> copyList2D(List<List<Integer>> list2D) {
        List<List<Integer>> copy = new ArrayList<>();
        for(int i = 0; i < list2D.size(); i++) {
            copy.add(new ArrayList<>(list2D.get(i)));
        }
        return copy;
    }
}
