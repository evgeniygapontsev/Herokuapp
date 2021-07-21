package com.herokuapp.theinternet.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgotPasswordTest {
    private WebDriver driver;


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void retrievePassword() {
       driver = new ChromeDriver();
       driver.get("http://the-internet.herokuapp.com/forgot_password");
    }
    @Test
    public void inputTest(){

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();

        }
    }
}