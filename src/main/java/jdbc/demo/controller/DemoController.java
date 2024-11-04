package jdbc.demo.controller;

import jdbc.demo.DTO.Item;
import jdbc.demo.service.DemoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable("id") int id){
        return this.demoService.findItemById(id);
    }

    @GetMapping("/items/{item_id}/{user_id}")
    public Map<String, Object> getItemById(@PathVariable("item_id") int item_id, @PathVariable("user_id") int user_id){
        return this.demoService.findItemById(item_id, user_id);
    }

    @PostMapping("/items")
    public void saveItem(@RequestBody Item item){
        this.demoService.saveItem(item);
    }

    @PostMapping("/items/{item_id}/like/{user_id}")
    public void likeItem(@PathVariable("item_id") int item_id, @PathVariable("user_id") int user_id){
        this.demoService.likeItem(user_id, item_id);
    }

}
