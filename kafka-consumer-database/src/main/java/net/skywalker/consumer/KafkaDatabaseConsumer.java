package net.skywalker.consumer;

import net.skywalker.consumer.entity.WikimediaData;
import net.skywalker.consumer.repository.WikiMediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikiMediaDataRepository wikiMediaDataRepository;

    public KafkaDatabaseConsumer(WikiMediaDataRepository wikiMediaDataRepository) {
        this.wikiMediaDataRepository = wikiMediaDataRepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Message received -> %s", eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikiMediaDataRepository.save(wikimediaData);
    }
}
