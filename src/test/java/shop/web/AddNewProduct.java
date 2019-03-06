package shop.web;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddNewProduct {
    private static HtmlUnitDriver browser;

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate rest;

    @BeforeClass
    public static void setup() {
        browser = new HtmlUnitDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }

    @Test
    public void testAddNewProductTest() throws Exception{
        browser.get(homePageUrl());
        clickAddNewProduct();
        assertLandedOnLoginPage();
        doRegistration("test","test");
        assertLandedOnLoginPage();
        doLogin("test","test");
        assertEquals(addPageUrl(), browser.getCurrentUrl());
        setUpNewProduct();
        submitNewProduct();
        assertEquals(homePageUrl(), browser.getCurrentUrl());
        clickAddNewProduct();
        doLogout();
        assertEquals(homePageUrl(), browser.getCurrentUrl());
    }

    @Test
    public void testAddNewProductTestEmptyProductInfo() throws Exception{
        browser.get(homePageUrl());
        clickAddNewProduct();
        assertLandedOnLoginPage();
        doRegistration("test","test");
        assertLandedOnLoginPage();
        doLogin("test","test");
        assertEquals(addPageUrl(), browser.getCurrentUrl());
        setUpNewProductWithBlankData();
        submitNewProductWithBlankData();
        doLogout();
        assertEquals(homePageUrl(), browser.getCurrentUrl());
    }

    private void submitNewProductWithBlankData() {
        browser.findElementByCssSelector("form#addProduct").submit();

        assertEquals(addPageUrl(), browser.getCurrentUrl());

        List<String> validationErrors = getValidationErrorTexts();
        assertEquals(7, validationErrors.size());
        assertTrue(validationErrors.contains("Please correct the problems below and resubmit."));
    }

    private List<String> getValidationErrorTexts() {
        List<WebElement> validationErrorElements = browser.findElementsByClassName("validationError");
        List<String> validationErrors = validationErrorElements.stream()
                .map(el -> el.getText())
                .collect(Collectors.toList());
        return validationErrors;
    }

    private void setUpNewProductWithBlankData() {
        browser.findElementByName("name").sendKeys("");
        browser.findElementByName("unitPrice").sendKeys("");
        browser.findElementByName("description").sendKeys("");
        browser.findElementByName("manufacturer").sendKeys("");
        browser.findElementByName("category").sendKeys("");
        browser.findElementByName("unitsInStock").sendKeys("");
    }

    private void doLogout() {
        browser.findElementByCssSelector("form#logoutForm").submit();

    }

    private void submitNewProduct() {
        browser.findElementByCssSelector("form#addProduct").submit();
    }

    private void setUpNewProduct() {
        browser.findElementByName("name").sendKeys("telewizor MSI");
        browser.findElementByName("unitPrice").sendKeys("2000");
        browser.findElementByName("description").sendKeys("Super telewizor");
        browser.findElementByName("manufacturer").sendKeys("MSI");
        browser.findElementByName("category").sendKeys("Tv");
        browser.findElementByName("unitsInStock").sendKeys("10");
        browser.findElementByCssSelector("option[value='NEW']").click();
    }

    private void clickAddNewProduct() {
        assertEquals(homePageUrl(), browser.getCurrentUrl());
        browser.findElementByCssSelector("a[id='addProduct']").click();
    }

    private void assertLandedOnLoginPage() {
        assertEquals(loginPageUrl(), browser.getCurrentUrl());
    }

    private void doRegistration(String username, String password) {
        browser.findElementByLinkText("here").click();
        assertEquals(registrationPageUrl(), browser.getCurrentUrl());
        browser.findElementByName("username").sendKeys(username);
        browser.findElementByName("password").sendKeys(password);
        browser.findElementByName("confirmPassword").sendKeys(password);
        browser.findElementByCssSelector("form#registerForm").submit();
    }

    private void doLogin(String username, String password) {
        browser.findElementByName("username").sendKeys(username);
        browser.findElementByName("password").sendKeys(password);
        browser.findElementByCssSelector("form#loginForm").submit();
    }


    //
    // URL helper methods
    //
    private String loginPageUrl() {
        return "http://localhost:" + port + "/login";
    }

    private String registrationPageUrl() {
        return "http://localhost:" + port + "/register";
    }


    private String homePageUrl() {
        return "http://localhost:" + port + "/products";
    }

    private String addPageUrl() {
        return homePageUrl() + "/add";
    }


}
