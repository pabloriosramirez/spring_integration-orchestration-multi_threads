package online.grisk.artemisa.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.grisk.artemisa.domain.entity.RiskScore;
import online.grisk.artemisa.domain.service.RiskScoreService;

@RestController
@RequestMapping({ "/api/artemisa/riskScores" })
public class RiskScoreController {
	
	@Autowired
	private RiskScoreService riskScoreService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody RiskScore riskScore) {
		try {
			return new ResponseEntity<Object>(riskScoreService.save(riskScore),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/organization/{organizationId}")
	public ResponseEntity<?> findByOrganizationId(@PathVariable(name = "organizationId", required = true) long id) {
		try {
			return new ResponseEntity<RiskScore>(riskScoreService.findByOrganization(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteByIdOrganization(@PathVariable(name = "id", required = true) long id) {
		try {
			//borrar padre e hijo (risk ratio y ratio)
			riskScoreService.deletedByOrganization(id);
			return new ResponseEntity<Object>("Business Tree deleted.",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
