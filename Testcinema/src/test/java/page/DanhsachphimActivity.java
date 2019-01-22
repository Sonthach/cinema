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

    public void clickUserprofile() throws Exception {

        WebElement ftuserprofile = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnuserprofile")));
        ftuserprofile.click();
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Đi đến user profile",true);
    }

    public void clickftcreatemovie(){
        WebElement fttaophim = wait.until(ExpectedConditions.elementToBeClickable(By.id("btntaophim"
        )));
        fttaophim.click();
        TestReport.testReport(appiumDriver,true,"Đi đến Tạo phim",true);
    }

    public boolean checkNameMovie(String name){
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.TextView[1]")));
        String mname = inputName.getText();

        boolean result = mname.equals(name);
        TestReport.testReport(appiumDriver,result,"Kiểm tra tên phim",true);
        return  result;
    }

    public void details() throws InterruptedException {
        WebElement clickDetails = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.ImageView")));
        Thread.sleep(4000);
        clickDetails.click();
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Kiểm tra tên phim ngoài danh sách",true);
    }

    public void searchMovie(String moviename) throws InterruptedException {
        WebElement clicksearch = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_button")));
        clicksearch.click();
        TestReport.testReport(appiumDriver,true,"Khi bấm vào kính lúp",true);

        WebElement inputtext = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_src_text")));
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Điền tên phim cần tìm",true);
        inputtext.sendKeys(moviename);


        WebElement closesearch = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_close_btn")));
        closesearch.click();
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Khi không điền gì hết",true);
    }

}
