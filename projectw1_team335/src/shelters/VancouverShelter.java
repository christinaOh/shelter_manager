package shelters;

import animal.Animal;
import animal.Dog;

public class VancouverShelter extends LowerMainlandShelter {

    public VancouverShelter(){

        this.location = "VancouverShelter";
        Animal b = new Dog("b", "breedB",2,false,this);
        animals.add(b);
    }
}
