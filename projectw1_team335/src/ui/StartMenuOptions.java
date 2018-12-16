package ui;

import animal.Animal;
import animal.Cat;
import animal.Dog;
import animal.SmallAnimal;
import exception.Duplicate;
import shelters.LowerMainlandShelter;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartMenuOptions {
    //  PANELS:
    private JPanel panel;
    private JPanel panel_boxLayout;
    private JPanel panel_gbl;
    //  FRAME:
    private JFrame mainFrame;
    //  COMPONENTS:
    public JLabel headerLabel;

    private JButton StartMenuButton;
    private JButton addAnotherAnimalButton;
    public LowerMainlandShelter location;
    JComboBox nameCombo;
    JComboBox breedCombo;
    JList match;



    public StartMenuOptions(LowerMainlandShelter location, JFrame mainFrame) {
        this.location = location;
        this.mainFrame = mainFrame;

        StartMenuButton = new JButton();
        StartMenuButton.setText("StartMenu");
        StartMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.remove(panel_boxLayout);
                prepareGUI(mainFrame);
                mainFrame.setContentPane(panel);

                new StartMenu(location, mainFrame);
            }
        });

        addAnotherAnimalButton = new JButton();
        addAnotherAnimalButton.setText("Add Another Animal");
        addAnotherAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel_gbl.removeAll();
                try {
                    ShowAddNewAnimal();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }


    public void prepareGUI(JFrame curr) {
        this.mainFrame = curr;

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        mainFrame.setContentPane(panel);
        mainFrame.setVisible(true);
    }

    public void prepareGUI_boxLayout(JFrame curr) {
        mainFrame = curr;

        headerLabel = new JLabel("", JLabel.CENTER);

        panel_boxLayout = new JPanel();
        panel_boxLayout.setBounds(61, 11, 81, 140);
        panel_boxLayout.setLayout(new BoxLayout(panel_boxLayout, BoxLayout.Y_AXIS));

        mainFrame.setContentPane(panel_boxLayout);
        mainFrame.add(headerLabel);
        mainFrame.setVisible(true);
    }


    public void showAnimals(JList list) {
        prepareGUI_boxLayout(this.mainFrame);
        headerLabel.setText("Animals currently in shelter:");
        panel_boxLayout.add(headerLabel);
        JScrollPane showAllAnimals = new JScrollPane(list);
        panel_boxLayout.add(showAllAnimals);
        panel_boxLayout.add(StartMenuButton);
        mainFrame.setVisible(true);
    }


    public void ShowAddNewAnimal() throws IOException {
        prepareGUI_boxLayout(mainFrame);

        headerLabel.setText("Add new: ");
        panel_boxLayout.add(headerLabel);

        final DefaultComboBoxModel animalTypes = new DefaultComboBoxModel();
        animalTypes.addElement("Dog");
        animalTypes.addElement("Cat");
        animalTypes.addElement("Small Animal");

        final JComboBox animalTypeCombo = new JComboBox(animalTypes);
        animalTypeCombo.setSelectedIndex(0);

        JScrollPane animalTypeScrollPane = new JScrollPane(animalTypeCombo);
        panel_boxLayout.add(animalTypeScrollPane);

        JButton selectButton = new JButton("Select");
        panel_boxLayout.add(selectButton);

        mainFrame.setVisible(true);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int animalType = animalTypeCombo.getSelectedIndex();
                panel_boxLayout.removeAll();
                mainFrame.remove(panel_boxLayout);
                panel_gbl = new JPanel();
                mainFrame.setContentPane(panel_gbl);
                mainFrame.setLayout(new GridBagLayout());
                addNewAnimal(animalType);
            }
        });
    }

    public void addNewAnimal(int animalType) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,50,5,50);

        JLabel nameLabel = new JLabel("Name: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel_gbl.add(nameLabel, gbc);

        JTextField nameText = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx=1;
        gbc.gridwidth=20;
        panel_gbl.add(nameText, gbc);


        JLabel breedLabel = new JLabel("Breed: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel_gbl.add(breedLabel, gbc);

        JTextField breedText = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx=1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel_gbl.add(breedText, gbc);


        JLabel ageLabel = new JLabel("Age: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
//        gbc.fill = GridBagConstraints.CENTER;
        panel_gbl.add(ageLabel, gbc);


        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setAllowsInvalid(false); //this is the key!!

        JFormattedTextField ageText = new JFormattedTextField(numberFormatter);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel_gbl.add(ageText, gbc);


        JButton addButton = new JButton("Add");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridheight= 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel_gbl.add(addButton, gbc);

        mainFrame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animalType == 0) {
                    Animal a = new Dog(nameText.getText(), breedText.getText(), ageText.getFocusLostBehavior(), false, location);
                    try {
                        location.addAnimal(a);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Duplicate duplicate) {
                        duplicate.printStackTrace();
                    }
                } else if (animalType == 1) {
                    Animal a = new Cat(nameText.getText(), breedText.getText(), ageText.getFocusLostBehavior(), false, location);
                    try {
                        location.addAnimal(a);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Duplicate duplicate) {
                        duplicate.printStackTrace();
                    }
                } else if (animalType == 2) {
                    Animal a = new SmallAnimal(nameText.getText(), breedText.getText(), ageText.getFocusLostBehavior(), false, location);
                    try {
                        location.addAnimal(a);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Duplicate duplicate) {
                        duplicate.printStackTrace();
                    }
                }
                mainFrame.remove(panel_gbl);
                mainFrame.setContentPane(panel_boxLayout);
                headerLabel.setText("Animals in shelter updated to:");
                panel_boxLayout.add(headerLabel);
                panel_boxLayout.add(new JScrollPane(location.getAllAnimalInfo()));
                panel_boxLayout.add(StartMenuButton);
                panel_boxLayout.add(addAnotherAnimalButton);
                mainFrame.setVisible(true);
            }

        });

    }


    public void searchAnimal() {
        prepareGUI_boxLayout(mainFrame);
        headerLabel.setText("Search animal by: ");

        final DefaultComboBoxModel searchFilter = new DefaultComboBoxModel();
        searchFilter.addElement("Name");
        searchFilter.addElement("Breed");
//        searchFilter.addElement("Age");

        final JComboBox filterCombo = new JComboBox(searchFilter);
        filterCombo.setSelectedIndex(0);

        JScrollPane SearchFilterScrollPane = new JScrollPane(filterCombo);
        JButton filterButton = new JButton("Search");

        panel_boxLayout.add(headerLabel);
        panel_boxLayout.add(SearchFilterScrollPane);
        panel_boxLayout.add(filterButton);
        mainFrame.setVisible(true);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel_boxLayout.removeAll();
                int selectedIndex =filterCombo.getSelectedIndex();

                //show combobox of names
                if (selectedIndex == 0) {
                    nameFilter();
                    JButton searchButton = new JButton("Search");
                    panel_boxLayout.add(searchButton);
                    mainFrame.setVisible(true);

                    searchButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e ) {
                            panel_boxLayout.remove(searchButton);
                            String animalToSearch = (String) nameCombo.getItemAt(nameCombo.getSelectedIndex());
                            match = location.getAnimal(animalToSearch);
                            JScrollPane j =new JScrollPane(match);
                            panel_boxLayout.add(j);
                            panel_boxLayout.add(StartMenuButton);
                            mainFrame.setVisible(true);
                        }
                    });

                //show comboBox of Breeds currently in shelter
                } else if (filterCombo.getSelectedIndex() == 1) {
                    breedFilter();
                    JButton searchButton = new JButton("Search");
                    panel_boxLayout.add(searchButton);
                    mainFrame.setVisible(true);

                    searchButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e ) {
                            panel_boxLayout.remove(searchButton);
                            String animalToSearch = (String) breedCombo.getItemAt(breedCombo.getSelectedIndex());
                            match = location.getAnimal(animalToSearch);
                            JScrollPane j =new JScrollPane(match);
                            panel_boxLayout.add(j);
                            panel_boxLayout.add(StartMenuButton);
                            mainFrame.setVisible(true);
                        }
                    });

                } else if (filterCombo.getSelectedIndex() == 2) {
                    //show comboBox of age intervals
                }
            }
        });
    }
    public List<String> checkForDupes(List<String> list) {
        Set<String> set = new HashSet<>(list);
        List<String> alreadySeen = new ArrayList<>();
        List<String> dupe = new ArrayList<>();

        if (set.size() < list.size()) {
            for (String s : list) {
                if (!alreadySeen.contains(s))
                    alreadySeen.add(s);
                else if (!dupe.contains(s)){
                    dupe.add(s);
                }
            }

        }
        return dupe;
    }

    public void nameFilter(){
        final DefaultComboBoxModel animalSearch = new DefaultComboBoxModel();
        List<String> alist = location.listOfAllNames();

        for (String aName : alist) {
            if (!checkForDupes(alist).contains(aName))
            animalSearch.addElement(aName);
        }

        for(String n : checkForDupes(alist)) {
            animalSearch.addElement(n);
        }


        nameCombo = new JComboBox(animalSearch);
        nameCombo.setSelectedIndex(0);

        JScrollPane AnimalSearchScrollPane = new JScrollPane(nameCombo);
        panel_boxLayout.add(AnimalSearchScrollPane);



        mainFrame.setVisible(true);

    }

    public void breedFilter(){
        final DefaultComboBoxModel animalSearch = new DefaultComboBoxModel();
        List<String> alist = location.listOfAllBreeds();

        for (String aBreed : alist) {
            if (!checkForDupes(alist).contains(aBreed))
                animalSearch.addElement(aBreed);
        }
        for(String n : checkForDupes(alist)) {
            animalSearch.addElement(n);
        }
        breedCombo = new JComboBox(animalSearch);
        breedCombo.setSelectedIndex(0);

        String animalToSearch = (String) breedCombo.getItemAt(breedCombo.getSelectedIndex());
        match = location.getAnimal(animalToSearch);

        JScrollPane AnimalSearchScrollPane = new JScrollPane(breedCombo);
        panel_boxLayout.add(AnimalSearchScrollPane);

        mainFrame.setVisible(true);
    }
}
