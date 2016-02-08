/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Theo
 */
public class ProgressiveRateStrategyTest {
    RateStrategy rs = new ProgressiveRateStrategy();
    public ProgressiveRateStrategyTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculate method, of class ProgressiveRateStrategy.
     */
    @Test
    public void shouldGive120MinFor350cent() {
        assertEquals(2*60, rs.calculate(350));
    }
    @Test
    public void shouldGive60MinFor150cent(){
        assertEquals(60, rs.calculate(150));
    }
    @Test
    public void shouldgive180Minfor650cent(){
        assertEquals(180, rs.calculate(650));
    }
    @Test
    public void shouldgive240Minfor950cent(){
        assertEquals(240, rs.calculate(950));
    }
    
}
