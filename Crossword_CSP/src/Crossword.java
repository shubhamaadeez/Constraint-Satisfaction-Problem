import java.util.ArrayList;

//text file must be bordered by black squares
//INITIALIZE
public class Crossword
{
  private Cell[][] map;
  private ArrayList<String> dictionary;
  private ArrayList<Move> possibleMoves;
  private GUI g;
  public static long startingTime = System.currentTimeMillis(); 
	
  
  public static void main(String[] args)
  {
    Crossword c = new Crossword();
    System.out.println(c.solve());
  }
  public static void printElapsedTime() {
		System.out.println( ((double)(System.currentTimeMillis()-startingTime))/1000+"s" );
	}
  public Crossword()
  {
    map = null;
    load("two.txt");
    dictionary = new ArrayList<String>();
    loadDict("words.txt");
    possibleMoves = new ArrayList<Move>();
    findPossibleMoves();
    g = new GUI(map);
  }
  
  public boolean solve()
  {
   if (solved())
   {
     print2DArray();
     return true;
   }
   else
   {
     possibleMoves = new ArrayList<Move>();
     findPossibleMoves();
     Move m = findMostConstrained();
     if (m != null)
     {
       for (String word : dictionary)
       {
         boolean b = true;
         //NO DUPLICATES
         for (Move move : possibleMoves)
         {
           if (move.getWord().equals(word))
           {
             b = false;
           }
         }
         if (b && m.getLength() == word.length() && m.fits(word))
         {
           //System.out.println(word);
           m.write(word);
           //print2DArray();
           //System.out.println("///////");
           g.update(map);
           if (solve())
           {
             return true;
           }
           else
           {
             m.undo();
             g.update(map);
             //print2DArray();
           }
         }
       }
     }
     return false;
   }
  }
  
  public void findPossibleMoves()
  {
    for (int r = 1; r < map.length; r++)
    {
      for (int c = 1; c < map[0].length; c++)
      {//ROW
        if (!map[r][c].getBlack() && map[r][c - 1].getBlack()) // IF THE VARIABLE NOT BLACK
        {
          int counter = 0;
          Cell temp = map[r][c];
          ArrayList<Cell> cellsToMove = new ArrayList<Cell>();  
          while (!temp.getBlack())
          {
            counter++;
            cellsToMove.add(temp);
            temp = map[r][c+counter]; // THEN COUNTER THE VALUES
          }
          if (cellsToMove.size() > 1)
            possibleMoves.add(new Move(cellsToMove));
        }//COLUMNS
        if (!map[r][c].getBlack() && map[r - 1][c].getBlack())
        {
          int counter = 0;
          Cell temp = map[r][c];
          ArrayList<Cell> cellsToMove = new ArrayList<Cell>();
          while (!temp.getBlack())
          {
            counter++;
            cellsToMove.add(temp);
            temp = map[r+counter][c];
          }
          if (cellsToMove.size() > 1)
            possibleMoves.add(new Move(cellsToMove));
        }
      }
    }
  }
  
  public Move findMostConstrained()
  {
    int mostConstrainedNumber = Integer.MAX_VALUE;
    Move mostConstrainedMove = null;
    for (Move m : possibleMoves)
    {
      String s = m.getChars();
      int posAnswers = compareToDict(s);
      //posAnswers != 1 is probably wrong here
      if (posAnswers != 0 && posAnswers < mostConstrainedNumber)
      {
        if (!m.filled())
        {
          mostConstrainedMove = m;
          mostConstrainedNumber = posAnswers;
        }
      }
      else if (posAnswers == 0)
      {
        return null;
      }
    }
    if (mostConstrainedMove == null)
    {
      return possibleMoves.get(0);
    }
    return mostConstrainedMove;
  }
  
  public int compareToDict(String s)
  {
    boolean b = true;
    int count = 0;
    for (int j = 0; j < dictionary.size(); j++)
    {
      b = true;
      String word = dictionary.get(j);  //ENTERING VALUES 
      if (word.length() == s.length())
      {
        for (int i = 0; i < s.length(); i++)
        {
          if (word.substring(i,i+1).equals(s.substring(i,i+1)))
          {
          }
          else if (s.substring(i,i+1).equals("-"))
          {
          }
          else
          {
            b = false;
          }
        }
        if (b)
        {
          count++;
        }
      }
    }
    return count;
  }
  public boolean solved()
  {
    for (int r = 0; r < map.length; r++)
    {
      for (int c = 0; c < map[0].length; c++)
      {
        if (!map[r][c].getBlack() && map[r][c].getValue().equals("-"))
          return false;
      }
    }
    Move n = findMostConstrained();
    if (n == null)
    {
      return false;
    }
    return true;
  }
  
  
  public void createRow(int r, int c)
  {
    int tempR = r;
    
    while (!map[tempR][c].getBlack())
    {
      
      tempR++;
    }
  }
  
  public void loadDict(String mapFileName)
  {
    while (Util.hasMoreLines(mapFileName))
      dictionary.add(Util.readLine(mapFileName));
    Util.close(mapFileName);
  }
  
  public void load(String mapFileName)
  {
    ArrayList<String> lines = new ArrayList<String>();
    while (Util.hasMoreLines(mapFileName))
      lines.add(Util.readLine(mapFileName));
    Util.close(mapFileName);
    
    int numRows = lines.size();
    int numCols = lines.get(0).length();
    
    map = new Cell[numRows][numCols];
    
    for (int row = 0; row < numRows; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        if (lines.get(row).length() != numCols)
          throw new RuntimeException("Inconsistent line length in map file \"" + mapFileName + "\"");
        
        char ch = lines.get(row).charAt(col);
        if (ch == 'X')
          map[row][col] = new Cell(true);
        else if (ch == '.')
          map[row][col] = new Cell(false);
        else
          throw new RuntimeException("Invalid character '" + ch + "' in map file \"" + mapFileName + "\"");
      }
    }
  }
  
    public void print2DArray()
  {
    for(int i = 0; i < map.length; i++)
    {
      for(int j = 0; j < map[0].length; j++)
      {
        if (map[i][j].getBlack())
          System.out.print("X ");
        else
          System.out.print(map[i][j].getValue() + " ");
      }
      System.out.println();
    }
    System.out.print("Backtracking time:");
    printElapsedTime();
    System.out.print("Forward Search time:");
    forward.printElapsedTime();

   }
  }
