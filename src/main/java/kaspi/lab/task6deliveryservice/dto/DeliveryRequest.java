package kaspi.lab.task6deliveryservice.dto;

import lombok.Data;

@Data
public class DeliveryRequest {
    private Long productId;
    private String address;
}
