package shop.reactive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "groceries")
@Data
@Builder
@AllArgsConstructor
public class Item implements Comparable<Item>{

    @Id
    private String id;
    private String name;
    private boolean bought;
    private Type type;

    // Empty constructor is needed for Jackson to deserialize JSON
    public Item() {
    }

    public Item(String name) {
        this.name = name;
        this.type = Type.other;
        this.bought = false;
    }

    public Item(String name, Type type) {
        this.name = name;
        this.type = type;
        this.bought = false;
    }

    public boolean isBought() {
        return bought;
    }

    public void updateBuy(){
        this.bought = !this.bought;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public int compareTo(Item item) {
        if (this.type == item.type){
            return this.name.compareTo(item.name);
        }
        if (this.type.ordinal() > item.type.ordinal()){
            return 1;
        }
        return -1;
    }
}
