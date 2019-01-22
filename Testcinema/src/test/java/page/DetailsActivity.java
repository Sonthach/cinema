package page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultis.TestReport;

public class DetailsActivity extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

        public DetailsActivity(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver,this);
        this.wait = new WebDriverWait(appiumDriver,30);
    }

    public boolean checkDetailsName(String name){
        WebElement detailsname = wait.until(ExpectedConditions.elementToBeClickable(By.id(
                "chitiet_tenphim")));
        String mdetails = detailsname.getText();


        boolean result = mdetails.contains(name);

        TestReport.testReport(appiumDriver,result,"Check Details Name",true);
        return  result;
    }

    public void clickEditMovie(){
            WebElement clickEdit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.Button[1]")));
            clickEdit.click();

        TestReport.testReport(appiumDriver,true,"Click Edit Movie",true);
        }

    public void editNameMovie(String name) throws Exception {
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.id("edtTenPhim")));
        inputName.sendKeys(name);

        srollingDown2(appiumDriver);

        WebElement buttonsuaphim = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.Button")));
        buttonsuaphim.click();

        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Click Button Edit Name Movie",true);
    }

    public boolean checkNameAfterEdit(String name){
        WebElement detailsname = wait.until(ExpectedConditions.elementToBeClickable(By.id(
                "chitiet_tenphim")));
        String mdetails = detailsname.getText();
        boolean result = mdetails.contains(name);
        TestReport.testReport(appiumDriver,result,"Check Details Name After Edit",true);
        return  result;
    }

    public void clickBackdanhsachphim() throws InterruptedException {
        WebElement backdanhsachphim = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")));
        backdanhsachphim.click();
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Back ListMovie After EditMove",true);
    }

    public void clickDelteleMovie() throws InterruptedException {
            WebElement clickDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.Button[2]"
            )));
            clickDelete.click();

            WebElement clickYes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[2]"
            )));
            clickYes.click();
            Thread.sleep(4000);
            TestReport.testReport(appiumDriver,true,"Delete Movie",true);
    }
}
