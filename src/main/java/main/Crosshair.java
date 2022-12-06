package main;

import javax.swing.*;
import java.awt.*;

public class Crosshair extends JPanel {

    int x,y;
    String color;

    public Crosshair(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect((x - 15),y,30,1);
        g.fillRect(x,(y - 15),1,30);
    }

}
