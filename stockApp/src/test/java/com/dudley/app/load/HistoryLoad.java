package com.dudley.app.load;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dudley.app.service.BaseTest;

public class HistoryLoad extends BaseTest {
	
	@Autowired
	HistoryLoader loader;

	@Test
	public void load() {
		loader.run();
	}

}
