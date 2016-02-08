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
public class ProgressiveRateStrategy implements RateStrategy{
    @Override
    public int calculate(int totalInserted){
        int time = 0;
        if(totalInserted>=150)
            time = time + 60;
        if(totalInserted>=350)
            time = time + 60;
        totalInserted -= 350;
        while(totalInserted>=300){
            if(totalInserted>=300)
                time = time + 60;
            totalInserted -= 300;
        }
        return time;
    }
}
