package com.xkupc.crawler.service;

import com.xkupc.crawler.BaseTestService;
import com.xkupc.crawler.model.BaseModel;
import com.xkupc.crawler.model.TcVideo;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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

        System.setProperty("webdriver.chrome.driver", "D:/tools/chromedriver/chromedriver.exe");//这一步必不可少

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.get("https://v.qq.com/");
        try {
            WebElement webElement = driver.findElement(By.xpath("//div[@class='quick_item quick_user']"));
            Actions action = new Actions(driver);
            action.moveToElement(webElement).perform();
            webElement = driver.findElement(By.xpath("//div[@class='quick_pop_btn']"));
            webElement.click();
            webElement = driver.findElement(By.xpath("//div[@class='login_btns']"));
            WebElement qqloginElement = webElement.findElement(By.xpath("//a[@class='btn_qq _login_type_item']"));
            qqloginElement.click();
            //切换iframe,使用账号密码登陆
            driver.switchTo().frame("_login_frame_quick_");
            WebElement loginElement = driver.findElement(By.xpath("//div[@class='login']"));
            WebElement loginElementWithAccount = loginElement
        } catch (Exception e) {
               System.err.println(e);
        } finally {
            if (null != driver) {
                driver.close();
            }
        }

    }
}
