public class Cell
{
  private boolean black;
  private String value;
  
  public Cell(boolean black)
  {
    this.black = black;
    value = "-";
  }
  
  public boolean getBlack()
  {
    return black;
  }
  
  public void setValue(String s)
  {
    value = s;
  }
  
  public String getValue()
  {
    return value;
  }
}