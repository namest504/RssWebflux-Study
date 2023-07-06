package com.list.realtimenews.handler;

import com.list.realtimenews.dto.RssDto.RssData;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RssHandler {

    /**
     * RSS_LINKS.txt 파일 읽는 메소드
     * @return Flux<RssData>
     */
    public Flux<RssData> getRssLink() {
        List<RssData> result = new ArrayList<>();
        try {
            File file = new File("src/main/resources/RSS_LINKS.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);


            String line = "";
            String titleName = null;

            while ((line = bufReader.readLine()) != null) {
                RssData rssData = new RssData();
                if (!line.contains("http") && !line.equals("")) {
                    titleName = line;
                }
                Pattern pattern = Pattern.compile("^(.+)\\s+(http.+)$");

                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    rssData.setTitle(titleName);
                    rssData.setCategory(matcher.group(1));
                    rssData.setLink(matcher.group(2));
                    result.add(rssData);
                }
            }
            bufReader.close();
        } catch (
                FileNotFoundException e) {
        } catch (
                IOException e) {
        }
        return Flux.fromIterable(result);
    }
}
