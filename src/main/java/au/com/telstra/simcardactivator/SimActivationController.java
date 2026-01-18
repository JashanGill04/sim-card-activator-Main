package au.com.telstra.simcardactivator;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class SimActivationController {
    private RestTemplate restTemplate = new RestTemplate();
    private final SimCardActivationRepository repository;

    public SimActivationController(RestTemplate restTemplate,
                                   SimCardActivationRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }


    @PostMapping("/activate-sim")
    public String activateSim(@RequestBody ActivateSimRequest request){
        ActuatorRequest actuatorRequest = new ActuatorRequest(request.getIccid());
        ActuatorResponse response= restTemplate.postForObject(
          "http://localhost:8444/actuate",
          actuatorRequest,
          ActuatorResponse.class
        );

        boolean success = response != null && response.isSuccess();

        // Save to DB
        SimCardActivation entity = new SimCardActivation(
                request.getIccid(),
                request.getCustomerEmail(),
                success
        );

        repository.save(entity);
        return success ? "SIM activation successful"
                : "SIM activation failed";
    }

    @GetMapping("/sim-card")
    public SimCardActivation getSimCard(@RequestParam Long simCardId) {
        return repository.findById(simCardId)
                .orElseThrow(() -> new RuntimeException("SIM not found"));
    }
}
