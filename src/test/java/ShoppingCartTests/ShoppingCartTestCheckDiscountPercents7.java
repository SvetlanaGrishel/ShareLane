package ShoppingCartTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTestCheckDiscountPercents7 {

    @Test
    public void checkDiscount7() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&zip_code=12345&first_name=test&last_name=test&" +
                "email=user%40pflb.ru&password1=12345678&password2=12345678");
        String email = driver.findElement(By.xpath(
                "//table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value = Login]")).click();
        driver.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=1");
        driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("5000");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 7);
        softAssert.assertEquals(discount$, 3500.0);
        softAssert.assertEquals(total, 46500.0);
        driver.quit();
        softAssert.assertAll();
    }
}
