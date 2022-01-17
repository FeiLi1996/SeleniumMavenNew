package day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumDemo {
    public static void main(String[] args) {

        System.setProperty("webdriver.edge.driver","C:\\Users\\jonly\\Downloads\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
