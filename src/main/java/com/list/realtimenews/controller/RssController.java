package com.list.realtimenews.controller;

import com.list.realtimenews.dto.RssDto.RssRootResponse;
import com.list.realtimenews.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RssController {

    private RssService rssService;

    @Autowired
    public RssController(RssService rssService) {
        this.rssService = rssService;
    }

    @GetMapping("/")
    public Mono<RssRootResponse> getRssData() {
        Mono<String> rssXML = rssService.getRssXML();
        return rssService.convertXMLToPOJO(rssXML);
    }
}
