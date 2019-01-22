package page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultis.TestReport;

public class DanhsachphimActivity extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    public DanhsachphimActivity(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver,this);
        this.wait = new WebDriverWait(appiumDriver,30);
    }

    public void clickUserprofile(){
        WebElement ftuserprofile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageButton[1]"
        )));
        ftuserprofile.click();

        TestReport.testReport(appiumDriver,true,"Go to User Profile",true);
    }
}
