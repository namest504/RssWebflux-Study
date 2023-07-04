package com.list.realtimenews.repository;

import entity.RssItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssItemRepository extends ReactiveCrudRepository<RssItem, Long> {
}
