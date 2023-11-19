package Sanda;

import Sanda.model.Subscriber;
import Sanda.repository.SubscriberCrudOperations;

import java.util.List;

public class SubscriberTest {
    public static void test(){
        SubscriberCrudOperations subscriberCrudOperations = new SubscriberCrudOperations();

        //Find all test
        List<Subscriber> subscribers = subscriberCrudOperations.findAll();
        subscribers.forEach(el -> System.out.println("[FIND]: "  + el));

        //delete test
        subscribers.forEach(el -> System.out.println("[DELETED]: " + subscriberCrudOperations.delete(el)));
        System.out.println(subscriberCrudOperations.findAll());

        //save test
        Subscriber subscriber1 = new Subscriber(null, "Subscriber1", "ref");
        System.out.println("[CREATE]: " + subscriberCrudOperations.save(subscriber1));

        //saveAll test
        Subscriber subscriber2 = new Subscriber(null, "Subscriber2", "ref5");
        Subscriber subscriber3 = new Subscriber(null, "Subscriber3", "ref8");
        subscriberCrudOperations.saveAll(List.of(subscriber2, subscriber3)).forEach(el -> System.out.println("[CREATE]: " + el));

        //Last find
        List<Subscriber> subscribersTwo = subscriberCrudOperations.findAll();
        subscribersTwo.forEach(el -> System.out.println("[FIND]: "  + el));
    }
}
