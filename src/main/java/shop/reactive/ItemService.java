package shop.reactive;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class ItemService {

    private final ItemMongoRepository repository;

    public Mono<List<Item>> getAll(){
        Flux<Item> itemFlux = repository.findAll().switchIfEmpty(Flux.empty());
        Mono<List<Item>> itemMono = itemFlux.collectSortedList();
        return itemMono;
    }
    public Mono<Item> getById(final String id){

        return repository.findById(id);
    }
    public Mono updateState (final Item item){
        item.updateBuy();
        return this.save(item);
    }
    public Mono save(final Item item){
        if (item.getType()==null){
            item.setType(Type.other);
        }
        System.out.println("Adding item to repo");
        return repository.save(item);
    }

    public Mono delete(final String id){
        final Mono<Item> dbItem = getById(id);
        System.out.println("Deleting an item: "+ id);
        if (Objects.isNull(dbItem)){
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull)
                .flatMap(itemToBeDeleted -> repository.delete(itemToBeDeleted).then(Mono.just(itemToBeDeleted)));
    }

    public Mono deleteAll(){
        System.out.println("Deleting all items");
        return this.repository.deleteAll();
    }
}
