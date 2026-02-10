package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setup() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void user_can_create_product_and_see_it_in_list(ChromeDriver driver) {
        String productName = "E2E Product";
        String productQuantity = "7";
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys(productName);
        driver.findElement(By.id("quantityInput")).sendKeys(productQuantity);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement added = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + productName + "']")));
        assertNotNull(added);
        assertEquals(productName, added.getText());
    }

    @Test
    void create_form_shows_validation_when_name_missing(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("quantityInput")).sendKeys("3");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement heading = driver.findElement(By.tagName("h3"));
        assertEquals("Create New Product", heading.getText());
    }
}
