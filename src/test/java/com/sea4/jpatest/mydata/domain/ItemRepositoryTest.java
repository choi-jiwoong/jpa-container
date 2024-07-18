package com.sea4.jpatest.mydata.domain;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

import com.sea4.jpatest.mydata.entity.Item;
import com.sea4.jpatest.testcontainer.AbstractContainerBaseTest;
	import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @Filter(
	type = ASSIGNABLE_TYPE
))
@Transactional
@Slf4j
class ItemRepositoryTest extends AbstractContainerBaseTest {

	@Autowired private ItemRepository itemRepository;

	@Test
	void insertItem() {
		itemRepository.save(Item.of("testCd", "testNm", 0L, 0L, "OK"));
		Item item = itemRepository.findFirstByItemCd("testCd");
		log.info("item : {}", item.toString());
	}
}

