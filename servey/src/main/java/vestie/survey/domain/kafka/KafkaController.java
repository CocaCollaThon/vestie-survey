package vestie.survey.domain.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class KafkaController {

    private final KafkaProducer producer;

    @PostMapping("/kafka")
    public String sendMessage(@RequestParam("message") String message){
        this.producer.sendMessage(message);

        return "success";
    }

}
