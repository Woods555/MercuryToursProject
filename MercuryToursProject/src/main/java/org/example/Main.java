package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
        @FindBy(name = "userName")
        private WebElement userNameInput;
        @FindBy(name = "password")
        private WebElement passwordInput;
        @FindBy(name = "submit")
        private WebElement submitButton;
        @FindBy(xpath = "//a[text()=\"Flights\"]")
        private WebElement flightsButton;
        @FindBy(name = "fromPort")
        private WebElement departingFromSelector;
        @FindBy(xpath = "//span[@class='home_test_select']")
        private WebElement selectStartATestButton;
        @FindBy(className = "footer")
        private WebElement pageBuildVersion;

        //data
        static String pageUrl = "https://demo.guru99.com/test/newtours/";
        static String departingToFind = "New York";
        static String userNameData = "123";
        static String passwordData = "123";

        WebDriver driver = new ChromeDriver();
        public static void main(String[] args) throws InterruptedException {
            Main test = new Main();
            test.runTest(departingToFind,userNameData,passwordData);
        }

        public Main() {
            PageFactory.initElements(driver, this);
        }

        public void runTest(String departingToFind, String userNameData, String passwordData) throws InterruptedException {
            setConfiguration();
            String codeBuild;

            System.out.println(driver.getTitle());

            sendTextToElement(userNameInput,userNameData);
            sendTextToElement(passwordInput,passwordData);
            clickElement(submitButton);
            clickElement(flightsButton);
            selectElementFromList(departingFromSelector,departingToFind);

            codeBuild = getTextFromElement(pageBuildVersion);

            System.out.println("The build code of the page is: "+ codeBuild);

            Thread.sleep(5000);

            driver.quit();
        }

        protected void setConfiguration(){
            driver.manage().window().maximize();
            driver.get(pageUrl);
        }

        protected void waitIfIsClickable(WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        protected void clickElement(WebElement element) {
            waitIfIsClickable(element);
            element.click();
        }

        protected String getTextFromElement(WebElement element) {
            String textFromElement;

            waitIfIsClickable(element);
            textFromElement = element.getText();

            return textFromElement;
        }

        protected void sendTextToElement(WebElement element,String text) {
            waitIfIsClickable(element);
            element.sendKeys(text);
        }

        protected void selectElementFromList(WebElement element, String optionToSelect) {
            Select select = new Select(element);
            select.selectByValue(optionToSelect);
        }
    }


