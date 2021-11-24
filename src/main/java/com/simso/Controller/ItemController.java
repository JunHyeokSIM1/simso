package com.simso.Controller;


import com.simso.domain.Item;
import com.simso.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("api/get")
    public ResponseEntity<?> getItem(Model model){
        List<Item> items = itemService.findItem();

        model.addAttribute("items", items);
        return ResponseEntity.ok(model);
    }


    @PostMapping("api/item/add")
    public ResponseEntity<?> addItem(@RequestBody Item item){

        itemService.register(item);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @GetMapping("api/item/findId")
    public ResponseEntity<?> findItem(@RequestParam Long id , Model model){


        Optional<Item> items = itemService.findOne(id);

        model.addAttribute("items", items);
        return ResponseEntity.ok(model);
    }
    @DeleteMapping("api/item/deleteId")
    public ResponseEntity<?> deleteId(@RequestParam Long id){

        itemService.DeleteItem(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
