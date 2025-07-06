import com.vitosak.annotations.FieldMapping;
import com.vitosak.annotations.Flatten;
import com.vitosak.annotations.SimpleDTO;

@SimpleDTO(name = "test")
@SimpleDTO(name = "test2")
public class Person {
    @FieldMapping
    String name;
    @FieldMapping @Flatten
    Child child;
}
