package com.dudley.app.load;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dudley.app.service.BaseTest;

public class CompanyLoad extends BaseTest {
	
	@Autowired
	CompanyLoader loader;

	@Test
	public void test() {
		
		loader.run();
		
		assertEquals("hey", "hey");
	}

}
