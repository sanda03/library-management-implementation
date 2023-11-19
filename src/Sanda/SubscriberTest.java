package Sanda;

import Sanda.model.Subscriber;
import Sanda.repository.SubscriberCrudOperations;

import java.util.List;

public class SubscriberTest {
    public static void test(){
        SubscriberCrudOperations subscriberCrudOperations = new SubscriberCrudOperations();

        //Find all test
        List<Subscriber> subscribers = subscriberCrudOperations.findAll();
        System.out.println("-".repeat(5)  +  " findAll test  for subscribers" + "-".repeat(5));
        subscribers.forEach(el -> System.out.println("[FIND]: "  + el));

        //delete test
        System.out.println( "\n" + "-".repeat(5)  +  " delete test  for subscribers" + "-".repeat(5));
        subscribers.forEach(el -> System.out.println("[DELETED]: " + subscriberCrudOperations.delete(el)));
        System.out.println(subscriberCrudOperations.findAll());

        //save test
        Subscriber subscriber1 = new Subscriber(null, "Subscriber1", "ref");
        System.out.println("\n" + "-".repeat(5)  +  " save test  for subscribers" + "-".repeat(5));
        System.out.println("[CREATE]: " + subscriberCrudOperations.save(subscriber1));

        //saveAll test
        Subscriber subscriber2 = new Subscriber(null, "Subscriber2", "ref5");
        Subscriber subscriber3 = new Subscriber(null, "Subscriber3", "ref8");
        System.out.println("\n" + "-".repeat(5)  +  " saveAll test  for subscribers" + "-".repeat(5));
        subscriberCrudOperations.saveAll(List.of(subscriber2, subscriber3)).forEach(el -> System.out.println("[CREATE]: " + el));

        //Last find
        System.out.println("\n" + "-".repeat(5)  +  " findAll test  for subscribers" + "-".repeat(5));
        List<Subscriber> subscribersTwo = subscriberCrudOperations.findAll();
        subscribersTwo.forEach(el -> System.out.println("[FIND]: "  + el));
    }
}
