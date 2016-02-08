/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Theo
 */
public class main{
    public static void main(String[] args) throws IllegalCoinException {
        int i = 0;
        PayStationImpl ps = new PayStationImpl();
        Scanner scan = new Scanner(System.in);
        int n;
        
        while(i == 0){
            writeMenu();
            n = scan.nextInt();
            scan.nextLine();
            switch(n){
                case 0: i = 1;
                    break;
                case 1:   
                    int c;
                    while(i!=1){
                        System.out.println("Enter coin(5,10 or 25) or 0 to stop entering:");
                        c = scan.nextInt();
                        scan.nextLine();
                        
                        if(c!=0)
                            ps.addPayment(c);
                        else
                            i = 1;
                    }
                    i = 0;
                    break;
                case 2:
                    int time = ps.readDisplay();
                    System.out.println("Current Time Bought = " + time);
                    break;
                case 3:
                    Receipt r =  ps.buy();
                    System.out.println("Receipt: Time Puchased = " + r.value());
                    break;
                case 4:
                    HashMap h;
                    
                    h = ps.cancel();
                    
                    Integer f = (Integer)h.get(5);
                    Integer t = (Integer)h.get(10);
                    Integer tf = (Integer)h.get(25);
                    int total = (5*f)+(10*t)+(25*tf);
                    System.out.println("Total change returned: " + total+ "\nNumber of nickels: " 
                            + f + "\nNumber of dimes: " + t + "\nNumber of Quarters: " +  tf);
                    break;
                case 5:
                    System.out.println("Enter 1 for AlphaTown, 2 for BetaTown, or 3 for GammaTown:");
                    int rs = scan.nextInt();
                    scan.nextLine();
                    if(rs == 1){
                        ps.setRateStrategy(1);
                    }
                    else if(rs == 2){
                        ps.setRateStrategy(2);
                    }
                    else if(rs == 3){
                        System.out.println("Enter day of the week: \n1 = Monday\n2 = Tuesday\n...\n7 = Sunday");
                        int day = scan.nextInt();
                        scan.nextLine();
                        if(day<6)
                            ps.setRateStrategy(1);
                        else
                            ps.setRateStrategy(2);
                    }
                    break;
            }
           
        }
    }
    public static void writeMenu(){
        System.out.println("Pick an option by typing the number or 0 to exit:\n1) Deposit Coins"
                + "\n2) Display \n3) Buy Ticket\n4) Cancel\n5) Change Rate Strategy");
    }
}