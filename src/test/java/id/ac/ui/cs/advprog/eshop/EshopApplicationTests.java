package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
    }

}

class EshopApplicationMainTest {

    @Test
    void testRunSpring() {
        try (MockedStatic<SpringApplication> mocked = Mockito.mockStatic(SpringApplication.class)) {
            mocked.when(() -> SpringApplication.run(EshopApplication.class, new String[] {})).thenReturn(null);
            EshopApplication.main(new String[] {});
            mocked.verify(() -> SpringApplication.run(EshopApplication.class, new String[] {}));
        }
    }
}