package shelters;

import animal.Animal;
import animal.Cat;
import animal.Dog;
import animal.SmallAnimal;
import exception.Duplicate;
import observer.Subject;
import worker.Staff;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.awt.*;

public abstract class LowerMainlandShelter extends Subject{
    protected List<Animal> animals = new ArrayList<>();
    protected List<Staff> staff = new ArrayList<>();
    protected Animal animalToGet;
    public String location;

    protected List<String> lines;
    protected ArrayList<String> partsOfLine;

    public LowerMainlandShelter(){}
    


    //MODIFIES: the list SurreyShelter
    //EFFECTS: creates a new dog and adds that dog to the list of SurreyShelter
    public void addAnimal(Animal a) throws IOException, Duplicate {
        if (animals.contains(a))
            throw new Duplicate();
        else {
            animals.add(a);
            notifyObservers(a);
        }
    }


    //MODIFIES: nothing
    //EFFECTS: prints name, breed, age, status of the dog of the given name or prints "Sorry " + dog.name + "could not be found."
    //         if name is not in the listofdogs
    public void printAnimalInfo(String name) {
        if (!contains(name)) {
            System.out.println("Sorry " + name + "could not be found.");
        } else {
            for (Animal a: animals) {
                if (a.name.equals(name)) {
                    System.out.println("\nName: " + a.name);
                    System.out.println("Breed: " + a.breed);
                    System.out.println("Age: " + a.age);
                    System.out.println("Status: " + a.status);
                }
            }
        }
    }


    //EFFECTS: prints the name, breed, age and status of all dogs in dogs
    public void printAllAnimals() throws IOException {
        for (Animal a : animals) {
            System.out.println("\nName: " + a.name);
            System.out.println("Breed: " + a.breed);
            System.out.println("Age: " + a.age);
            System.out.println("Status: " + a.status);
        }

        System.out.println("");


    }

    public JList getAllAnimalInfo() {
        DefaultListModel listModel = new DefaultListModel();
        for (Animal a : animals) {
            if (a instanceof Cat)
            listModel.addElement("\nName:"+ a.name + " Breed:"+ a.breed + " Age:"+ a.age+ "-- CAT");

            else if (a instanceof Dog){
                listModel.addElement("\nName:"+ a.name + " Breed:"+ a.breed + " Age:"+ a.age+ "-- DOG");
            }

            else listModel.addElement("\nName:"+ a.name + " Breed:"+ a.breed + " Age:"+ a.age+ "-- SMALL ANIMAL");
        }
        JList list = new JList(listModel);
        return list;
    }
    //EFFECTS: print the name of all dogs in dogs
    public  List<String>  listOfAllNames() {
        List<String> alist = new ArrayList<>();
        for (Animal a : animals) {
            alist.add(a.name);
        }
        return alist;
    }

    public  List<String> listOfAllBreeds() {
        List<String> alist = new ArrayList<>();
        for (Animal a : animals) {
            alist.add(a.breed);
        }
        return alist;
    }

//    public  List<String>  listOfAllNames() {
//        List<String> alist = new ArrayList<>();
//        for (Animal a : animals) {
//            alist.add(a.name);
//        }
//        return alist;
//    }


    //MODIFIES: nothing
    //EFFECTS: returns true if a given name corresponds to one of the SurreyShelter in the list

    public boolean contains(String name) {
        for (Animal a : animals) {
            if (a.name.equals(name)) {
                return true;
            }
        }
        return false;

    }

    //MODIFIES: nothing
    //EFFECTS: returns the size of the listofdog
    public int listLength() {
        return animals.size();
    }


    //MODIFIES: a dog in listofDog
    //EFFECTS: updates the status of the dog to false (not available) to true (available) and vice versa. if dog
    //         cannot be found, does nothing.
    public void updateStatus(String name) throws IOException {
        for (Animal a : animals) {
            if (a.name.equals(name)) {
                a.setStatus(false);
            }
        }
    }

    //EFFECTS: returns a dog with the given name
    public JList getAnimal(String nameOrBreed) {
        DefaultListModel l = new DefaultListModel();
        for (Animal a : animals) {
            if (a.name.equals(nameOrBreed))
                l.addElement("\nName:" + a.name + " Breed:" + a.breed + " Age:" + a.age);
            else if (a.breed.equals(nameOrBreed)){
                l.addElement("\nName:" + a.name + " Breed:" + a.breed + " Age:" + a.age);
            }
        }
        JList list = new JList(l);
        return list;
    }

    public void removeAnimal(Animal a) {
        animals.remove(a);
    }

    public void feedWaterCleanAll(){
        for(Animal a: animals){
            a.updateLastFed();
            a.updateLastKennelClean();
            a.updateLastWaterChange();
        }
    }

    public void addWorker(Staff w){
        staff.add(w);
        addObserver(w);
    }

    public List<Staff> getStaff(){
        return staff;
    }

    public String returnStringOfAnimalSpecies(Animal a){
        if (a instanceof Cat)
            return "cat";
        else if(a instanceof Dog)
            return "dog";
        else return "smallAnimal";
    }

    public Animal makeAnimal(String species, String name, String breed, int age, boolean status, LowerMainlandShelter shelterlocation){
        if (species == "cat")
            return new Cat(name,breed,age,status,shelterlocation);
        else if(species == "dog")
            return new Dog(name,breed,age,status,shelterlocation);
        else return new SmallAnimal(name,breed,age,status,shelterlocation);
    }





    //TODO: fix
    // EFFECTS: save the name, age, breed, status and shelter location of all dogs in a given shelter to its corresponding file
    public void save(LowerMainlandShelter shelterLocation) throws IOException {
        String filename = "src/" + shelterLocation.location + ".txt";
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (Animal a : animals) {
            if (lines.contains("" + returnStringOfAnimalSpecies(a)+ " " + a.name + " " + a.age + " " + a.breed + " " + a.status + " " + a.shelterLocation+ " " + a.dateAdmitted + " " + a.lastWaterChange+ " " + a.lastFed+ " " + a.lastKennelClean));
            else lines.add("" + returnStringOfAnimalSpecies(a)+ " " + a.name + " " + a.age + " " + a.breed + " " + a.status + " " + a.shelterLocation+ " " + a.dateAdmitted + " " + a.lastWaterChange+ " " + a.lastFed+ " " + a.lastKennelClean);


        }

        for (String line : lines) {
            partsOfLine = splitOnSpace(line);
            writer.println(line);
        }
        writer.close(); //note -- if you miss this, the file will not be written at all.
    }

    //
    public void remove(LowerMainlandShelter shelterLocation, Animal a) throws IOException {
        String filename = "src/" + shelterLocation.location + ".txt";
        PrintWriter writer = new PrintWriter(filename, "UTF-8");

        Iterator<String> linesIterator = lines.iterator();
        while(linesIterator.hasNext()){
            if (linesIterator.next().contains(a.name)){
                linesIterator.remove();
            }
        }

//        List<String> lineToRemove = new ArrayList<>();
//        for (String line : lines) {
//            if (!(lines.contains(line))){
//                lineToRemove.add(line);
//            }
//        }
//        lines.removeAll(lineToRemove);
    }


    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void load(LowerMainlandShelter shelterLocation) throws IOException, Duplicate {
        String fileToGet = "src/" + shelterLocation.location + ".txt";
        lines = Files.readAllLines(Paths.get(fileToGet));

        for (String line : lines) {
            partsOfLine = splitOnSpace(line);
            Integer age = Integer.parseInt(partsOfLine.get(2));
            Boolean status = Boolean.parseBoolean(partsOfLine.get(4));
            String species = partsOfLine.get(0);

            Animal a = makeAnimal(species,partsOfLine.get(1), partsOfLine.get(4), age, status, shelterLocation);
            addAnimal(a);

            LocalDateTime dateAdmitted = LocalDateTime.parse(partsOfLine.get(6));
            LocalDateTime lastWaterChange = LocalDateTime.parse(partsOfLine.get(7));
            LocalDateTime lastFed = LocalDateTime.parse(partsOfLine.get(8));
            LocalDateTime lastKennelClean = LocalDateTime.parse(partsOfLine.get(9));

            a.dateAdmitted = dateAdmitted;
            a.lastWaterChange = lastWaterChange;
            a.lastFed= lastFed;
            a.lastKennelClean= lastKennelClean;
        }
    }
}







