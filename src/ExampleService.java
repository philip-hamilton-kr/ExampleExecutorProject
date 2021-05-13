import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExampleService {

    public ExecutorService executor = Executors.newFixedThreadPool(10);

    public PersonCityListObject getPersonCityInformationAsync(){
        PersonCityListObject pclo = new PersonCityListObject();
        System.out.println("ExampleService::getPersonCityInformationAsync | Submitting Futures");
        CompletableFuture<Void> personFuture =
                CompletableFuture.supplyAsync(() -> getPerson(), executor).thenAccept(pclo::setPerson);
        CompletableFuture<Void> citiesFuture =
                CompletableFuture.supplyAsync(() -> getCities(), executor).thenAccept(pclo::setCities);
        System.out.println("ExampleService::getPersonCityInformationAsync | Futures have been started, waiting for them to finish.");

        CompletableFuture.allOf(new CompletableFuture[]{personFuture, citiesFuture}).join();

        return pclo;
    }

    public Person getPerson() {
        System.out.println("ExampleService::getPerson | Started processing on thread: " + Thread.currentThread().getName());
        Person p1 = new Person();
        p1.setName("Hank Hill");
        try {
            Thread.sleep(8000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ExampleService::getPerson | Returning");
        return p1;
    }

    public List<City> getCities() {
        System.out.println("ExampleService::getCities | Started processing on thread: " + Thread.currentThread().getName());

        City c1 = new City();
        c1.setName("Nowhere");
        c1.setCountryName("United States");

        City c2 = new City();
        c2.setName("Nowhere");
        c2.setCountryName("Antarctica");

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<City> cities = new ArrayList<>();
        cities.add(c1);
        cities.add(c2);

        System.out.println("ExampleService::getCities | Returning");
        return cities;
    }

}
