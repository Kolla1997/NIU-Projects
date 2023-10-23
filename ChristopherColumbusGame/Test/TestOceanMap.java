package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.OceanMap;

public class TestOceanMap {
	
	@Test
	public void testOceanMap() {
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		
		assertEquals(oceanMap1, oceanMap2);
		assertTrue(oceanMap1 == oceanMap2);
	}
	
	@Test
	public void  testDimensions()
	{
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		
		assertEquals(oceanMap1.getDimensions(), oceanMap2.getDimensions());
		assertTrue(oceanMap1.getDimensions() == oceanMap2.getDimensions());
	}
	
	
	@Test
	public void testIsIsland()
	{
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		
		assertEquals(oceanMap1.isIsland(5,5), oceanMap2.isIsland(5, 5));
		assertTrue(oceanMap1.isIsland(5, 5) == oceanMap2.isIsland(5, 5));
	}
	
	@Test
	public void testISOcean()
	{
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		
		assertEquals(oceanMap1.isOcean(5,5), oceanMap2.isOcean(5, 5));
		assertTrue(oceanMap1.isOcean(5, 5) == oceanMap2.isOcean(5, 5));
	}
	
	@Test
	public void testIsOCeanIsland()
	{
		OceanMap oceanMap1 = OceanMap.getGrid();
		OceanMap oceanMap2 = OceanMap.getGrid();
		
		assertNotEquals(oceanMap1.isOcean(5, 5), oceanMap2.isIsland(5, 5));
		assertFalse(oceanMap1.isIsland(5, 5) == oceanMap2.isOcean(5, 5));
	}

}
