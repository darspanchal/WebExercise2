package Org;

import com.sun.org.apache.bcel.internal.generic.FieldGenOrMethodGen;
import javafx.scene.input.DataFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.java2d.pipe.SpanShapeRenderer;
import sun.plugin2.message.Message;

import javax.xml.xpath.XPath;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static javafx.scene.input.KeyCode.T;
import static okhttp3.internal.http.HttpDate.format;
import static sun.rmi.transport.TransportConstants.Return;
//Main Mehtod
public class WebExercise2
{

    static protected WebDriver driver;

//1. Creating method for openbrowser
    @Before
        public void openbrowser ()
    {
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\BrowsersDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }
//2. Creating method for time stamp
        public String timeStamp()
    {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
        Date date = new Date();
        return (dateFormat.format(date));
    }
//3. Creating method for classbrwser
        @After
        public void classbrowser()
    {
        driver.quit();
    }
//4. Creating method for clickonelement
        public void clickonelement(By by)
    {
        driver.findElement(by).click();
    }
//5. Creating method for waitforclickable
        public void waitForClickable(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
//6. Creating method for waitforvisibility
        public void waitForvisibility (By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
//7. Creating method for entertext
        public void entertext (By by,String text)
    {
        driver.findElement(by).sendKeys(text);
        waitForClickable(by,30);
    }
//8. Creating method fot gettextfromelement
        public String getTextfromelement (By by)
    {
       // WebElement webElement = driver.findElement(by);
       // String text = webElement.getText();
        return driver.findElement(by).getText();
    }
//9. Creating method for selectfromdropdownbyvalue
    public void selectFromDropDownByValue (By by ,String text){
        Select select = new Select(driver.findElement(by));
        select.selectByValue(String.valueOf(text));
    }
//10.Creating method for selectfromdropdownbyvisibletext
        public void selectFromDropDownByVisibletext (By by,String text){
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }
//11.Creating method for selectfromdropdownbyindex
        public void selectFromDropDownByIndex (By by , int index){
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(index);
    }
//12.Creating method for waitforelementispresent
        public void waitForElementsIsPresent(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }



    @Test
    public void nopcommerce() {
//  Open website
        driver.get("https://demo.nopcommerce.com/");

//  Click on register button
        clickonelement(By.linkText("Register"));

//  Click on register button after website is ready
        //waitForClickable(By.id("register-button"), 30);
        waitForClickable(By.id("FirstName"), 40);

//  will print firstname
        entertext(By.id("FirstName"), "abc");

//  will print lastname
        entertext(By.id("LastName"), "def");

//  will select dateofbirthday
        selectFromDropDownByValue(By.name("DateOfBirthDay"), "8");

//  will select dateofbirthyear
        selectFromDropDownByVisibletext(By.name("DateOfBirthYear"), "1991");

//  will select dateofbirthmonth
        selectFromDropDownByIndex(By.name("DateOfBirthMonth"), 10);

//  will enter email
        entertext(By.id("Email"), "abcdef+" + timeStamp() + "@gmail.com");

//  will type password
        entertext(By.id("Password"), "london123");

//  will type passowerd again
        entertext(By.id("ConfirmPassword"), "london123");

//  will click on register button
        clickonelement(By.id("register-button"));

//  will dispay result
        getTextfromelement(By.className("result"));

//  will print expected result
        String expected = "Your registration completed";

//  will print actual result
        String actual = driver.findElement(By.className("result")).getText();

//  will compare expected and actual result
        Assert.assertEquals("Failed", expected, actual);

    }
    @Test
        public void Orangehrmlive() {
//  Open website
        driver.get("https://opensource-demo.orangehrmlive.com/");

//  will type username
        entertext(By.id("txtUsername"), "Admin");

//  will type password
        entertext(By.id("txtPassword"), "admin123");

//  will click on submit
        clickonelement(By.name("Submit"));

//  will wait until specific time
        waitForElementsIsPresent(By.cssSelector("a.panelTrigger"),70);

//  will print expected result
        String expected = "Welcome Admin";

//  will print actual result
        String actual = driver.findElement(By.cssSelector("a.panelTrigger")).getText();

//  will compare expected and actual result
        Assert.assertEquals("Failed", expected, actual);
    }

    @Test
    public void MockPlus() {
//  will open website
        driver.get("https://www.mockplus.com/");

//  click on register buttton
        clickonelement(By.className("user-btn"));

//   will type email address
        entertext(By.id("email"), "test+" + timeStamp() + "@test.com");

//  will type password
        entertext(By.id("password"), "test123");

//  will type confirmed password
        entertext(By.id("cofPassword"), "test123");

//  will select agree
       clickonelement(By.id("agree"));

//  will click on register button
        clickonelement(By.id("register")); //Register Successfully

//  will give you expected result
        String expected = "logo";

//  Will give you the output result
        String actual = getTextfromelement(By.className("logo"));

//  will compare result
        Assert.assertEquals("Failed", expected, actual);

    }

    @Test
        public void ocado(){
//  will open website
        driver.get("https://www.ocado.com/webshop/startWebshop.do");

//  will click on register
        clickonelement(By.id("quickReg"));

//  will click on registration button
        waitForClickable(By.id("registration-submit-button"),50);

//  clcik in title
        selectFromDropDownByVisibletext(By.id("title"),"Mr");

//  type firstname
        entertext(By.id("firstName"),"abc");

//  type lastname
        entertext(By.id("lastName"),"def");

//  type email id
        entertext(By.id("login"),"abcdef+"+timeStamp()+"@gmail.com");

//  type password
        entertext(By.id("password"),"London@20");

//  type postcode
        entertext(By.id("postcode"),"HA0 4UG");

//  click on submit button
        clickonelement(By.id("registration-submit-button"));

//  will give you expected result
        String expected = "My Ocado";

//  will give you output for actual result
        String actual = getTextfromelement(By.cssSelector("a.primary-bar-link"));

//  will comare expected and actual result
        Assert.assertEquals("Fail",expected,actual);
    }

    @Test
        public void sky(){
//  will open website
        driver.get("https://www.sky.com/");

//  click on sign in
        clickonelement(By.linkText("Sign in"));

//  click on sign up
        clickonelement(By.linkText("Sign up"));

//  will select title from dropdown
        selectFromDropDownByVisibletext(By.id("title"),"Mr");

//  will type firstname
        entertext(By.id("firstname"),"abcdef");

//  will type lastname
        entertext(By.id("lastname"),"ghijkl");

//  will type email address
        entertext(By.id("email"),"abcdef+"+timeStamp()+"@gmail.com");

//  will type confirmed email address
        entertext(By.id("confirmEmail"),"abcdef+"+timeStamp()+"@gmail.com");

//  will type password
        entertext(By.id("password"),"sky@09876");

//  will type confirmpassowrd
        entertext(By.id("confirmPassword"),"sky@09876");

//  will click
        clickonelement(By.id("termsAndConditions"));

//  will click
        clickonelement(By.id("marketingOptOut"));

//  will click on submit button
        clickonelement(By.id("submitButton"));

//  will give you expexted result
        String expected = "Sorry, the characters do not match";

//  will give you actual result
        String actual = "Sorry, the characters do not match";

//  will comare expected with actual result
        Assert.assertEquals("Fail",expected,actual); }

    @Test
    public void Asda (){
//  will open website
        driver.get("https://groceries.asda.com/?cmpid=ppc-_-ghs-_--_-google-_-asda-_-dskwid-s43700013662392458_dc");

//  will click on register
        clickonelement(By.linkText("Register"));

//  will enter emailaddress
        entertext(By.xpath("//input[@type='email']"),"asdgef@test.com");

//  will enter password
        entertext(By.xpath("//input[@type='password']"),"1234asdf");

//  will enter postcode
        entertext(By.xpath("//input[@type='text']"),"ha0 3el");

//  will click on checkmark
        clickonelement(By.className("checkmark"));

//  will click on submit button
        clickonelement(By.xpath("//button[@class='primary full']"));

//  will click on submit button
        clickonelement(By.xpath("//button[@type='submit']"));

//  will click needhelp
        clickonelement(By.id("need-help"));

//  will give you expected result
        String Expected = "Registration completed";

//  will give you actual result
        String Actual = getTextfromelement(By.id("need-help"));

//  will comapre expected and actual
        Assert.assertEquals("Fail",Expected,Actual);
    }
}




