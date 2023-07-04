package com.list.realtimenews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


public class RssDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RssRootResponse {
        public Rss rss;
    }

    @Getter
    @Setter
    public static class Rss {
        public Channel channel;
    }

    @Getter
    @Setter
    public static class Channel {
        public String title;
        public String link;
        public String description;
        public String language;
        public String pubDate;
        public String lastBuildDate;
        public Image image;
        public ArrayList<Item> item = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class Image {
        public String title;
        public String url;
        public String link;
        public String description;
    }

    @Getter
    @Setter
    public static class Item {
        public String title;
        public String link;
        public String description;
        public String author;
        public Long guid;
        public String comments;
        public String pubDate;
    }
}
