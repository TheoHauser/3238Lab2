package paystation.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private int totalCollected;
    private HashMap map = new HashMap();
    private RateStrategy rs = new LinearRateStrategy();
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: break;
            case 10: break;
            case 25: break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        Integer c = coinValue;
        Integer n = 1;
        if(map.containsKey(c)){
            n = (Integer)map.get(c);
            n++;
        }
        map.put(c, n);
        insertedSoFar += coinValue;
        
        
        timeBought = rs.calculate(insertedSoFar);
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        totalCollected += insertedSoFar;
        reset();
        return r;
    }
    
    /**
     * Cancel the present transaction. Resets the paystation for a new
     * transaction. Return a Map defining the coins returned to the user.
     * The key is the coin type and the associated value is the number of
     * these coins that are returned. The Map object is never null even if
     * no coins are returned.  The map will only contain keys for coins to 
     * be returned.  The Map will only contain keys for coins to be returned
     * The Map will be cleared after a cancel or a buy
     */

    @Override
    public HashMap<Integer, Integer> cancel() {
        HashMap m = (HashMap)map.clone();
        m.putAll(map);
        reset();
        return m;
    }
    
    public int empty(){
        int r = totalCollected;
        totalCollected = 0; 
        return r;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        map.clear();
    }
    
    public void setRateStrategy(int r){
        if(r == 1)
            rs = new LinearRateStrategy();
        if(r == 2)
            rs = new ProgressiveRateStrategy();
    }
}
