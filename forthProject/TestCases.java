package forthProject;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	@BeforeTest
	public void HomePage() {
		driver.get("https://automationteststore.com/");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void AddToCart() throws InterruptedException {
		List<WebElement> TheListOfItems = driver.findElements(By.className("prdocutname"));
		int TotalNumberOfItems = TheListOfItems.size();
		System.out.println(TotalNumberOfItems);
		int RandomItemIndex = rand.nextInt(TheListOfItems.size());
		TheListOfItems.get(RandomItemIndex).click();
		Thread.sleep(1000);
		if (driver.getPageSource().contains("Out Of Stock")) {
			driver.navigate().back();
			System.out.print("Sorry The Item Is Out Of Stock");
		} else {
			System.out.println("the item is available");
		}

		Thread.sleep(1000);
		int randomQty = rand.nextInt(5) + 2;
		WebElement QtyInput = driver.findElement(By.name("quantity"));
		QtyInput.clear();
		QtyInput.sendKeys(String.valueOf(randomQty));
		System.out.println("Quantity Chossen" + randomQty);
		Thread.sleep(1000);
		if (driver.getPageSource().contains("Out Of Stock")) {
			driver.navigate().back();
			System.out.print("Sorry The Item Is Out Of Stock");
		}

		WebElement AddToCart = driver.findElement(By.className("cart"));
		AddToCart.click();
		Thread.sleep(1500);

		WebElement TotalPrice = driver.findElement(By.cssSelector("span[class='bold totalamout']"));
		if (TotalPrice.isDisplayed()) {
			System.out.println("Cart Total Shown" + TotalPrice.getText());

		}

	}

}
