package test;

import org.testng.annotations.Test;
import page.DanhsachphimActivity;
import page.SignInActivity;
import page.SignUpActivity;
import page.UserProfileActivity;
import ultis.Common;

public class ProfileUserTest extends TestBase {
    @Test
    public void profileuserTest(){
        SignInActivity signInActivity = new SignInActivity(androidDriver);
        signInActivity.clickDangky();
        String name = "user_" + Common.generateUniqueString();
        String email = "user_" + Common.generateUniqueString() + "@cinema.com";
        String password = "123456";
        SignUpActivity signUpActivity = new SignUpActivity(androidDriver);
        signUpActivity.inputThongtin(name, email, password);
        signUpActivity.clickDangky();

        DanhsachphimActivity danhsachphimActivity = new DanhsachphimActivity(androidDriver);
        danhsachphimActivity.clickUserprofile();

        UserProfileActivity userProfileActivity = new UserProfileActivity(androidDriver);
        boolean result = userProfileActivity.checkUserNameEmail(name,email);
        if(result){
            testResult = testResult*1;
        }else {
            testResult = testResult *0;
        }
    }
}
