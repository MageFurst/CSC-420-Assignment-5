package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class CountryImage extends JPanel {

    ImageIcon i;
    int x;
    int y;

    public CountryImage(ImageIcon i,int x, int y) {
        this.i = i;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        i.paintIcon(this,g,x,y);
    }
}
