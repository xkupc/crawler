package com.xkupc.crawler.service;

import com.xkupc.crawler.BaseTestService;
import com.xkupc.crawler.model.BaseModel;
import com.xkupc.crawler.model.TcVideo;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xk
 * @createTime 2017/11/7 0007 上午 9:56
 * @description
 */
public class VideoService extends BaseTestService {

    @Autowired
    TcVideoService tcVideoService;

    @Test
    public void test() {
        CrawlerFactory crawlerFactory = new CrawlerFactory();
        List<TcVideo> videoList = crawlerFactory.getResult("https://v.qq.com/x/channel/movie?ptag=film.qq.com");
        tcVideoService.addVideoList(videoList);
    }

    @Test
    public void login() {
        WebDriver driver;

        System.setProperty("webdriver.chrome.driver", "D:/work/worktool/chromedriver/chromedriver.exe");//这一步必不可少

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.get("https://v.qq.com/");
        try {
            WebElement webElement = driver.findElement(By.xpath("//div[@class='quick_item quick_user']"));
            Actions action = new Actions(driver);
            action.moveToElement(webElement).perform();
            Thread.sleep(500L);
            webElement = driver.findElement(By.xpath("//div[@class='quick_pop_btn']"));
            webElement.click();
            webElement = driver.findElement(By.xpath("//div[@class='login_btns']"));
            WebElement qqloginElement = webElement.findElement(By.xpath("//a[@class='btn_qq _login_type_item']"));
            qqloginElement.click();
            Thread.sleep(500L);
            //切换iframe,使用账号密码登陆
            driver.switchTo().frame("_login_frame_quick_");
            WebElement loginElement = driver.findElement(By.xpath("//div[@class='login']"));
            WebElement loginElementWithAccount = loginElement.findElement(By.xpath("//div[@class='bottom hide']"));
            loginElementWithAccount = loginElementWithAccount.findElement(By.xpath("//a[@id='switcher_plogin']"));
            loginElementWithAccount.click();
            loginElementWithAccount = driver.findElement(By.xpath("//div[@class='web_qr_login']"));
            loginElementWithAccount = loginElementWithAccount.findElement(By.xpath("//div[@class='web_qr_login_show']")).findElement(By.xpath("//div[@class='web_login']")).
                    findElement(By.xpath("//div[@class='login_form']"));
            loginElementWithAccount.findElement(By.xpath("//div[@class='uinArea']")).findElement(By.id("u")).clear();
            loginElementWithAccount.findElement(By.xpath("//div[@class='uinArea']")).findElement(By.id("u")).sendKeys("xxxxxx");
            loginElementWithAccount.findElement(By.xpath("//div[@class='pwdArea']")).findElement(By.id("p")).clear();
            loginElementWithAccount.findElement(By.xpath("//div[@class='pwdArea']")).findElement(By.id("p")).sendKeys("xxxxxx");
            loginElementWithAccount.findElement(By.xpath("//div[@class='submit']")).findElement(By.xpath("//a[@class='login_button']")).click();
            Thread.sleep(100L);
            Set<Cookie> cookies = driver.manage().getCookies();
            for (Cookie cookie:cookies){

            }
        } catch (Exception e) {
               System.err.println(e);
        } finally {
            if (null != driver) {
                driver.close();
            }
        }

    }
}
