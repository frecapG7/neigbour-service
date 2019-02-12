package com.neigbour.service.neigbourservice.model.repository;

import java.math.BigDecimal;
import java.util.Optional;

import javax.swing.Spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.neigbour.service.neigbourservice.model.entity.Item;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.util.TestConstants;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	@Autowired
	ItemRepository itemRepository;

	
	private Item testItem = Item.builder()
			.nameEn("Menu1")
			.nameFr("Menu1")
			.description("Test")
			.price(new BigDecimal("234"))
			.pointOfInterest(TestConstants.PARISA)
			.build();
	
	@Test
	public void should_insert_an_item() {
		Item item = itemRepository.save(testItem);
		Assert.assertNotNull(item);
	}
	
	@Test
	public void should_find_an_item() {
		Long id = this.testEntityManager.persistAndGetId(testItem, Long.class);
		
		Optional<Item> result = itemRepository.findById(id);
		Assert.assertEquals(true,  result.isPresent());
	}
	@Test
	public void should_delete_an_item() {
		Long id = this.testEntityManager.persistAndGetId(testItem, Long.class);
		
		itemRepository.deleteById(id);
		
		Assert.assertFalse(itemRepository.findById(id).isPresent());
	}
	
	

}
