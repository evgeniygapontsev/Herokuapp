package com.herokuapp.theinternet.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SeleniumAdvancedTest {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void contextMenuTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/context_menu");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement hotSpot = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions
                .contextClick(hotSpot)
                .build()
                .perform();
        Alert alertMessage = driver.switchTo().alert();
        Assert.assertEquals(alertMessage.getText(), "You selected a context menu");
        Alert alertAccept = driver.switchTo().alert();
        alertAccept.accept();
    }

    @Test
    public void dynamicControlsTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
        WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button"));
        removeButton.click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("p[id='message']"), "It's gone!"));
        wait.until(ExpectedConditions.invisibilityOf(checkbox));

        WebElement inputElement = driver.findElement(By.cssSelector("input[type='text']"));
        Assert.assertFalse(inputElement.isEnabled());

        WebElement enableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        enableButton.click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("p[id='message']"), "It's enabled!"));
        Assert.assertTrue(inputElement.isEnabled());
    }

    @Test
    public void fileUploadTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement fileUpload = driver.findElement(By.id("file-upload"));
        fileUpload.sendKeys
                ("F:/QA12WorkSpace/Herokuapp/src/test/java/com/herokuapp/theinternet/test/InputFile.txt");
        WebElement uploadButton = driver.findElement(By.cssSelector("[type='submit'][value='Upload']"));
        uploadButton.click();
        String uploadedFileName = driver.findElement(By.xpath("//*[@id=\"uploaded-files\"]")).getText();
        Assert.assertEquals(uploadedFileName, "InputFile.txt");

    }

    @Test
    public void iframeTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://the-internet.herokuapp.com/iframe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement iFrameElement = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrameElement);

        WebElement iFrameBody = driver.findElement(By.tagName("body"));
        String iFrameBodyText = iFrameBody.getText();
        Assert.assertEquals(iFrameBodyText, "Your content goes here.");

        driver.switchTo().defaultContent();
    }


/*    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();

        }
    }*/
}
