package vestie.survey.global.event;

public interface EventProducer {

    void send(Event event, Long targetId);
}
