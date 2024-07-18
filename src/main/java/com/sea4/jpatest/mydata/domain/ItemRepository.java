package com.sea4.jpatest.mydata.domain;


import com.sea4.jpatest.mydata.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{

	Item findFirstByItemCd (String itemCd);
}
