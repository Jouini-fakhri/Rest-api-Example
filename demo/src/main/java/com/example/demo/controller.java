import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private List<Item> itemList = new ArrayList<>();

    @GetMapping
    public List<Item> getAllItems() {
        return itemList;
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable long id) {
        return itemList.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        itemList.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable long id, @RequestBody Item updatedItem) {
        Item item = itemList.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setName(updatedItem.getName());
            item.setDescription(updatedItem.getDescription());
        }

        return item;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable long id) {
        itemList.removeIf(item -> item.getId() == id);
    }
}
