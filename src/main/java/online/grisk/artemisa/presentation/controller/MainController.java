package online.grisk.artemisa.presentation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import online.grisk.artemisa.integration.gateway.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/api/artemisa"})
@Api(value = "Consumer API Artemisa")
public class MainController {

    @Autowired
    GatewayService gateway;

    @RequestMapping(method = {RequestMethod.POST})
    @ApiOperation("Execution Transaction")
    @ApiResponses({@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 500, message = "Server Error")})
    public ResponseEntity<?> report(@NotEmpty @Payload @RequestBody Map payload,@NotEmpty @Headers @RequestHeader Map headers) {
        this.verifyParameters(payload);
        Map<String, Object> request = new HashMap<>();
        request.put("request", payload);
        Message build = MessageBuilder.withPayload(request).setHeader("action", headers.getOrDefault("action", "").toString()).build();
        Map process = gateway.process(build);
        return new ResponseEntity<>(process, HttpStatus.valueOf(Integer.parseInt(process.getOrDefault("status", "500").toString())));
    }

    private void verifyParameters(Map payload){
        Assert.notEmpty(payload, "Payload required");
    }
}