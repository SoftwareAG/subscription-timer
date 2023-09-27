package c8y.microservice.controllers;

import c8y.microservice.models.TimerModel;
import c8y.microservice.services.TenantApplicationService;
import com.cumulocity.rest.representation.application.ApplicationReferenceRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setup")
public class TenantApplicationsController {

    @Autowired
    private TenantApplicationService service;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribeApplication(@RequestBody ApplicationReferenceRepresentation requestBody) {
        ApplicationReferenceRepresentation response = service.subscribe(requestBody);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/unsubscribe/{id}")
    public ResponseEntity<?> unsubscribeApplication(@PathVariable String id) {
        service.unsunscribe(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/subscribeWithTimer")
    public ResponseEntity<?> subscribeWithTimer(@RequestBody TimerModel requestBody) {
        //ObjectMapper mapper = new ObjectMapper()
        //save(requestBody);
        return new ResponseEntity<>(service.save(requestBody), HttpStatus.CREATED);
    }


}
