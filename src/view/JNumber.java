package view;

import javax.swing.*;

public class JNumber extends JLabel {
    public JNumber(){}
    public int num = 0;
    public void changNumberofEatenChess(){
        this.setText(String.format("* %d",this.num));
    }


}
