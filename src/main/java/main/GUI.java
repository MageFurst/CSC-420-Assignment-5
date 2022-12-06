package main;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TooManyListenersException;
import java.util.concurrent.ExecutionException;

public class GUI {

    int currentx;
    int currenty;

    boolean crosshair = true;

    String lastcountry;

    public void loadGUI() throws ExecutionException, InterruptedException, TooManyListenersException {

        ArrayList<String> countries = new CountriesMap().countries;

        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel holder = new JPanel();
        holder.setLayout(new MigLayout("align 50%"));
        JPanel selectsection = new JPanel();
        selectsection.setLayout(new MigLayout("bottom,center"));
        JPanel loadingbar = new JPanel();
        loadingbar.setLayout(new MigLayout("align 50% 50%"));

        JPanel flagsection = new JPanel();
        flagsection.setLayout(new BorderLayout());
        flagsection.setPreferredSize(new Dimension(25000,25000));

        JProgressBar jpb = new JProgressBar();
        JLabel loadlabel = new JLabel();
        jpb.setIndeterminate(false);
        jpb.setMaximum(countries.size());

        loadingbar.add(jpb, "wrap");
        loadingbar.add(loadlabel,"center");
        frame.add(loadingbar);

        ImageLoaderWorker imageworker = new ImageLoaderWorker(countries, jpb, loadlabel, holder);
        imageworker.execute();

        frame.setVisible(true);

        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon());

        DefaultBoundedRangeModel vert = new DefaultBoundedRangeModel();
        DefaultBoundedRangeModel horiz = new DefaultBoundedRangeModel();
        vert.setMaximum((int) (frame.getHeight() * .675));
        vert.setMinimum(15);
        horiz.setMaximum((int) (frame.getWidth() * .96));
        horiz.setMinimum(15);

        JSlider top = new JSlider(horiz);
        JSlider bottom = new JSlider(horiz);

        JSlider left = new JSlider(SwingConstants.VERTICAL);
        left.setInverted(true);
        left.setModel(vert);
        JSlider right = new JSlider(SwingConstants.VERTICAL);
        right.setInverted(true);
        right.setModel(vert);

        JLabel applicationtitle = new JLabel("Eurasia + Oceania Flag Selection Menu");
        applicationtitle.setFont(new Font("",Font.BOLD,28));
        JLabel countryhelp = new JLabel("Drag & drop from the top menu into the area between the sliders. Use the sliders to change the image's position.");

        JList countrieslist = new JList(countries.toArray());
        countrieslist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        countrieslist.setDragEnabled(true);

        //flagsection.add(picture);
        flagsection.add(left,BorderLayout.WEST);
        flagsection.add(top, BorderLayout.NORTH);
        flagsection.add(bottom,BorderLayout.SOUTH);
        flagsection.add(right,BorderLayout.EAST);

        HashMap<String,BufferedImage> result = (HashMap)imageworker.get();

        top.addChangeListener( e -> {
            if(crosshair){
                handleCrosshair(top, left, flagsection);
            }
            else {
                handleImage(top, left, flagsection, result, lastcountry);
            }
        });

        left.addChangeListener( e -> {
            if(crosshair){
                handleCrosshair(top, left, flagsection);
            }
            else {
                handleImage(top, left, flagsection, result, lastcountry);
            }
        });

        DropTargetListener dtl = new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {}
            @Override
            public void dragOver(DropTargetDragEvent dtde) {}
            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {}
            @Override
            public void dragExit(DropTargetEvent dte) {}
            @Override
            public void drop(DropTargetDropEvent dtde) {
                crosshair = false;
                lastcountry = countrieslist.getSelectedValue().toString();
                System.out.println(lastcountry);
                handleImage(top, left, flagsection, result, lastcountry);
            }
        };

        DropTarget dt = new DropTarget();
        flagsection.setDropTarget(dt);
        dt.addDropTargetListener(dtl);

        selectsection.add(applicationtitle, "center,span");
        selectsection.add(countryhelp, "center,span");
        selectsection.add(countrieslist, "center,span");
        holder.add(selectsection,"span");
        holder.add(flagsection,"center,span");
        handleCrosshair(top, left, flagsection);
        frame.add(holder);
        holder.setVisible(false);
        frame.setVisible(true);

    }

    void handleCrosshair(JSlider horiz, JSlider vert, JPanel jpane){
        currentx = horiz.getValue();
        currenty = vert.getValue();
        Crosshair c = new Crosshair(currentx,currenty);
        jpane.add(c);
        jpane.revalidate();
    }

    void handleImage(JSlider horiz, JSlider vert, JPanel jpane, HashMap<String,BufferedImage> result, String countrytext){
        currentx = horiz.getValue();
        ImageIcon flag = new ImageIcon(result.get(countrytext));
        currenty = vert.getValue();
        CountryImage c = new CountryImage(flag,currentx - (flag.getIconWidth()/2),currenty - (flag.getIconHeight()/2));
        jpane.add(c);
        jpane.revalidate();
    }


}
