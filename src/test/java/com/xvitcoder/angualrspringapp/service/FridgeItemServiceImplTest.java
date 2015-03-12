package com.xvitcoder.angualrspringapp.service;


import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:app-config-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FridgeItemServiceImplTest {

	@Resource
	FridgeItemService fridgeItemService;
	
	@Test
	public void test1() {
		// TODO
	}
	

}
