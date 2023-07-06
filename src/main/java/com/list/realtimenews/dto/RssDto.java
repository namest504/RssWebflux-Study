package com.list.realtimenews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class RssDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RssData {
        private String title;
        private String category;
        private String link;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RssRootResponse {
        private Rss rss;
    }

    @Getter
    @Setter
    public static class Rss {
        private Channel channel;
    }

    @Getter
    @Setter
    public static class Channel {
        private String title;
        private String link;
        private String description;
        private String language;
        private String pubDate;
        private String lastBuildDate;
        private Image image;
        private List<Item> item = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class Image {
        private String title;
        private String url;
        private String link;
        private String description;
    }

    @Getter
    @Setter
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String author;
        private Long guid;
        private String comments;
        private String pubDate;
    }
}
