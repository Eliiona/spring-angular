package shop.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@EnableAutoConfiguration
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public Mono<List<Item>> getItems() {
        System.out.println("Received GET '/items' request");
        return itemService.getAll();
    }

    @PostMapping("/items")
    public Mono addItem(@RequestBody Item item){
        System.out.println("Received POST '/items' request");
        return itemService.save(item);
    }

    @DeleteMapping("/items")
    public Mono deleteAllItems(){
        System.out.println("Received DELETE '/items' request");
        return itemService.deleteAll();
    }

    @PutMapping("/items/{id}")
    public Mono updateItem(@RequestBody Item itemToUpdate, @PathVariable String id) {
        System.out.println("Received PUT '/items/"+id+"' request");
        return itemService.updateState(itemToUpdate);
    }

    @DeleteMapping("/items/{id}")
    public Mono deleteItem(@PathVariable String id){
        System.out.println("Received DELETE '/items/"+id+"' request");
        return itemService.delete(id);
    }


}
