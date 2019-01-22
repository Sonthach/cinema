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
        this.wait = new WebDriverWait(appiumDriver,10);
    }

    public boolean checkUserNameEmail(String name, String email) throws Exception {
        srollingDown2(appiumDriver);
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtnameuser")));
        String mname = inputName.getText();

        WebElement txtEmail = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtemailuser")));
        String memail =txtEmail.getText();

        boolean result = mname.equals(name) && memail.equals(email);

        TestReport.testReport(appiumDriver,result,"Check Name and Email User",true);
        return  result;
    }



    public void clickLogout() throws Exception {
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.Button")));
        Thread.sleep(4000);
        logout.click();
        Thread.sleep(4000);
        TestReport.testReport(appiumDriver,true,"Click SignOut",true);

        Thread.sleep(4000);
        WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(By.id("button1")));
        ok.click();

        TestReport.testReport(appiumDriver,true,"Click Có thì đi đến màn hình Đăng nhập",true);
    }

    public void changeUsername() throws InterruptedException {
        TestReport.testReport(appiumDriver,true,"Click vào tên để đổi tên",true);
        WebElement changeName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView")));
        changeName.click();

    }

    public void inputChangeUsername(String username) throws InterruptedException {
        WebElement inputChangeName = wait.until(ExpectedConditions.elementToBeClickable(By.id("edtnewname")));
        inputChangeName.sendKeys(username);
        Thread.sleep(2000);
        TestReport.testReport(appiumDriver,true,"Input Change Username",true);


        WebElement clickSumbit = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnxacnhandoiten")));
        clickSumbit.click();

        Thread.sleep(2000);
        TestReport.testReport(appiumDriver,true,"Submit Change Username",true);
    }

    public boolean checkChangeUsername(String name){
        WebElement inputName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView")));
        String mname = inputName.getText();

        boolean result = mname.equals(name);
        TestReport.testReport(appiumDriver,result,"Check Change Username",true);
        return  result;
    }

    public void clickChanggepassword(){
        WebElement clickpassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[2]"
        )));
        clickpassword.click();
    }

    public void inputPassword(String oldPassword,String newPassword) throws Exception {
        WebElement edtonlpassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
                "edtmatkhaucu"
        )));
        edtonlpassword.sendKeys(oldPassword);

        WebElement edtnewpassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
                "edtmatkhaumoi"
        )));
        edtnewpassword.sendKeys(newPassword);

        WebElement confirm = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
                "edtconfirmmatkhau"
        )));
        confirm.sendKeys(newPassword);

        WebElement submitchangepassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnxacnhandoimatkhau")));
        submitchangepassword.click();

    }

    public void clickSignout(){
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.id(
                "btndangxuat")));
        logout.click();
        TestReport.testReport(appiumDriver,true,"Click SignOut",true);
        WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(By.id("button1")));
        ok.click();
        TestReport.testReport(appiumDriver,true,"Click Có thì đi đến màn hình SignIn",true);
    }

    public void clickBackListMovie(){
        WebElement clickback = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")));
        clickback.click();
        TestReport.testReport(appiumDriver,true,"Trở về màn hình danh sách phim",true);
    }
}
