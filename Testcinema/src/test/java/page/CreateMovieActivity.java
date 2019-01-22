package page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultis.TestReport;

public class CreateMovieActivity extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    public CreateMovieActivity(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver,this);
        this.wait = new WebDriverWait(appiumDriver,30);
    }

    public void createmovie(String tenphim,String mota) throws Exception {
        Thread.sleep(4000);
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.id("edtTenPhim")));
        inputName.sendKeys(tenphim);
//
//        WebElement theloai = wait.until(ExpectedConditions.elementToBeClickable(By.id("text1")));
//        theloai.click();
//        theloai.sendKeys(Keys.ARROW_DOWN);
//        theloai.sendKeys(Keys.ENTER);

        srollingDown2(appiumDriver);
        WebElement inputcontent   = wait.until(ExpectedConditions.elementToBeClickable(By.id("edtMoTa")));
        inputcontent.sendKeys(mota);

        TestReport.testReport(appiumDriver,true,"Tạo phim",true);

        WebElement buttontaophim   = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnTaoPhim")));
        buttontaophim.click();

        Thread.sleep(2000);
        TestReport.testReport(appiumDriver,true,"Click Tạo phim",true);

    }
}
