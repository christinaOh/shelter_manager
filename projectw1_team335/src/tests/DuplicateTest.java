//package tests;
//
//import animal.Dog;
//import exception.Duplicate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AnimalTest;
//import shelters.LowerMainlandShelter;
//import shelters.SurreyShelter;
//import shelters.VancouverShelter;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DuplicateTest {
//        private LowerMainlandShelter testlistA;
//        private LowerMainlandShelter testlistB;
//        private String fm = "exception should not be thrown here";
//        private String sm = "exception Successfully thrown";
//
//        @BeforeEach
//        public void setup() throws IOException, Duplicate {
//            testlistA = new SurreyShelter();
//            testlistB = new VancouverShelter();
//
//            Dog a = new Dog("A","A",0,true, new SurreyShelter());
//            Dog b = new Dog("B", "B",0, true, new VancouverShelter());
//
//            testlistA.addDog(a);
//            testlistB.addDog(b);
//        }
//
//        @AnimalTest
//        public void NoExceptionThrownA() throws IOException{
//            Dog dog = new Dog("Dog", "Dog",0, true, new SurreyShelter());
//            try{
//                testlistA.addDog(dog);
//            }catch (Duplicate duplicate){
//                fail(fm);
//            }
//        }
//
//        @AnimalTest
//        public void NoExceptionThrownB() throws IOException{
//            Dog dog = new Dog("Dog", "Dog",0, true, new VancouverShelter());
//            try{
//                testlistA.addDog(dog);
//            }catch (Duplicate duplicate){
//                fail(fm);
//            }
//        }
//
//        // Add a dog in listB that exists in listA - exception should not be thronw
//        @AnimalTest
//        public void NoExceptionThrownAB() throws IOException{
//            Dog dog = new Dog("A","A",0,true, new VancouverShelter());
//            try{
//                testlistB.addDog(dog);
//            }
//            catch (Duplicate duplicate){
//                fail(fm);
//            }
//        }
//
//        @AnimalTest
//        public void ExceptionThrownA() throws IOException {
//            Dog doga = new Dog("A", "A", 0, true, new SurreyShelter());
//            try {
//                testlistA.addDog(doga);
//            } catch (Duplicate duplicate) {
//                System.out.println(sm);
//            }
//        }
//
//        @AnimalTest
//        public void ExceptionThrownB() throws IOException{
//            Dog dogb = new Dog("B","B", 0,true, new VancouverShelter());
//            try{
//                testlistB.addDog(dogb);
//            }catch (Duplicate duplicate){
//                System.out.println(sm);
//            }
//        }
//
//        @AnimalTest
//        public void testTime() {
//            LocalTime t1 = LocalTime.parse("11:00");
//            LocalTime t2 = LocalTime.parse("17:00");
//
//            t2=t2.minusHours(6); // then t2 would be 9:00
//            System.out.println(t2);
//            assertFalse(t2.isAfter(t1));
//            assertTrue(t2.equals(t1));
//
//            LocalDateTime dt1 = LocalDateTime.parse("2007-12-03T10:15:30");
//            System.out.println(dt1);
//
//            LocalDateTime dateTime = LocalDateTime.now();
//            // 'yyyy-MM-dd KK:mm:ss a' pattern
//            System.out.println(dateTime.format(
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//
//
//            LocalDateTime currentTime = LocalDateTime.of(2018,1,13,12,00);
//            LocalDateTime lastFed = LocalDateTime.of(2018,1,12,12,00);
//
//
//            System.out.println(currentTime.minusHours(lastFed.getHour()).getHour());
//
//
//
//        }
//
//    }


