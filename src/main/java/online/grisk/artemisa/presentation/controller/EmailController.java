package online.grisk.artemisa.presentation.controller;

import online.grisk.artemisa.integration.activator.impl.EmailServiceActivator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping({"/api/artemisa"})
public class EmailController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailServiceActivator emailServiceActivator;

    @PostMapping("/email")
    public ResponseEntity sendEmail(@NotEmpty @RequestBody Map<String, Object> payload, @NotNull @RequestHeader Map<String, Object> headers) throws Exception {
        if(headers.get("action") != null) {
	    	Map<String, Object> response = emailServiceActivator.invokeSendEmail(payload, headers);
	        return new ResponseEntity(response, HttpStatus.OK);
        } else {
        	//retornar que no viene action
        	return null;
        }
        
    }
}