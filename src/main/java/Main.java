import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demowebshop.tricentis.com/computing-and-internet");


        WebElement firstBook = driver.findElement(By.xpath("(//input[@value='Add to cart'])[1]"));
        firstBook.click();


        driver.get("https://demowebshop.tricentis.com/fiction");

        WebElement firstFictionBook = driver.findElement(By.xpath("(//input[@value='Add to cart'])[1]"));
        firstFictionBook.click();


        driver.get("https://demowebshop.tricentis.com/notebooks");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement prviNotebook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@value='Add to cart'])[1]")));
        prviNotebook.click();

        driver.get("https://demowebshop.tricentis.com/cart");


        String bookPrice = driver.findElement(By.cssSelector(".product-subtotal")).getText();
        String fictionBookPrice = driver.findElement(By.xpath("(//span[@class='product-subtotal'])[2]")).getText();
        String notebookPrice = driver.findElement(By.xpath("(//span[@class='product-subtotal'])[3]")).getText();


        bookPrice = bookPrice.replace("$", "").replace(",", "");
        float bookPriceValue = Float.parseFloat(bookPrice);

        fictionBookPrice = fictionBookPrice.replace("$", "").replace(",", "");
        float fictionBookPriceValue = Float.parseFloat(fictionBookPrice);

        notebookPrice = notebookPrice.replace("$", "").replace(",", "");
        float notebookPriceValue = Float.parseFloat(notebookPrice);


        float totalPrice = bookPriceValue + fictionBookPriceValue + notebookPriceValue;


        String totalPriceOnPage = driver.findElement(By.cssSelector(".order-total .product-price")).getText();
        totalPriceOnPage = totalPriceOnPage.replace("$", "").replace(",", "");
        float totalPriceOnPageValue = Float.parseFloat(totalPriceOnPage);


        Assert.assertEquals(totalPrice, totalPriceOnPageValue,"Not equal");
        if (totalPrice == totalPriceOnPageValue) {
            System.out.println("Verified");
        }


        driver.quit();
    }

}
