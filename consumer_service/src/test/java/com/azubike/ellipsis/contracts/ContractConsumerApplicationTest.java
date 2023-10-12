package com.azubike.ellipsis.contracts;


import com.azubike.ellipsis.OrderServiceConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;



@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL , ids = "com.example:producer_service:+:8090")
public class ContractConsumerApplicationTest {
    @Test
    void test() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OrderServiceConsumer.Order> response = restTemplate.
                getForEntity("http://localhost:8090/orders/1",
                        OrderServiceConsumer.Order.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());
        assertThat(requireNonNull(response.getBody()).getOrderId()).isEqualTo("1");
    }

}
