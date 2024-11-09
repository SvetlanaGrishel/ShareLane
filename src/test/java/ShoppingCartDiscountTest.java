import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ShoppingCartDiscountTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
    }

    @Test
    public void checkDiscount0() {
        driver.findElement(By.name("q")).clear();
        //quantity of books = 10 (Books: 1 - 19 -> Discount = 0)
        driver.findElement(By.name("q")).sendKeys("10");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 0);
        softAssert.assertEquals(discount$, 0.0);
        softAssert.assertEquals(total, 100.0);

        //quantity of books = 19 (Books: 1 - 19 -> Discount = 0)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("19");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 0);
        softAssert.assertEquals(discount$Step2, 0.0);
        softAssert.assertEquals(totalStep2, 190.0);
        softAssert.assertAll();
    }

    @Test
    public void checkDiscount2() {
        //quantity of books = 20 (Books: 20 - 49 -> Discount = 2%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("20");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 2);
        softAssert.assertEquals(discount$, 4.0);
        softAssert.assertEquals(total, 196.0);

        //quantity of books = 49 (Books: 20 - 49 -> Discount = 2%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("49");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 2);
        softAssert.assertEquals(discount$Step2, 9.8);
        softAssert.assertEquals(totalStep2, 480.2);
        softAssert.assertAll();
    }

    @Test
    public void checkDiscount3() {
        //quantity of books = 50 (Books: 50 - 99 -> Discount = 3%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("50");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 3);
        softAssert.assertEquals(discount$, 15.0);
        softAssert.assertEquals(total, 485.0);

        //quantity of books = 99 (Books: 50 - 99 -> Discount = 3%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("99");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 3);
        softAssert.assertEquals(discount$Step2, 29.7);
        softAssert.assertEquals(totalStep2, 960.3);
        softAssert.assertAll();
    }

    @Test
    public void checkDiscount4() {
        //quantity of books = 100 (Books: 100 - 499 -> Discount = 4%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("100");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 4);
        softAssert.assertEquals(discount$, 40.0);
        softAssert.assertEquals(total, 960.0);

        //quantity of books = 499 (Books: 100 - 499 -> Discount = 4%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("499");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 4);
        softAssert.assertEquals(discount$Step2, 199.6);
        softAssert.assertEquals(totalStep2, 4790.4);
        softAssert.assertAll();
    }

    @Test
    public void checkDiscount5() {
        //quantity of books = 500 (Books: 500 - 999 -> Discount = 5%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("500");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 5);
        softAssert.assertEquals(discount$, 250.0);
        softAssert.assertEquals(total, 4750.0);

        //quantity of books = 999 (Books: 500 - 999 -> Discount = 5%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("999");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 5);
        softAssert.assertEquals(discount$Step2, 499.5);
        softAssert.assertEquals(totalStep2, 9490.5);
        softAssert.assertAll();
    }

    @Test
    public void checkDiscount6() {
        //quantity of books = 1000 (Books: 1000 - 4999 -> Discount = 6%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("1000");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 6);
        softAssert.assertEquals(discount$, 600.0);
        softAssert.assertEquals(total, 9400.0);

        //quantity of books = 4999 (Books: 1000 - 4999 -> Discount = 6%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("4999");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 6);
        softAssert.assertEquals(discount$Step2, 2999.4);
        softAssert.assertEquals(totalStep2, 46990.6);
        softAssert.assertAll();
    }

    @Test
    public void checkDiscount7() {
        //quantity of books = 5000 (Books: 5000 - 9999 -> Discount = 7%)
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

        //quantity of books = 9999 (Books: 5000 - 9999 -> Discount = 7%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("9999");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 7);
        softAssert.assertEquals(discount$Step2, 6999.3);
        softAssert.assertEquals(totalStep2, 92990.7);
        softAssert.assertAll();
    }

    @Test
    public void checkDiscount8() {
        //quantity of books = 10000 (Books: 10000 and more -> Discount = 8%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("10000");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercent = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(discountPercent, 8);
        softAssert.assertEquals(discount$, 8000.0);
        softAssert.assertEquals(total, 92000.0);

        //quantity of books = 20000 (Books: 10000 and more -> Discount = 8%)
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("20000");
        driver.findElement(By.cssSelector("[value = Update]")).click();
        String discountPercentStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$Step2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String totalStep2 = driver.
                findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        softAssert.assertEquals(discountPercentStep2, 8);
        softAssert.assertEquals(discount$Step2, 16000.0);
        softAssert.assertEquals(totalStep2, 184000.0);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
