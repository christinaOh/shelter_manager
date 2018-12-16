package ui;

import exception.Duplicate;
import shelters.LowerMainlandShelter;
import shelters.SurreyShelter;
import shelters.VancouverShelter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LocationSelector {
    LowerMainlandShelter location;
    //PANEL:
    private JPanel panel_locationSelector;
    //  FRAME:
    private JFrame mainFrame;
    //  COMPONENTS:
    public JLabel headerLabel;

    public LocationSelector() throws IOException, Duplicate {
        prepareGUI();
        LocationSelectorDisplay();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Shelter Manager");
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 1, 10, 2));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        headerLabel = new JLabel("", JLabel.CENTER);


        panel_locationSelector = new JPanel();
        panel_locationSelector.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(panel_locationSelector);
        mainFrame.setVisible(true);
    }

    private void LocationSelectorDisplay() {
        headerLabel.setText("Select your shelter location");

        JButton surreyButton = new JButton("Surrey Shelter");
        panel_locationSelector.add(surreyButton);

        JButton vancouverButton = new JButton("Vancouver Shelter");
        panel_locationSelector.add(vancouverButton);

        mainFrame.setVisible(true);


        surreyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location = new SurreyShelter();

                mainFrame.remove(panel_locationSelector);
                new StartMenu(location,mainFrame);
            }
        });

        vancouverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location = new VancouverShelter();

                mainFrame.remove(panel_locationSelector);
                new StartMenu(location,mainFrame);
            }
        });

    }

}
