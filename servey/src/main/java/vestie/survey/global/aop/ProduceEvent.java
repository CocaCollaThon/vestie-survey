package vestie.survey.global.aop;

import vestie.survey.global.event.Event;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 메서드에 사용하며, 이때 메서의 반환 타입은 반드시 Long이어야 한다.
 * 예시 :
 *  @ProduceEvent(event = Event.SURVEY_CREATE)
 *  public Long save() {
 *      // save
 *      return surveyId;
 *  }
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface ProduceEvent {

    Event event();
}
