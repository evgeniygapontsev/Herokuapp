package com.herokuapp.theinternet.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
/*import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;*/

import java.util.concurrent.TimeUnit;

public class ForgotPasswordTest {
    private WebDriver driver;


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void retrievePassword() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/forgot_password");
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys("test@test.com");
        emailInput.submit();

    }

    @Test
    public void dragAndDropTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        WebElement columnAElement = driver.findElement(By.id("column-a"));
        WebElement columnBElement = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
        actions
                .moveToElement(columnAElement)
                .clickAndHold()
                .moveToElement(columnBElement)
                .release()
                .build()
                .perform();

    }

    @Test
    public void addRemoveElementsTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        addElement.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        addElement.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement deleteElement = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[2]"));
        deleteElement.click();

    }

    @Test
    public void checkboxesTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        WebElement firstCheckbox = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        firstCheckbox.isSelected();
        firstCheckbox.click();
        firstCheckbox.isSelected();
        WebElement secondCheckbox = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
        secondCheckbox.isSelected();
        secondCheckbox.click();
        secondCheckbox.isSelected();

    }

    @Test
    public void dropdownTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/dropdown");
        WebElement optionButton = driver.findElement(By.id("dropdown"));
        optionButton.click();
        WebElement optionOne = driver.findElement(By.cssSelector("[value='1']"));
        optionOne.click();
        optionOne.isSelected();
        WebElement optionTwo = driver.findElement(By.cssSelector("[value='2']"));
        optionTwo.click();
        optionTwo.isSelected();

    }

    @Test
    public void inputsTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement entryField = driver.findElement(By.tagName("input"));
        entryField.click();
        Actions action = new Actions(driver);
        action.sendKeys(entryField, Keys.ARROW_UP).perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        action.sendKeys(entryField, Keys.ARROW_DOWN).perform();
        action.sendKeys(entryField, Keys.ARROW_DOWN).perform();


    }

    @Test
    public void sortableDataTablesTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/tables");
        WebElement lastNameCell = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[2]/td[1]"));
        String lastName = lastNameCell.getText();
        Assert.assertEquals(lastName, "Bach");
        WebElement emailCell = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[3]/td[3]"));
        String email = emailCell.getText();
        Assert.assertEquals(email, "jdoe@hotmail.com");
        WebElement webSiteCell = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[4]/td[5]"));
        String webSite = webSiteCell.getText();
        Assert.assertEquals(webSite, "http://www.timconway.com");


    }

    @Test
    public void typosTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/typos");
        WebElement typoArticleElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]"));
        String typoArticle = typoArticleElement.getText();
        Assert.assertEquals(typoArticle,"Sometimes you'll see a typo, other times you won't.");

    }

    @Test
    public void hoversTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/hovers");
        Actions action = new Actions(driver);
        WebElement profileFirst = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
        WebElement viewButtonOne = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        action.moveToElement(profileFirst).perform();
        WebElement userOneNameElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5"));
        String userOneName = userOneNameElement.getText();
        Assert.assertEquals(userOneName, "name: user1");
        action.click(viewButtonOne).perform();
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement profileSecond = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/img"));
        WebElement viewButtonTwo = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/a"));
        action.moveToElement(profileSecond).perform();
        WebElement userTwoNameElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/h5"));
        String userTwoName = userTwoNameElement.getText();
        Assert.assertEquals(userTwoName, "name: user2");
        action.click(viewButtonTwo).perform();
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement profileThird = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/img"));
        WebElement viewButtonThree = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/a"));
        action.moveToElement(profileThird).perform();
        WebElement userThreeNameElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/h5"));
        String userThreeName = userThreeNameElement.getText();
        Assert.assertEquals(userThreeName, "name: user3");
        action.click(viewButtonThree).perform();


    }

    @Test
    public void notificationMessageTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
        WebElement clickHereButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p/a"));
        clickHereButton.click();
        WebElement notificationMessageElement = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
        String notificationMessage = notificationMessageElement.getText();
        Assert.assertEquals(notificationMessage, "Action successful " +
                "x");


    }

/*    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();

        }
    }*/
}