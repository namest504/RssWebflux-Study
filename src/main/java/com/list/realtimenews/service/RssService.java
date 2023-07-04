package com.list.realtimenews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.list.realtimenews.dto.RssDto.RssRootResponse;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Service
public class RssService {

    private WebClient webClient;
    private ObjectMapper objectMapper;

    @Autowired
    public RssService(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    public Mono<String> getRssXML() {
        return webClient.get()
                .uri("https://rss.etnews.com/Section901.xml")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<RssRootResponse> convertXMLToPOJO(Mono<String> xmlString) {
        return xmlString.flatMap(xml -> {
            String string = XML.toJSONObject(xml).toString();
            try {
                RssRootResponse rssRootResponse = objectMapper.readValue(string, RssRootResponse.class);
                return Mono.just(rssRootResponse);
            } catch (IOException e) {
                return Mono.error(new RuntimeException(e));
            }
        });
    }
}
