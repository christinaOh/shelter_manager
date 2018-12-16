package worker;

import animal.Animal;
import observer.Observer;

public class Staff implements Observer {
    String name;

    public Staff(String name){
        this.name = name;
    }


    @Override
    public void update(Animal a) {
        System.out.println(a.name+ " has been added to the " + a.shelterLocation.location);
    }

    public void update2(Animal a){
        System.out.println(this.name+ "!" +a.name+ " needs to be fed!!!");
    }}
