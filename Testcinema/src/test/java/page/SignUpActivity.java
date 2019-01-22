package page;

import io.appium.java_client.AppiumDriver;
import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultis.TestReport;

public class SignUpActivity extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    public SignUpActivity (AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver,this);
        this.wait = new WebDriverWait(appiumDriver,30);
    }

    public void inputThongtin(String name,String email,String password){
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtregname")));
        inputName.sendKeys(name);

        WebElement inputEmail   = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtregemail")));
        inputEmail.sendKeys(email);

        WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtregpassword")));
        inputPassword.sendKeys(password);

        WebElement inputConfirm = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtcomfirmpassword")));
        inputConfirm.sendKeys(password);



        TestReport.testReport(appiumDriver,true,"input Info",true);

    }

    public void clickDangky(){
        WebElement buttonDangky = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonregister")));
        buttonDangky.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestReport.testReport(appiumDriver,true,"Capture List Movie",true);
    }
}
