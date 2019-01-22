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

        TestReport.testReport(appiumDriver,result,"Kiểm tra tên phim vừa tạo, nếu đúng phim vừa tạo hiện Button SỬA - XÓA",true);
        return  result;
    }

    public void clickEditMovie(){
            WebElement clickEdit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.Button[1]")));
            clickEdit.click();

        TestReport.testReport(appiumDriver,true,"Click SỬA",true);
        }

    public void editNameMovie(String name) throws Exception {
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.id("edtTenPhim")));
        inputName.sendKeys(name);

        srollingDown2(appiumDriver);
        TestReport.testReport(appiumDriver,true,"Click Button Sửa phim",true);
        WebElement buttonsuaphim = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.Button")));
        buttonsuaphim.click();

        Thread.sleep(4000);
    }

    public boolean checkNameAfterEdit(String name){
        WebElement detailsname = wait.until(ExpectedConditions.elementToBeClickable(By.id(
                "chitiet_tenphim")));
        String mdetails = detailsname.getText();
        boolean result = mdetails.contains(name);
        TestReport.testReport(appiumDriver,result,"Kiểm tra tên phim sau khi sửa",true);
        return  result;
    }

    public void clickBackdanhsachphim() throws InterruptedException {
        WebElement backdanhsachphim = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")));
        backdanhsachphim.click();
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Trở về danh sách phim sau khi sửa phim",true);
    }

    public void clickDelteleMovie() throws InterruptedException {
            WebElement clickDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.Button[2]"
            )));
            clickDelete.click();

            Thread.sleep(3000);
            WebElement clickYes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[2]"
            )));
        TestReport.testReport(appiumDriver,true,"Click Có",true);
            clickYes.click();
            Thread.sleep(4000);

            TestReport.testReport(appiumDriver,true,"Xóa phim, phim đã xóa biến mất khỏi danh sách phim",true);
    }
}
