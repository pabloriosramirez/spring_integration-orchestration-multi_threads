package online.grisk.artemisa.integration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.Message;

import java.util.Map;

public interface GatewayService {
    @Gateway
    Map<String, Object> process(Message<Map<String, Object>> payload);
}
