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

    public void clickftcreatemovie(){
        WebElement fttaophim = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageButton[2]"
        )));
        fttaophim.click();

        TestReport.testReport(appiumDriver,true,"Go to Create Movie",true);
    }

    public boolean checkNameMovie(String name){
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.TextView[1]")));
        String mname = inputName.getText();


        boolean result = mname.equals(name);

        TestReport.testReport(appiumDriver,result,"Check Name Movie",true);
        return  result;
    }

    public void details() throws InterruptedException {
        WebElement clickDetails = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.ImageView")));
        clickDetails.click();
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Check Name Movie",true);
    }


}
