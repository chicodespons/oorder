package com.swichfully.chicodespons.oorder.mapper;

import com.swichfully.chicodespons.oorder.dtos.ItemGroupDto;
import com.swichfully.chicodespons.oorder.dtos.OrderDto;
import com.swichfully.chicodespons.oorder.objects.Item;
import com.swichfully.chicodespons.oorder.objects.ItemGroup;
import com.swichfully.chicodespons.oorder.objects.Order;
import com.swichfully.chicodespons.oorder.objects.User;
import com.swichfully.chicodespons.oorder.repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ItemRepository itemRepository;

    public OrderMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Order mapFromOrderDtoToOrder(OrderDto orderDto, User user) {

        double totalPrice = orderDto.getItemGroupDtosList().stream()
                .mapToDouble(itemGroupDto -> mapFromItemGroupDtoToItemGroup(itemGroupDto).getTotalPrice())
                .sum();


        return new Order(
                mapFromItemGroupDtoToItemGroupList(orderDto.getItemGroupDtosList()),
                totalPrice, user.getEmail()
                );
    }

    private List<ItemGroup> mapFromItemGroupDtoToItemGroupList(List<ItemGroupDto> itemGroupDtoList){
        return itemGroupDtoList.stream()
                .map(this::mapFromItemGroupDtoToItemGroup)
                .collect(Collectors.toList());
    }

    private ItemGroup mapFromItemGroupDtoToItemGroup(ItemGroupDto itemGroupDto) {

        Item item = getItemById(itemGroupDto.getItemId());
        double totalPrice = item.getPrice()*itemGroupDto.getAmount();
        LocalDate shippingDate = calculateShippingDate(itemGroupDto);


        return new ItemGroup(item,
                itemGroupDto.getAmount(), totalPrice,
                shippingDate
                );
    }

    private LocalDate calculateShippingDate(ItemGroupDto itemGroupDto) {
        Item item = getItemById(itemGroupDto.getItemId());
        LocalDate shippingDate;

        if(item.getStockAmount()>= itemGroupDto.getAmount()) {
            shippingDate = LocalDate.now().plusDays(1);
        }else
            shippingDate = LocalDate.now().plusDays(7);
        return shippingDate;
    }

    private Item getItemById(int itemId) {
         return itemRepository.getItemById(itemId);
    }
}
