package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.ItemDto;
import com.swichfully.chicodespons.oorder.security.Feature;
import com.swichfully.chicodespons.oorder.security.SecurityService;
import com.swichfully.chicodespons.oorder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final SecurityService securityService;
    private final ItemService itemService;

    public ItemController(SecurityService securityService, ItemService itemService) {
        this.securityService = securityService;
        this.itemService = itemService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addANewItem(@RequestHeader String authorization, @RequestBody ItemDto itemDto){
        securityService.validateAuthorization(authorization, Feature.ADD_NEW_ITEM);
        return itemService.addANewItem(itemDto);
    }

    @PatchMapping(path = "/{name}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ItemDto updateItem(@RequestHeader String authorization, @RequestBody ItemDto itemDto, @PathVariable String name) {
        securityService.validateAuthorization(authorization, Feature.UPDATE_ITEM);
        return itemService.updateItem(itemDto, name);
    }
}
