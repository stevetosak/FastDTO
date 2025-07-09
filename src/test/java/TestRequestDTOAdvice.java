import com.vitosak.Config;
import com.vitosak.RequestDTOClassComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(Config.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestRequestDTOAdvice {
    @Autowired
    RequestDTOClassComponent requestDTOClassComponent;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldConvertJsonToPerson() throws Exception {
        String json = """
            {
              "name": "Stefan",
              "age": 30
            }
            """;


    }
}
