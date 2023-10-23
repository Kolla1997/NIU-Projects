package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.strategy.PirateShipChaseStrategy;
import main.strategy.PirateShipHorizontalPatrolStrategy;
import main.strategy.PirateShipPatrolStrategy;
import main.strategy.PirateShipStrategy;
import main.strategy.PirateShipVerticalPatrolStrategy;

public class TestPirateShipStrategy {
	
	@Test
	public void testChaseStartegy()
	{
		PirateShipStrategy strategy = new PirateShipChaseStrategy();
		assertTrue(strategy instanceof PirateShipStrategy);
		assertTrue(strategy instanceof PirateShipChaseStrategy);
		assertFalse(strategy instanceof PirateShipHorizontalPatrolStrategy);
		assertFalse(strategy instanceof PirateShipVerticalPatrolStrategy);
		
	}
	
	@Test
	public void testHorizontalPatrolStartegy()
	{
		PirateShipPatrolStrategy strategy = new PirateShipHorizontalPatrolStrategy();
		assertTrue(strategy instanceof PirateShipPatrolStrategy);
		assertTrue(strategy instanceof PirateShipHorizontalPatrolStrategy);
		assertFalse(strategy instanceof PirateShipChaseStrategy);
		assertFalse(strategy instanceof PirateShipVerticalPatrolStrategy);
		
	}
	
	@Test
	public void testVerticalPatrolStartegy()
	{
		PirateShipPatrolStrategy strategy = new PirateShipVerticalPatrolStrategy();
		assertTrue(strategy instanceof PirateShipPatrolStrategy);
		assertTrue(strategy instanceof PirateShipVerticalPatrolStrategy);
		assertFalse(strategy instanceof PirateShipChaseStrategy);
		assertFalse(strategy instanceof PirateShipHorizontalPatrolStrategy);
		
	}
	

}
