package vestie.survey.infrastructure.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vestie.survey.global.event.Event;
import vestie.survey.global.event.EventProducer;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaEventProducer implements EventProducer {

    private final KafkaTemplate<String, Long> template;

    @Override
    public void send(Event event, Long targetId) {
        template.send(event.topic(), targetId);
        log.info("KAFKA TOPIC [%s](이)가 전달되었습니다. TARGET_ID : [%d]"
                .formatted(event.topic(), targetId));
    }
}
