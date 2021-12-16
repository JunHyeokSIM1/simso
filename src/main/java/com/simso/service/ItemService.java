package com.simso.service;

import com.simso.domain.Item;
import com.simso.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ItemService {


    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private final ItemRepository itemRepository;

    public Long register(Item item){

        getValidateDuplicateItem(item);
        itemRepository.save(item);
        return item.getId();
    }

    private void getValidateDuplicateItem(Item item) {
        itemRepository.findByname(item.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("상품 코드가 중복됩니다.");
                });

    }
    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Optional<Item> findOne(Long itemId){
        return itemRepository.findByid(itemId);
    }

    public  void DeleteItem(Long itemId){
        itemRepository.delete(itemId);
    }
}