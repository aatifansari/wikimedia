package net.skywalker.consumer.repository;

import net.skywalker.consumer.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiMediaDataRepository extends JpaRepository<WikimediaData, Long> {

}
