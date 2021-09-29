package com.simso.Controller;


import com.simso.domain.Item;
import com.simso.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

/*    @GetMapping("api/test")
    public ResponseEntity<?> getItem(Model model){
        List<Item> items = i

        return ResponseEntity.ok(model)
    }*/
}
