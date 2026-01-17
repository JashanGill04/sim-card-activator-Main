package au.com.telstra.simcardactivator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class SimActivationController {
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/activate-sim")
    public String activateSim(@RequestBody ActivateSimRequest request){
        ActuatorRequest actuatorRequest = new ActuatorRequest(request.getIccid());
        ActuatorResponse response= restTemplate.postForObject(
          "http://localhost:8444/actuate",
          actuatorRequest,
          ActuatorResponse.class
        );

        if (response != null && response.isSuccess()) {
            System.out.println("SIM Activation Successful");
            return "SIM activation successful";
        } else {
            System.out.println("SIM Activation Failed");
            return "SIM activation failed";
        }

    }



}
