package c8y.microservice.services;

import c8y.microservice.models.TimerModel;
import c8y.microservice.repository.TimerRepository;
import com.cumulocity.microservice.subscription.service.MicroserviceSubscriptionsService;
import com.cumulocity.rest.representation.application.ApplicationCollectionRepresentation;
import com.cumulocity.rest.representation.application.ApplicationMediaType;
import com.cumulocity.rest.representation.application.ApplicationReferenceRepresentation;
import com.cumulocity.rest.representation.application.ApplicationRepresentation;
import com.cumulocity.sdk.client.RestConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TenantApplicationService {

    @Autowired
    private RestConnector restConnector;
    @Autowired
    private MicroserviceSubscriptionsService microserviceSubscriptionsService;

    private final String PATH = "/tenant/tenants/management/applications";

    private final TimerRepository timerRepository;

    public TenantApplicationService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    public ApplicationReferenceRepresentation subscribe(ApplicationReferenceRepresentation reference) {
        /*
        ApplicationReferenceRepresentation applicationReference = new ApplicationReferenceRepresentation();
        ApplicationRepresentation application = new ApplicationRepresentation();
        application.setId(id);
        applicationReference.setApplication(application);
         */
        try {
            restConnector.post(PATH, ApplicationMediaType.APPLICATION_REFERENCE, reference);
        } catch (Exception e) {
            System.out.println("EE");
        }
        return null;
    }

    public ResponseEntity<?> getApplication(String id) {
        ApplicationCollectionRepresentation subscribedApps = restConnector.get(PATH, ApplicationMediaType.APPLICATION_REFERENCE_COLLECTION, ApplicationCollectionRepresentation.class);
        ApplicationRepresentation application = subscribedApps.getApplications().stream().filter(app -> app.getId().equals(id)).findFirst().orElse(null);
        return ResponseEntity.ok(application.toJSON());
    }

    public void unsunscribe(String id) {
        restConnector.delete(PATH + "/" + id);
    }

    public Iterable<TimerModel> save(TimerModel requestBody) {
        timerRepository.save(requestBody);
        return timerRepository.findAll();
    }

}
