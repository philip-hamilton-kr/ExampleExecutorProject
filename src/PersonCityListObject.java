import lombok.Data;

import java.util.List;

@Data
public class PersonCityListObject {
    private Person person;
    private List<City> cities;
}
