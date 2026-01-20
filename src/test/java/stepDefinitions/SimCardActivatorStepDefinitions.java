package stepDefinitions;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
public class SimCardActivatorStepDefinitions {

    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity<String> activationResponse;
    private ResponseEntity<String> queryResponse;

    private static final String BASE_URL = "http://localhost:8080";

    @Given("the SIM activation service is running")
    public void the_sim_activation_service_is_running() {
        // No-op
    }

    @When("I submit an activation request with ICCID {string}")
    public void i_submit_an_activation_request_with_iccid(String iccid) {

        String requestBody = "{ \"iccid\": \"" + iccid + "\" }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<>(requestBody, headers);

        activationResponse = restTemplate.postForEntity(
                BASE_URL + "/activate",
                request,
                String.class
        );
    }

    @Then("the activation should be successful")
    public void the_activation_should_be_successful() {
        assertEquals(HttpStatus.OK, activationResponse.getStatusCode());
    }

    @Then("the activation should fail")
    public void the_activation_should_fail() {
        assertEquals(HttpStatus.BAD_REQUEST, activationResponse.getStatusCode());
    }

    @Then("the activation status for record ID {int} should be {string}")
    public void the_activation_status_for_record_id_should_be(int id, String expectedStatus) {

        queryResponse = restTemplate.getForEntity(
                BASE_URL + "/activation/" + id,
                String.class
        );

        assertEquals(HttpStatus.OK, queryResponse.getStatusCode());
        assertNotNull(queryResponse.getBody());
        assertTrue(queryResponse.getBody().contains(expectedStatus));
    }
}
