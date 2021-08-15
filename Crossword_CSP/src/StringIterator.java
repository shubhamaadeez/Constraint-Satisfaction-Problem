import java.util.Iterator;

//USED N CROSSWORD 

public class StringIterator implements Iterator<String>
{
  private String s;
  
  public StringIterator(String s)
  {
    this.s = s;
  }
  
  public boolean hasNext()
  {
    return s.length() > 0;
  }
  
  public String next()
  {
    String temp = s.substring(0,1);
    s = s.substring(1);
    return temp;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("do not implement this method");
  }
}
