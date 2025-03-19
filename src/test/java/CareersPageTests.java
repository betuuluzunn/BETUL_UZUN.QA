import org.testng.annotations.Test;

public class CareersPageTests extends BaseTest{

    @Test
    public void verifyQADetailPageTest() throws InterruptedException { //testleri run ettigimiz class
        careersPage.checkHomePage();
        careersPage.tapCookieButton();
        careersPage.verifyCookieClosed();
        careersPage.tapCareersButton();
        careersPage.verifyLocationsComponent();
        careersPage.verifyLifeComponent();
        careersPage.goToQaUrl();
        careersPage.tapQaJobs();
        careersPage.openFilter();
        careersPage.selectLocation();
        careersPage.checkQualityAssuranceTexts();
        careersPage.checkLocationTexts();
        careersPage.tapViewRoleButton();
        careersPage.verifyJobDetailPage();
    }
}

