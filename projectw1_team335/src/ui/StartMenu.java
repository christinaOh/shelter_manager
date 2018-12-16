package ui;

import shelters.LowerMainlandShelter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartMenu {
    //  PANELS:
    private JPanel panel;
    //  FRAME:
    private JFrame mainFrame;
    //  COMPONENTS:
    public JLabel headerLabel;

    public StartMenuOptions startMenuOptions;
    public int selectedOption;
    public LowerMainlandShelter location;


    public StartMenu(LowerMainlandShelter location, JFrame mainFrame) {
        this.location = location;
        prepareGUI(mainFrame);
        startMenuDisplay();
        startMenuOptions = new StartMenuOptions(location, mainFrame);
    }


    public void prepareGUI(JFrame curr) {
        this.mainFrame = curr;

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 2));

        mainFrame.setContentPane(panel);
        mainFrame.setVisible(true);
    }


    public void startMenuDisplay() {
        headerLabel = new JLabel("", JLabel.CENTER);
        headerLabel.setText("Select one of the following options: ");
        panel.add(headerLabel);

        JButton viewButton = new JButton();
        viewButton.setText("View All Animals");
        panel.add(viewButton);
        JButton addButton = new JButton("Add New Animal");
        panel.add(addButton);
        JButton searchButton = new JButton("Search Animal");
        panel.add(searchButton);
        JButton exitButton = new JButton("Exit");
        panel.add(exitButton);

        mainFrame.setVisible(true);


        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.remove(panel);
                startMenuOptions.prepareGUI(mainFrame);
                startMenuOptions.showAnimals(location.getAllAnimalInfo());

            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.remove(panel);
                try {
                    startMenuOptions.ShowAddNewAnimal();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.remove(panel);
                startMenuOptions.searchAnimal();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}











