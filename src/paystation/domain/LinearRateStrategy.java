/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author Theo
 */
public class LinearRateStrategy implements RateStrategy{
    
    @Override
    public int calculate(int totalInserted){
        return totalInserted/5*2;
    }
    
}
