package day1;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestDrugInteractions{

    WebDriver driver;


    @BeforeClass
    public void startBrowser(){

        System.setProperty("webdriver.edge.driver","C:\\Users\\jonly\\Downloads\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        System.out.println("Browser started");

    }
    
    @Test(priority=0)
    public void startApp(){


        //check if login page
        driver.get("http://feilitest1.herokuapp.com/");
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        Assert.assertTrue(currentURL.contains("fei"));
    }

    @Test(priority=1)
    public void goToLogin() throws InterruptedException {

        driver.findElement(By.className("log_button")).click();
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);

    }

    @Test(priority=2)
    public void attemptToLogin() throws InterruptedException {

        driver.findElement (By.name ("email")).sendKeys("test1");
        Thread.sleep(1000);
        driver.findElement (By.name ("password")).sendKeys("test1");
        Thread.sleep(3000);
        driver.findElement(By.className("button-7")).click();
        Thread.sleep(3000);

        String buttonText = driver.findElement(By.className("log_button")).getText();
        Assert.assertEquals("Logout",buttonText);
        System.out.println(buttonText);

    }





    @AfterClass
    public void closeApp(){
        //AfterClass means  trigger after done with class
        driver.quit();
        System.out.println("Browser closes");
    }



}
