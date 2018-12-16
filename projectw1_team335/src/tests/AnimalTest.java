package tests;

import animal.Animal;
import animal.Dog;
import exception.Duplicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shelters.LowerMainlandShelter;
import shelters.VancouverShelter;
import worker.Staff;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    private Animal a;
    private Staff w = new Staff("john");
    private LowerMainlandShelter sh  =new VancouverShelter();

    @BeforeEach
    public void setUp(){
        sh.addWorker(w);
        a =  new Dog("test", "test", 2, false, sh);
    }

    @Test
    public void testNeedToBeFed(){
        a.updateLastFed2();
        System.out.println(a.lastFed);
        assertTrue(a.needToBeFed());
    }

    @Test
    public void testAddAnimal() throws IOException, Duplicate {
        Animal b= new Dog("test2", "test2", 3, true, new VancouverShelter());
        sh.addAnimal(b);
    }



}
