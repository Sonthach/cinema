package test;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import page.DanhsachphimActivity;
import page.SignInActivity;
import page.SignUpActivity;
import ultis.Common;
import ultis.TestReport;

public class SearchTest extends TestBase {
    AppiumDriver appiumDriver;
    @Test
    public void searchTest() throws InterruptedException {
        SignInActivity signInActivity = new SignInActivity(androidDriver);
        signInActivity.clickDangky();
        String name = "user_" + Common.generateUniqueString();
        String email = "user_" + Common.generateUniqueString() + "@cinema.com";
        String password = "123456";
        SignUpActivity signUpActivity = new SignUpActivity(androidDriver);
        signUpActivity.inputThongtin(name, email, password);
        signUpActivity.clickDangky();

        String Query = "SpiderMan";
        Thread.sleep(3000);
        DanhsachphimActivity danhsachphimActivity = new DanhsachphimActivity(androidDriver);
        TestReport.testReport(appiumDriver,true,"SignIn",true);
        danhsachphimActivity.searchMovie(Query);
    }
}
