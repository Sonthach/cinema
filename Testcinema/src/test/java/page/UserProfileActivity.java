package page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultis.TestReport;

public class UserProfileActivity extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    public UserProfileActivity(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver,this);
        this.wait = new WebDriverWait(appiumDriver,30);
    }

    public boolean checkUserNameEmail(String name, String email){
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtnameuser")));
        String mname = inputName.getText();

        WebElement txtEmail = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtemailuser")));
        String memail =txtEmail.getText();

        boolean result = mname.equals(name) && memail.equals(email);

        TestReport.testReport(appiumDriver,result,"Check Name and Email User",true);
        return  result;
    }

    public void clickLogout() throws InterruptedException {
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.id("btndangxuat")));
        logout.click();
        TestReport.testReport(appiumDriver,true,"Click SignOut",true);
        WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(By.id("button1")));
        ok.click();
        Thread.sleep(4000);


        TestReport.testReport(appiumDriver,true,"Click Có thì go to SignIn Capture",true);
    }


}
