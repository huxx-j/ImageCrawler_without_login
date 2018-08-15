package com.huxx.src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    protected void imageDownloader(int index, String title, String date, String imageUrl){
        String dirPath = System.getProperty("user.home") + "\\Desktop\\" + date;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File outputFile = new File(dirPath + "/" + title + "_" + index + ".png");

        URL url;
        BufferedImage bi;

            try {
                url = new URL(imageUrl);
                bi = ImageIO.read(url);
                ImageIO.write(bi, "png", outputFile);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
