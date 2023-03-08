package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        //Download webs
        List<String> links = new ArrayList<String>();
        links.add("https://www.bbc.com/");
        links.add("https://www.taringa.net/");
        links.add("https://www.twitch.tv/");

        links.stream().parallel().forEach(link -> getWebContent(link));

        String link = "https://www.bbc.com/";
        String result = getWebContent(link);

        System.out.println(result);
    }

    private synchronized static String getWebContent(String link) {
        System.out.println(link);
        try {
            URL url = new URL(link);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String encoding = conn.getContentEncoding();

            InputStream input = conn.getInputStream();

            Stream<String> lines = new BufferedReader(new InputStreamReader(input))
                    .lines();
            return lines.collect(Collectors.joining());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
}