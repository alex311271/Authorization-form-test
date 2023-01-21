package org.exsample;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationFormTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void authorizationOK() {
        driver.get("http://u920152e.beget.tech/#");
        driver.findElement(By.cssSelector("body > div.form_auth_block > div > form > input[type=email]:nth-child(2)")).
                sendKeys("mail@mail.ru");
        driver.findElement(By.cssSelector("body > div.form_auth_block > div > form > input[type=password]:nth-child(4)")).
                sendKeys("qwerty123");
        driver.findElement(By.cssSelector("body > div.form_auth_block > div > form > button")).click();
        String text = driver.findElement(By.cssSelector("body > form > p")).getText();
        assertEquals("Сколько Вам лет?", text.trim());
    }
}
