package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CareersPage extends BaseMethods {

    public CareersPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    private final By homePage = By.id("desktop_hero_24");
    private final By cookieAcceptButton = By.id("wt-cli-accept-all-btn");
    private final By companyButton = By.xpath("/html/body/nav/div[2]/div/ul[1]/li[6]/a");
    private final By careerButton = By.xpath("/html/body/nav/div[2]/div/ul[1]/li[6]/div/div[2]/a[2]");
    private final By qaJobsButton = By.xpath("/html/body/div[2]/section[1]/div/div/div/div[1]/div/section/div/div/div[1]/div/div/a");
    private final By openFilter = By.id("select2-filter-by-location-container");
    private final By selectLocation = By.xpath("/html/body/span/span/span[2]/ul/li[2]");
    private final By firstJobPosition = By.xpath("/html/body/section[3]/div/div/div[2]/div[1]/div/p");
    private final By viewRoleButton = By.xpath("/html/body/section[3]/div/div/div[2]/div[1]/div/a");
    private final By jobDetailPage = By.xpath("/html/body/div[3]");
    private final By locationComponent = By.id("location-slider");
    private final By lifeComponent = By.className("elementor-widget-container");
    private final By filterComponent = By.xpath("/html/body/section[3]/div/div/div[2]/div[1]/div");

    public void checkHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage));
    }

    public void tapCookieButton() {
        click(cookieAcceptButton);
    }

    public void verifyCookieClosed() {
        isElementPresent(cookieAcceptButton);
    }

    public void tapCareersButton() {
        Actions actions = new Actions(driver);
        WebElement companyElement = driver.findElement(companyButton);
        actions.moveToElement(companyElement).perform();
        WebElement careerElement = driver.findElement(careerButton);
        careerElement.click();
    }

    public void verifyLocationsComponent() {
        Actions actions = new Actions(driver);
        WebElement locationElement = driver.findElement(locationComponent);
        actions.moveToElement(locationElement).perform();
    }

    public void verifyLifeComponent() {
        Actions actions = new Actions(driver);
        WebElement lifeElement = driver.findElement(lifeComponent);
        actions.moveToElement(lifeElement).perform();
    }

    public void goToQaUrl () {
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }

    public void tapQaJobs () {
        click(qaJobsButton);
    }

    public void openFilter () throws InterruptedException {
        Thread.sleep(10000);
        Actions actions = new Actions(driver);
        WebElement filterElement = driver.findElement(filterComponent);
        actions.moveToElement(filterElement).perform();
        Thread.sleep(5000);
        click(openFilter);
    }

    public void selectLocation () {
        click(selectLocation);
    }

    public void checkQualityAssuranceTexts () {
        List<WebElement> elements = driver.findElements(By.className("position-department"));
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.equals("Quality Assurance")) {
                System.out.println("Element text: " + text + " => Test PASSED");
            } else {
                System.out.println("Element text: " + text + " => Test FAILED");
            }
        }
    }

    public void checkLocationTexts () {
        List<WebElement> elements = driver.findElements(By.className("position-location"));
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.equals("Istanbul, Turkiye")) {
                System.out.println("Element text: " + text + " => Test PASSED");
            } else {
                System.out.println("Element text: " + text + " => Test FAILED");
            }
        }
    }

    public void tapViewRoleButton () {
        Actions actions = new Actions(driver);
        WebElement firstJobElement = driver.findElement(firstJobPosition);
        actions.moveToElement(firstJobElement).perform();
        WebElement viewRoleElement = driver.findElement(viewRoleButton);
        viewRoleElement.click();
    }

    public void verifyJobDetailPage () {
    Set<String> allWindowHandles = driver.getWindowHandles();
    ArrayList<String> tabs = new ArrayList<>(allWindowHandles);
    driver.switchTo().window(tabs.get(1));
    isElementPresent(jobDetailPage);
    }
}
