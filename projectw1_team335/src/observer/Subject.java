package observer;

import animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer ob){
       if(!observers.contains(ob)){
           observers.add(ob);
       }
    }

    public void notifyObservers(Animal a){
        for (Observer ob : observers){
            ob.update(a);
        }
    }

    public void notifyObservers2(Animal a){
        for (Observer ob : observers){
            ob.update2(a);
        }
    }
}
