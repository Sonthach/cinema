package page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultis.AppiumServer;
import ultis.TestReport;

public class SignInActivity extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    public SignInActivity(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver,this);
        this.wait = new WebDriverWait(appiumDriver,30);
    }

    public void clickDangky(){
        WebElement buttonDangky = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonreg")));
        buttonDangky.click();
    }

    public void inputinfo(String email,String password) throws InterruptedException {
        TestReport.testReport(appiumDriver,true,"Sign In - Chưa nhập thông tin",true);
        WebElement inputemail = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtloginemail")));
        inputemail.sendKeys(email);

        WebElement inputpassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtloginpassword")));
        inputpassword.sendKeys(password);
        TestReport.testReport(appiumDriver,true,"Sau khi nhập Email và Mật khẩu",true);
        WebElement buttonDangnhap = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonlogin")));
        buttonDangnhap.click();
        Thread.sleep(4000);

        TestReport.testReport(appiumDriver,true,"SignIn",true);
    }
}
