package shelters;

import animal.Animal;
import animal.Dog;

public class SurreyShelter extends LowerMainlandShelter {

    public SurreyShelter(){
        this.location = "SurreyShelter";
        Animal a = new Dog("a", "breedA",3,false,this);
        animals.add(a);
    }
}
