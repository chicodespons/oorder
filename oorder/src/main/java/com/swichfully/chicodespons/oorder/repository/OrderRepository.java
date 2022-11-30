package com.swichfully.chicodespons.oorder.repository;

import com.swichfully.chicodespons.oorder.objects.ItemGroup;
import com.swichfully.chicodespons.oorder.objects.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final List<Order> orderList = new ArrayList<>();

    public OrderRepository(ItemRepository itemRepository, UserRepository userRepository){
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        mockData();
    }

    private void mockData() {
        ItemGroup itemGroup1 = new ItemGroup(
                itemRepository.getItem("Playdow"),
                1, 10.00, LocalDate.of(2022,12,5));
        ItemGroup itemGroup2 = new ItemGroup(
                itemRepository.getItem("Bubble blazer"),
                2, 10.00, LocalDate.of(2022,12,10));

        List<ItemGroup> itemGroupList1 = new ArrayList<>();
        itemGroupList1.add(itemGroup1);
        List<ItemGroup> itemGroupList2 = new ArrayList<>();
        itemGroupList2.add(itemGroup1);
        itemGroupList2.add(itemGroup2);

        Order order1 = new Order(
                itemGroupList1, 10.00, "jonny@email.com"
        );

        Order order2 = new Order(
                itemGroupList2, 20.00, "admin@email.com"
        );

        orderList.add(order1);
        orderList.add(order2);

    }

    public void addOrder(Order order) {

        orderList.add(order);
    }
}
