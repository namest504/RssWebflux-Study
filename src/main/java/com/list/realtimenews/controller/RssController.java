package com.list.realtimenews.controller;

import com.list.realtimenews.dto.RssDto.RssRootResponse;
import com.list.realtimenews.service.RssService;
import entity.RssChannel;
import entity.RssImage;
import entity.RssItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RssController {

    private RssService rssService;

    @Autowired
    public RssController(RssService rssService) {
        this.rssService = rssService;
    }

    @GetMapping("/get")
    public Mono<RssRootResponse> getRssDataInJson() {
        Mono<String> rssXML = rssService.getRssXML();
        return rssService.convertXMLToPOJO(rssXML);
    }

    @GetMapping("/save")
    public String saveRssData() {
        Mono<RssRootResponse> rssRootResponseMono = rssService.convertXMLToPOJO(rssService.getRssXML());
        Mono<RssChannel> rssChannel = rssService.getRssChannel(rssRootResponseMono);
        Mono<RssImage> rssImage = rssService.getRssImage(rssRootResponseMono);
        Mono<List<RssItem>> rssItem = rssService.getRssItem(rssRootResponseMono);
        rssService.saveRssChannel(rssChannel).subscribe();
        rssService.saveRssImage(rssImage).subscribe();
        rssService.saveRssItem(rssItem).subscribe();
        return "true";
    }
}
