public class Main {

    public static void main(String[] args){

        ExampleService es = new ExampleService();

        PersonCityListObject returnObject = es.getPersonCityInformationAsync();

        System.out.println(returnObject);

        es.executor.shutdownNow();
    }

}
