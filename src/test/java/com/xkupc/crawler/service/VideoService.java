package com.xkupc.crawler.service;

import com.xkupc.crawler.BaseTestService;
import com.xkupc.crawler.model.TcVideo;
import com.xkupc.crawler.service.impl.AddressParseServiceImpl;
import org.assertj.core.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    AddressParseServiceImpl addressParseService;

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
            loginElementWithAccount.findElement(By.xpath("//div[@class='uinArea']")).findElement(By.id("u")).sendKeys("1031572219");
            loginElementWithAccount.findElement(By.xpath("//div[@class='pwdArea']")).findElement(By.id("p")).clear();
            loginElementWithAccount.findElement(By.xpath("//div[@class='pwdArea']")).findElement(By.id("p")).sendKeys("xkupctc@2016");
            loginElementWithAccount.findElement(By.xpath("//div[@class='submit']")).findElement(By.xpath("//a[@class='login_button']")).click();
            Thread.sleep(100L);
            Set<Cookie> cookies = driver.manage().getCookies();
            redisTemplate.opsForSet().add("cookie", cookies);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (null != driver) {
                driver.close();
            }
        }

    }

    @Test
    public void address() {
        WebDriver driver;

        System.setProperty("webdriver.chrome.driver", "D:/tools/chromedriver/chromedriver.exe");//这一步必不可少

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.get("http://jquerywidget.com/jquery-citys/");
        try {
            Thread.sleep(1000);
            //Map<String, String> provinceMap = parseCityHtml(driver.getPageSource(), "province");
            Map<String, String> provinceMap = province();
            redisTemplate.opsForHash().putAll("province", provinceMap);
            redisTemplate.expire("province", 1, TimeUnit.DAYS);
            driver.get("http://jquerywidget.com/jquery-citys/");
            WebElement webElement = driver.findElement(By.cssSelector("div#demo3.citys"));
            Select sProvince = new Select(webElement.findElement(By.cssSelector("p>select[name=province]")));
            Select sCity = new Select(webElement.findElement(By.cssSelector("p>select[name=city]")));
            Select sArea = new Select(webElement.findElement(By.cssSelector("p>select[name=area]")));
            for (Map.Entry entry : provinceMap.entrySet()) {
                sProvince.selectByValue(entry.getKey().toString());
                Thread.sleep(1000);
                String provinceSource = driver.getPageSource();
                Map<String, String> map = parseProvince(provinceSource, entry.getKey().toString(), entry.getValue().toString());
                if (null == map || map.isEmpty()) {
                    Map<String, String> areaMap = parseCityHtml(provinceSource, "area");
                    if (null == areaMap || areaMap.isEmpty()) {
                        continue;
                    }
                    redisTemplate.opsForHash().putAll(entry.getKey().toString(), areaMap);
                    redisTemplate.expire(entry.getKey().toString(), 1, TimeUnit.DAYS);
                    for (Map.Entry areaEntry : areaMap.entrySet()) {
                        sArea.selectByValue(areaEntry.getKey().toString());
                        Thread.sleep(1000);
                        String areaSource = driver.getPageSource();
                        Map<String, String> streetMap = parseCityHtml(areaSource, "town");
                        if (null == streetMap) {
                            continue;
                        }
                        redisTemplate.opsForHash().putAll(areaEntry.getKey().toString(), streetMap);
                        redisTemplate.expire(areaEntry.getKey().toString(), 1, TimeUnit.DAYS);
                    }
                }
                redisTemplate.opsForHash().putAll(entry.getKey().toString(), map);
                redisTemplate.expire(entry.getKey().toString(), 1, TimeUnit.DAYS);
                for (Map.Entry cityEntry : map.entrySet()) {
                    sCity.selectByValue(cityEntry.getKey().toString());
                    Thread.sleep(1000);
                    String citySource = driver.getPageSource();
                    Map<String, String> areaMap = parseCityHtml(citySource, "area");
                    if (null == areaMap) {
                        continue;
                    }
                    redisTemplate.opsForHash().putAll(cityEntry.getKey().toString(), areaMap);
                    redisTemplate.expire(cityEntry.getKey().toString(), 1, TimeUnit.DAYS);
                    for (Map.Entry areaEntry : areaMap.entrySet()) {
                        sArea.selectByValue(areaEntry.getKey().toString());
                        Thread.sleep(1000);
                        String areaSource = driver.getPageSource();
                        Map<String, String> streetMap = parseCityHtml(areaSource, "town");
                        if (null == streetMap) {
                            continue;
                        }
                        redisTemplate.opsForHash().putAll(areaEntry.getKey().toString(), streetMap);
                        redisTemplate.expire(areaEntry.getKey().toString(), 1, TimeUnit.DAYS);
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != driver) {
                driver.close();
            }
        }

    }

    private Map<String, String> parseProvince(String html, String provinceId, String provinceName) {
        if (Strings.isNullOrEmpty(html)) {
            return null;
        }
        Document document = Jsoup.parse(html);
        if (null == document) {
            return null;
        }
        Map<String, String> cityMap = new HashMap<>(15);
        Elements cityElements = document.select("div[id=demo3]").select("p").select("select[name=city]");
        if (null == cityElements) {
            //直辖市
            return null;
        } else {
            for (Element element : cityElements.get(0).children()) {
                cityMap.put(element.attr("value"), element.text());
            }
        }
        return cityMap;
    }

    private Map<String, String> parseCityHtml(String html, String name) {
        if (Strings.isNullOrEmpty(html)) {
            return null;
        }
        Document document = Jsoup.parse(html);
        if (null == document) {
            return null;
        }
        Map<String, String> areaMap = new HashMap<>(15);
        Elements areaElements = document.select("div[id=demo3]").select("p").select("select[name={}]".replace("{}", name));
        if (null == areaElements) {
            return null;
        } else {
            for (Element element : areaElements.get(0).children()) {
                areaMap.put(element.attr("value"), element.text());
            }
        }
        return areaMap;
    }

    private Map<String, String> province() {
        Map<String, String> map = new HashMap<>();
        map.put("110000", "北京市");
        map.put("120000", "天津市");
        map.put("310000", "上海市");
        map.put("500000", "重庆市");
        map.put("810000", "香港特别行政区");
        map.put("820000", "澳门特别行政区");
        return map;
    }
}
