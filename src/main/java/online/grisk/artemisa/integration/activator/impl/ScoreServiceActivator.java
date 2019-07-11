package online.grisk.artemisa.integration.activator.impl;

import online.grisk.artemisa.integration.activator.Activator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class ScoreServiceActivator implements Activator {

    public Map<String, Object> invoke(@Payload Map<String, Object> payload, @Headers Map<String, Object> header) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Object> attribute = new HashMap<>();
        for (int i = 0; i < new Random().nextInt(35) + 20; i++) {
            attribute.put("score_" + i, new Random().nextInt(35000) + 1 + "");
        }
        payload.put("scoreRisk", attribute);
        return payload;
    }
}