package kaspi.lab.task6deliveryservice.service;

import kaspi.lab.task6deliveryservice.dto.DeliveryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class DuplicateCheckService {
    private final ReactiveStringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "idempotency:delivery:";

    public Mono<Boolean> isDuplicate(DeliveryRequest request) {
        String key = KEY_PREFIX + request.getProductId() + ":" + request.getAddress().hashCode();
        return redisTemplate.opsForValue()
                .setIfAbsent(key, "locked", Duration.ofSeconds(2))
                .map(success -> !success);
    }
}
