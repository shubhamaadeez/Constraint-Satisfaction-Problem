import java.awt.event.*;  //for ActionListener, ActionEvent
import javax.swing.*;  //for JFrame, BoxLayout, JLabel, JTextField, JButton
import java.awt.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.border.Border;

// FOR THE BLACK AND WHITE SQUARES
public class GUI implements ActionListener
{
  private JTextField field;
  private JFrame frame;
  private JLabel[][] all;
  
  public GUI(Cell[][] cells)
  {
    int numRows = cells.length;
    int numCols = cells[0].length;
    
    all = new JLabel[numRows][numCols];
//make a window
    frame = new JFrame();
    frame.setTitle("Crossword");
    
//tell window to place each new component under the previous ones
    frame.getContentPane().setLayout(new GridLayout(numRows, numCols));
    
    //Border thingy
    Border border = BorderFactory.createLineBorder(Color.black);
//add some text
    for (int r = 0; r < numRows; r++)
    {
      for (int c = 0; c < numCols; c++)
      {
        if (cells[r][c].getBlack())
        {
          JLabel j = new JLabel(" ");
          j.setOpaque(true);
          j.setBackground(Color.BLACK);
          all[r][c] = j;
          frame.getContentPane().add(j);
        }
        else
        {
          if (cells[r][c].getValue().equals("-"))
          {
            JLabel j = new JLabel(" ");
            j.setHorizontalAlignment(JLabel.CENTER);
            j.setBorder(border);
            //j.setOpaque(true);
            //j.setBackground(Color.WHITE);
            all[r][c] = j;
            frame.getContentPane().add(j);
          }
          else
          {
            JLabel j = new JLabel(cells[r][c].getValue());
            j.setHorizontalAlignment(JLabel.CENTER);
            j.setBorder(border);
            //j.setBackground(Color.WHITE);
            //j.setOpaque(true);
            all[r][c] = j;
            frame.getContentPane().add(j);
          }
        }
      }
    }
    
   
    frame.pack();  //determine best size for window
    frame.setVisible(true);  //show the window
  }
  
  public void update(Cell[][] map)
  {
    int numRows = map.length;
    int numCols = map[0].length;
    
    for (int r = 0; r < numRows; r++)
    {
      for (int c = 0; c < numCols; c++)
      {
        if (map[r][c].getBlack())
        {}
        else
        {
          if (map[r][c].getValue().equals("-"))
          {
            all[r][c].setText(" ");
          }
          else
          {
            all[r][c].setText(map[r][c].getValue());
          }
        }
      }
    }
    if (Math.random() < 0.000001)
    {
      try{ Thread.sleep(10);} catch (Exception e){}
    }
  }
  
  public void actionPerformed(ActionEvent e)
  {
//button was pressed
    if (e.getActionCommand().equals("button1"))
    {
//we now know which button was pressed
      System.out.println("text field contains:  " + field.getText());
    }
  }
}