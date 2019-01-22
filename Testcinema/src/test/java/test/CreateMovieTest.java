package test;

import org.testng.annotations.Test;
import page.*;
import ultis.Common;

import static org.testng.Assert.assertEquals;

public class CreateMovieTest extends TestBase {

    //@Test
    public void taoPhimtest(){
        try {
            SignInActivity signInActivity = new SignInActivity(androidDriver);
            signInActivity.clickDangky();
            String name = "user_" + Common.generateUniqueString();
            String email = "user_" + Common.generateUniqueString() + "@cinema.com";
            String password = "123456";
            SignUpActivity signUpActivity = new SignUpActivity(androidDriver);
            signUpActivity.inputThongtin(name, email, password);
            signUpActivity.clickDangky();

            DanhsachphimActivity danhsachphimActivity = new DanhsachphimActivity(androidDriver);
            danhsachphimActivity.clickftcreatemovie();
            String tenphim = "Movie_" + Common.generateUniqueString();
            String mota = "Mota_" + Common.generateUniqueString();
            CreateMovieActivity createMovieActivity = new CreateMovieActivity(androidDriver);
            createMovieActivity.createmovie(tenphim, mota);

            boolean result = danhsachphimActivity.checkNameMovie(tenphim);
            if(result){
                testResult = testResult*1;
            }else {
                testResult = testResult *0;
            }
        }catch (Exception e){
            testResult = testResult * 0;
            e.printStackTrace();
        }finally {
            assertEquals(testResult, 1);
        }

    }

    @Test
    public void suaPhimTest(){
        try {
            SignInActivity signInActivity = new SignInActivity(androidDriver);
            signInActivity.clickDangky();
            String name = "user_" + Common.generateUniqueString();
            String email = "user_" + Common.generateUniqueString() + "@cinema.com";
            String password = "123456";
            SignUpActivity signUpActivity = new SignUpActivity(androidDriver);
            signUpActivity.inputThongtin(name, email, password);
            signUpActivity.clickDangky();

            DanhsachphimActivity danhsachphimActivity = new DanhsachphimActivity(androidDriver);
            danhsachphimActivity.clickftcreatemovie();
            String tenphim = "Movie_" + Common.generateUniqueString();
            String mota = "Mota_" + Common.generateUniqueString();
            CreateMovieActivity createMovieActivity = new CreateMovieActivity(androidDriver);
            createMovieActivity.createmovie(tenphim, mota);

            boolean result = danhsachphimActivity.checkNameMovie(tenphim);
            if(result){
                testResult = testResult*1;
            }else {
                testResult = testResult *0;
            }

            danhsachphimActivity.details();

            DetailsActivity detailsActivity = new DetailsActivity(androidDriver);
            result = detailsActivity.checkDetailsName(tenphim);
            if(result){
                testResult = testResult *1;
            }else {
                testResult = testResult *0;
            }

            detailsActivity.clickEditMovie();

            detailsActivity.editNameMovie(tenphim);

            result = detailsActivity.checkNameAfterEdit(tenphim);
            if(result){
                testResult = testResult *1;
            }else {
                testResult = testResult*0;
            }

            detailsActivity.clickBackdanhsachphim();

            result = danhsachphimActivity.checkNameMovie(tenphim);
            if(result){
                testResult = testResult *1;
            }else {
                testResult = testResult *0;
            }

            danhsachphimActivity.details();

            result = detailsActivity.checkDetailsName(tenphim);
            if(result){
                testResult = testResult *1;
            }else {
                testResult = testResult *0;
            }
            detailsActivity.clickDelteleMovie();

            result = danhsachphimActivity.checkNameMovie(tenphim);
            if(!result){
                testResult = testResult *1;
            }else {
                testResult = testResult *0;
            }

        }catch (Exception e){
            testResult = testResult * 0;
            e.printStackTrace();
        }finally {
            assertEquals(testResult, 1);
        }

    }
}
