package com.list.realtimenews.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.list.realtimenews.dto.RssDto;
import com.list.realtimenews.dto.RssDto.Item;
import com.list.realtimenews.dto.RssDto.RssRootResponse;
import com.list.realtimenews.repository.RssChannelRepository;
import com.list.realtimenews.repository.RssImageRepository;
import com.list.realtimenews.repository.RssItemRepository;
import entity.RssChannel;
import entity.RssImage;
import entity.RssItem;
import lombok.extern.slf4j.Slf4j;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

import static entity.RssItem.convertToRssItems;

@Slf4j
@Service
public class RssService {

    private WebClient webClient;
    private ObjectMapper objectMapper;
    private RssChannelRepository rssChannelRepository;
    private RssImageRepository rssImageRepository;
    private RssItemRepository rssItemRepository;

    @Autowired
    public RssService(WebClient webClient, ObjectMapper objectMapper, RssChannelRepository rssChannelRepository, RssImageRepository rssImageRepository, RssItemRepository rssItemRepository) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
        this.rssChannelRepository = rssChannelRepository;
        this.rssImageRepository = rssImageRepository;
        this.rssItemRepository = rssItemRepository;

    }

    public Mono<String> getRssXML() {
        return webClient.get()
                .uri("http://myhome.chosun.com/rss/www_section_rss.xml")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<RssRootResponse> convertXMLToPOJO(Mono<String> xmlString) {
        return xmlString.flatMap(xml -> {
            String string = XML.toJSONObject(xml).toString();
            try {
                RssRootResponse rssRootResponse = objectMapper
                        .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                        .readValue(string, RssRootResponse.class);
                return Mono.just(rssRootResponse);
            } catch (IOException e) {
                return Mono.error(new RuntimeException(e));
            }
        });
    }

    public Mono<RssChannel> getRssChannel(Mono<RssRootResponse> rssRootResponseMono) {
        return rssRootResponseMono.flatMap( x -> Mono.just(RssChannel.of(x.getRss().getChannel())));
    }

    public Mono<RssImage> getRssImage(Mono<RssRootResponse> rssRootResponseMono) {
        return rssRootResponseMono.flatMap(x -> {
            RssDto.Image image = x.getRss().getChannel().getImage();
            if (image != null) {
                return Mono.just(RssImage.of(image));
            } else {
                return Mono.empty();
            }
        }).onErrorResume(e -> {
            return Mono.error(new Exception(e));
        });
    }

    public Mono<List<RssItem>> getRssItem(Mono<RssRootResponse> rssRootResponseMono) {
        return rssRootResponseMono.map(r -> {
            List<Item> items = r.getRss().getChannel().getItem();
            List<RssItem> rssItems = convertToRssItems(items);
            return rssItems;
        });
    }

    public Mono<RssChannel> saveRssChannel(Mono<RssChannel> rssChannel) {
        log.info("saveRssChannel 실행");
        return rssChannel.flatMap( r ->
                rssChannelRepository.save(RssChannel.builder()
                            .title(r.getTitle())
                            .link(r.getLink())
                            .description(r.getDescription())
                            .language(r.getLanguage())
                            .pubDate(r.getPubDate())
                            .lastBuildDate(r.getLastBuildDate())
                            .build()));
    }

    public Mono<RssImage> saveRssImage(Mono<RssImage> rssImage) {
        log.info("saveRssImage 실행");
        return rssImage.flatMap( r ->
            rssImageRepository.save(RssImage.builder()
                            .title(r.getTitle())
                            .url(r.getUrl())
                            .link(r.getLink())
                            .description(r.getDescription())
                    .build())
        );
    }

    public Flux<RssItem> saveRssItem(Mono<List<RssItem>> rssItems) {
        log.info("saveRssItem 실행");
        return rssItems.flatMapMany(Flux::fromIterable)
                .flatMap( r -> rssItemRepository.save(r));

    }
}
