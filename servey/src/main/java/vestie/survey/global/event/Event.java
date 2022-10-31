package vestie.survey.global.event;

public enum Event {
    SURVEY_CREATE("survey-create"),
    SURVEY_UPDATE("survey-update"),
    SURVEY_DELETE("survey-delete"),
    ;

    private final String topic;

    Event(String topic) {
        this.topic = topic;
    }

    public String topic() {
        return topic;
    }
}
