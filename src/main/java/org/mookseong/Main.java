package org.mookseong;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try {
            String URL = "https://lib.bible.ac.kr/";
            Connection conn = Jsoup.connect(URL);

            Document document = conn.get();

            Element elementsByClass = document.getElementsByClass("col-md-9 sponge-main-book book").get(0);
            Elements parsingBestList = Objects.requireNonNull(elementsByClass.getElementById("book-best")).select("ul").select("li").select("a");
            System.out.println(parsingBestList);
            System.out.println("href : " + parsingBestList.get(0).attr("href"));
            System.out.println("src : " + parsingBestList.get(0).getElementsByTag("img").attr("src"));
            System.out.println("span : " + parsingBestList.get(0).getElementsByTag("span").text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}