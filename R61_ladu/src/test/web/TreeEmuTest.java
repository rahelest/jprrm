package test.web;

import org.junit.Test;

import web.control.ProductCatalogTreeEmulator;

public class TreeEmuTest {
	
	@Test
	public void test() {
		ProductCatalogTreeEmulator emu = new ProductCatalogTreeEmulator();
		System.out.println(emu.getCatalogTree()[0]);
	}

}
