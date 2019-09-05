package shop.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final ItemMongoRepository itemMongoRepository;

    public DataInitializer(ItemMongoRepository itemMongoRepository) {
        this.itemMongoRepository = itemMongoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
