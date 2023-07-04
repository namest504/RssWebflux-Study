package com.list.realtimenews.repository;

import entity.RssImage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssImageRepository extends ReactiveCrudRepository<RssImage, Long> {
}
