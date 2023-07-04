package com.list.realtimenews.repository;

import entity.RssChannel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssChannelRepository extends ReactiveCrudRepository<RssChannel, Long> {
}
