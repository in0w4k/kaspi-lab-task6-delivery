package kaspi.lab.task6deliveryservice.api;

import kaspi.lab.task6deliveryservice.dto.DeliveryRequest;
import kaspi.lab.task6deliveryservice.dto.DeliveryStatusDto;
import kaspi.lab.task6deliveryservice.service.DuplicateCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryApi {
    private final DuplicateCheckService duplicateCheckService;

    @PostMapping
    public Mono<ResponseEntity<String>> delivery(@RequestBody DeliveryRequest request) {
        return duplicateCheckService.isDuplicate(request)
                .flatMap(isDuplicate -> {
                    if (isDuplicate) {
                        return Mono.just(ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body("Duplicate request! Please wait 2 seconds."));
                    }
                    String response = "Delivery created successfully! Product ID: " + request.getProductId() + ", Address: " + request.getAddress();
                    System.out.println(response);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(response));
                });
    }

    @GetMapping("/status/{productId}")
    public Mono<DeliveryStatusDto> getStatus(@PathVariable Long productId) {
        if (productId > 100) return Mono.empty();
        String status = (productId % 2 == 0) ? "DELIVERED" : "IN_PROGRESS";
        return Mono.just(new DeliveryStatusDto(productId, status));
    }
}
