package day1;


import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.Duration;



public class TestDrugInteractions{

    WebDriver driver;

    String url = "http://feilitest1.herokuapp.com/";
    String username = "test1";
    String password = "test1";
    String drug1 = "aspirin";
    String drug2 = "ibuprofen";


    By logInButton = By.className("button-7");
    By goToLoginButton =  By.className("log_button");

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
    
    @Test
    public void startApp(){


        //check if login page
        driver.get(url);
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        Assert.assertTrue(currentURL.contains("fei"));
    }

    @Test(priority=1)
    public void goToLogin()  {

        driver.findElement(goToLoginButton).click();
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);

    }

    @Test(priority=2)
    public void attemptToLogin() throws InterruptedException {

        driver.findElement (By.name ("email")).sendKeys(username);
        Thread.sleep(1000);
        driver.findElement (By.name ("password")).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(logInButton).click();
        Thread.sleep(3000);

        String buttonText = driver.findElement(goToLoginButton).getText();
        Assert.assertEquals("Logout",buttonText);
        System.out.println(buttonText);

    }
    @Test(priority=3)
    public void goToProfile() throws InterruptedException {

        driver.findElement(By.linkText("User Profile")).click();
        String currentUrl=driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("userprofile"));
        System.out.println(currentUrl);
        Thread.sleep(3000);
        System.out.println("Profile Page");

    }

    @Test(priority=4)
    public void editProfile() throws InterruptedException {

        driver.findElement(By.className("edit_profile_modal_button")).click();

        driver.findElement(By.xpath("//*/form/div[1]/input")).sendKeys("hello3");
        driver.findElement(By.xpath("//*/form/div[2]/input")).sendKeys("hello1");
        driver.findElement(By.xpath("//*/form/div[3]/input")).sendKeys("hello2");
        driver.findElement(By.xpath("//*/form/div[4]/input")).sendKeys("12345");
        driver.findElement(By.id("drug-search")).sendKeys(drug1);
        driver.findElement(By.xpath("//*[text()='+']")).click();
        //https://stackoverflow.com/questions/50677760/selenium-clear-command-doesnt-clear-the-element
        driver.findElement(By.id("drug-search")).clear();
        driver.findElement(By.id("drug-search")).sendKeys(drug2);
        driver.findElement(By.xpath("//*[text()='+']")).click();


        Thread.sleep(3000);
        driver.findElement(By.className("modal_button")).click();
        Thread.sleep(3000);

    }
    @Test(priority=5)
    public void goToCheckInteraction() throws InterruptedException {

        driver.findElement(By.linkText("Check Interactions")).click();
        String currentUrl=driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkinteractions"));
        System.out.println(currentUrl);
        Thread.sleep(3000);
        System.out.println("Check Interaction Page");

    }
    @Test(priority=6)
    public void checkPositiveInteraction() throws InterruptedException, IOException {

        JavascriptExecutor j = (JavascriptExecutor) driver;
        driver.findElement(By.className("interacton_button")).click();
        Thread.sleep(5000);
        j.executeScript("window.scrollBy(0,1000)");

        String interactionDescription = driver.findElement(By.className("interaction_description_header")).getText().toLowerCase();
        System.out.println(interactionDescription);
        Assert.assertTrue(interactionDescription.contains(drug1) &&  interactionDescription.contains(drug2));
        Thread.sleep(5000);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("C:\\Users\\jonly\\IdeaProjects\\SeleniumMavenNew\\src\\test\\java\\screenshots\\"
            + "interactionImage" + ".jpg"));

    }





    @AfterClass
    public void closeApp(){
        //AfterClass means  trigger after done with class
        driver.quit();
        System.out.println("Browser closes");
    }



}
