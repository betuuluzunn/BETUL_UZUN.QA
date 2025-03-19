package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); //Dynamic wait koydugum yerlerde 60 sn bekliyor

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


    public void checkHomePage() { //homepage görüldü mü onu kontrol et
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage));
    }

    public void tapCookieButton() { //çerezi kapatma
        click(cookieAcceptButton);
    }

    public void verifyCookieClosed() { //çerez butonunu kontrol et kapandı mı
        isElementPresent(cookieAcceptButton);
    }

    public void tapCareersButton() {
        Actions actions = new Actions(driver); //framework'un verdigi aksiyon sınıfı(mouse ile aksiyon yapılcak)
        WebElement companyElement = driver.findElement(companyButton); //company butonunu bul
        actions.moveToElement(companyElement).perform(); //hover
        WebElement careerElement = driver.findElement(careerButton);
        careerElement.click(); //üstüne tık
    }

    public void verifyLocationsComponent() {
        Actions actions = new Actions(driver); //scroll icin yaptım
        WebElement locationElement = driver.findElement(locationComponent);
        actions.moveToElement(locationElement).perform();
    }

    public void verifyLifeComponent() {
        Actions actions = new Actions(driver); //lifeinsider'a scroll et
        WebElement lifeElement = driver.findElement(lifeComponent);
        actions.moveToElement(lifeElement).perform();
    }

    public void goToQaUrl () {
        driver.get("https://useinsider.com/careers/quality-assurance/"); //aynı sekmede url'e gittim
    }

    public void tapQaJobs () {
        click(qaJobsButton);
    }

    public void openFilter () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterComponent));
        Thread.sleep(8000);
        click(openFilter);
    }

    public void selectLocation () {
        click(selectLocation); //istanbulu seç
    }

    public void checkQualityAssuranceTexts () { //titleı  quality assurance olan textleri sırayla kontrol ettim
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

    public void checkLocationTexts () {// istanbul olup olmamasını kontrol et.
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

    public void tapViewRoleButton () throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Senior Software Quality Assurance Engineer')]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        Actions actions = new Actions(driver);
        WebElement firstJobElement = driver.findElement(firstJobPosition);
        actions.moveToElement(firstJobElement).perform(); //hover
        WebElement viewRoleElement = driver.findElement(viewRoleButton);
        viewRoleElement.click();
    }

    public void verifyJobDetailPage () throws InterruptedException { //yeni sekmeden devam etmek için
    Set<String> allWindowHandles = driver.getWindowHandles();
    ArrayList<String> tabs = new ArrayList<>(allWindowHandles);
    driver.switchTo().window(tabs.get(2));
    Thread.sleep(10000);
    isElementPresent(jobDetailPage);

    }
}
