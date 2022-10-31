package vestie.survey.global.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import vestie.survey.global.event.EventProducer;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class EventAspect {

    private final EventProducer eventProducer;

    @AfterReturning(value = "@annotation(vestie.survey.global.aop.ProduceEvent)", returning = "targetId")
    public void produceEvent(JoinPoint joinPoint, Long targetId) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ProduceEvent annotation = method.getAnnotation(ProduceEvent.class);
        eventProducer.send(annotation.event(), targetId);
    }
}