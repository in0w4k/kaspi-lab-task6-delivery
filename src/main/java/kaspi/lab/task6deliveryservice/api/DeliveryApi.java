package kaspi.lab.task6deliveryservice.api;

import kaspi.lab.task6deliveryservice.dto.DeliveryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/delivery")
public class DeliveryApi {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> delivery(@RequestBody DeliveryRequest request) {
        return Mono.fromCallable(() -> {
            String response = "Delivery created successfully! Product ID: " +
                    request.getProductId() + ", Address: " + request.getAddress();
            System.out.println(response);
            return response;
        });
    }
}
