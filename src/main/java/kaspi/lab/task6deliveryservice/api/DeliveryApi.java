package kaspi.lab.task6deliveryservice.api;

import kaspi.lab.task6deliveryservice.dto.DeliveryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryApi {
    @PostMapping
    public ResponseEntity<String> delivery(@RequestBody DeliveryRequest request) {
        String response = "Delivery created successfully! Product ID: " + request.getProductId() + ", Address: " + request.getAddress();
        System.out.println(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
