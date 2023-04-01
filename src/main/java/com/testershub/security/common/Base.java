package com.testershub.security.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

public class Base {

    static final String ZAP_PROXY_ADDRESS ="localhost";
    static final int ZAP_PROXY_PORT =8080;
    static final String ZAP_API_KEY ="2tcnolno7mvot9opmjrm8okmh1";

    private WebDriver driver;
    private ClientApi clientApi;

    @Before
    public void setUp(){
        String proxyURL = ZAP_PROXY_ADDRESS +":"+ZAP_PROXY_PORT;
        Proxy proxy=new Proxy();
        proxy.setHttpProxy(proxyURL);
        proxy.setSslProxy(proxyURL);
        ChromeOptions options=new ChromeOptions();
        options.setProxy(proxy);
        options.setAcceptInsecureCerts(true);
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver(options);
        clientApi = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
    }



    @Test
    public void verifySecurityTest(){
        driver.get("https://www.jawwy.tv/eg-en");
    }


    @After
    public void tearDown(){
        if(clientApi != null){
            try {
                ApiResponse generate = clientApi.reports.generate("Jawwy TV", "traditional-html", null, "Zap security Report",
                        null, null, null, null, null, "fileName_Jawwy1.html",
                        null, System.getProperty("user.dir"), null);
            } catch (ClientApiException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
