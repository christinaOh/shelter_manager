package animal;

import observer.Subject;
import shelters.LowerMainlandShelter;
import worker.Staff;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Animal extends Subject{
    public String name;
    public String breed;
    public int age;
    public boolean status;
    public LowerMainlandShelter shelterLocation;

    public LocalDateTime dateAdmitted;

    public LocalDateTime lastFed;
    public LocalDateTime lastWaterChange;
    public LocalDateTime lastKennelClean;

    public int HOURS_BETWEEN_FEEDS = 8;

    public List<Staff> staff;


    //MODIFIES: this
    //EFFECTS: Constructs a dog initialising its name, breed, age, status.
    //         If the status of the dog is true, it is available for adoption
    //         If the status of the dog is false, it is not available for adoption

    public Animal(String name, String breed, Integer age, boolean status, LowerMainlandShelter shelterlocation) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.status = status;
        this.shelterLocation = shelterlocation;

        this.dateAdmitted = LocalDateTime.now();
        this.lastFed= LocalDateTime.now();
        this.lastKennelClean = LocalDateTime.now();
        this.lastWaterChange = LocalDateTime.now();

        this.staff = shelterlocation.getStaff();

        for (Staff w : staff){
            addObserver(w);
        }

        //this.dateAdmitted = new Date();

        //this.lastFed = new Date();
        //this.lastWaterChange
        //this.lastKennelClean = new LocalDateTime();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setLocation(LowerMainlandShelter shelterlocation) {
        this.shelterLocation = shelterlocation;
    }

    public LowerMainlandShelter getShelterLocation() {
        return shelterLocation;
    }

    public void updateLastFed() {
        lastFed = LocalDateTime.now();
    }

    public void updateLastWaterChange() {
       lastWaterChange = LocalDateTime.now();
    }

    public void updateLastKennelClean() {
        lastKennelClean = LocalDateTime.now();
    }

    public Boolean needToBeFed() {
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.minusHours(lastFed.getHour()).getHour() >= HOURS_BETWEEN_FEEDS) {
            notifyObservers2(this);
            return true;
            //System.out.println(name + "needs to be fed !!!");
        }
        return false;
    }


    public void updateLastFed2(){
        lastFed= LocalDateTime.now().minusHours(9);
    }





}
