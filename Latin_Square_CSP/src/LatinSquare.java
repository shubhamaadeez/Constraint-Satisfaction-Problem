public class LatinSquare {
    int [][] latinSquere;
    int size;  
    
    public LatinSquare(int size){   
        this.size= size;
        latinSquere = new int [size][size];
    }

    public void initSquere(){
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                latinSquere[i][j] = 0;
            }
        }
    }

    public void printSquere(){
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                System.out.print("["+latinSquere[row][col] + "]");
            }
            System.out.println();
        }
    }
  // Check for empty spaces in row
    
    private boolean checkROW(int valToAdd, int y_pos){
        boolean isValid = true;
        for(int col= 0; col<size; col++){
            if(latinSquere[col][y_pos] == valToAdd) isValid = false;
        }
        return isValid;
    }
 // Check for empty spaces in column
    
    private boolean checkCOL(int valToAdd, int x_pos){
        boolean isValid = true;
        for(int row= 0; row<size; row++){
            if(latinSquere[x_pos][row] == valToAdd) isValid = false;
        }
        return isValid;
    }

    private boolean checkSAFE(int valToAdd, int x_pos, int y_pos){
        boolean isSafe = true;
        if(checkCOL(valToAdd,x_pos) == false || checkROW(valToAdd,y_pos) == false){
            isSafe=false;
        }
        return isSafe;
    }

    private void squereFinished(){
       System.out.println("Found this solution");
       printSquere();
    }

    private Position findNextEmpty() {
        for(int y= 0 ; y < size ; y++){
            for (int x = 0 ; x < size; x++){
                if(latinSquere[x][y] == 0 ) return new Position(x,y);
            }
        }
        return null;
    }
    public boolean backtracking(){
        Position nextEmpty = findNextEmpty();
        if(nextEmpty == null){
            squereFinished();
            return true;
        }
        for(int value = 1; value < size+1 ; value++ ){
            if(checkSAFE(value,nextEmpty.getX(),nextEmpty.getY())){
                latinSquere[nextEmpty.getX()][nextEmpty.getY()] = value;
                if(backtracking()) return true;
                latinSquere[nextEmpty.getX()][nextEmpty.getY()] = 0;
            }
        }
        return false;
    }

    
    public void run(){
        long start_time = System.currentTimeMillis();
        long end_time = System.currentTimeMillis();
        backtracking();    }

    public void printResearches(){
    	 
    	backward bt = new backward(size);
        long startTimeBt = System.currentTimeMillis();
        bt.go();
        double estimatedTimeBt = System.currentTimeMillis() - startTimeBt;
    	System.out.println("For size: "+size);
    	
    	System.out.println("                           BACKTRACK CHECKING                             ");
        System.out.println("Time is: "+estimatedTimeBt+ "ms");
        System.out.println("Operations: "+ bt.getNumberOfReturns());
        System.out.println("Number of nodes visited: "+ bt.getNumberOfRecur()+"\n");
       
        
		forward fc = new forward(size);
        long startTimeFc = System.currentTimeMillis();
        fc.go();
        double estimatedTimeFc = System.currentTimeMillis() - startTimeFc;

     System.out.println("                           FORWARD CHECKING                             ");
     System.out.println("Time is: "+ +estimatedTimeFc +"ms");
     System.out.println("Operations: "+fc.getNumberOfReturns());
     System.out.println("Number of nodes visited: "+ fc.getNumberOfRecur()+"\n");
    
 }
}

