import java.util.ArrayList;

// MOVING THE VALUES ACCORDING TO THE CONSTRAINTS

public class Move
{
  private ArrayList<Cell> cells;
  private ArrayList<Integer> lettersAdded;
  
  public Move(ArrayList<Cell> cells)
  {
    lettersAdded = new ArrayList<Integer>();
    this.cells = cells;
  }
  
  public int getLength()
  {
    return cells.size();
  }
  
  //pre: must be the same length
  public boolean fits(String word)
  {
    for (int i = 0; i < word.length(); i++)
    {
      if (!cells.get(i).getValue().equals(word.substring(i,i+1)) && !cells.get(i).getValue().equals("-"))
      {
        return false;
      }
    }
    return true;
  }
  
  public void write(String toWrite)
  {
    for (int i = 0; i < toWrite.length(); i++)
    {
      if (cells.get(i).getValue().equals("-"))
      {
        cells.get(i).setValue(toWrite.substring(i,i+1));
        lettersAdded.add(i);
      }
    }
  }
  
  //pre: must have written something
  public void undo()
  {
    for (int i = 0; i < lettersAdded.size(); i++)
    {
      cells.get(lettersAdded.get(i)).setValue("-");
    }
  }
  
  public String getChars()
  {
    String s = "";
    for (int i = 0; i < cells.size(); i++)
    {
      if (!cells.get(i).getValue().equals("-"))
        s = s + cells.get(i).getValue();
      else
        s = s + "-";
    }
    return s;
  }
  
  public String getWord()
  {
    if (!getChars().contains("-"))
      return getChars();
    else
      return "";
  }
  
  public boolean filled()
  {
    for (int i = 0; i < cells.size(); i++)
    {
      if (cells.get(i).getValue().equals("-"))
        return false;
    }
    return true;
  }
}