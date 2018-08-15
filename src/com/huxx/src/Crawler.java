package com.huxx.src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Crawler {
//        public static void main(String args[]) {
    protected void imageCrawler(String cafeUrl) throws InterruptedException {
            Downloader downloader = new Downloader();

            WebDriver driver;
            System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
            driver = new ChromeDriver();

            // 크롤링할 페이지로 이동
            driver.get(cafeUrl); // 대상 페이지 URL
            driver.switchTo().frame("cafe_main"); // 소스중에 cafe_main이라는 frame으로 이동시키는 코드

            // 이미지 소스 찾는 코드
            WebElement imgTag = driver.findElement(By.id("tbody")); // 태그중에 id 가 "tbody"인걸 찾아서 그 태그만 잘라서 가져오는 코드
            List<WebElement> imgTagList = imgTag.findElements(By.tagName("img")); // 위에서 짤라온 소스중에 tagName이 "img"인걸 잘라서 가져오는 코드

            // 제목 찾는 코드
            WebElement titleAreaTag = driver.findElement(By.className("tit-box"));
            WebElement titleTag = titleAreaTag.findElement(By.className("m-tcol-c"));
            String tmpTitle = titleTag.getText();
            String restrictChars = "[|\\\\?*<\":>/ ]"; // 파일명에 금지된 문자
            String title = tmpTitle.replaceAll(restrictChars, ""); //금지된 문자열을 지우는 코드

            // 업로드 날자 찾는 코드
            WebElement dateTag = driver.findElement(By.className("date"));
            String dateArr[] = dateTag.getText().split(" ");
            String date = dateArr[0].replace(".","");

            // 이미지를 다운 받는 코드
            for (int i = 0 ; i < imgTagList.size() ; i++) {
                String url = imgTagList.get(i).getAttribute("src");
                downloader.imageDownloader(i+1, title, date, url);
            }

            driver.quit(); // 웹브라우져 닫음
        }
    }

