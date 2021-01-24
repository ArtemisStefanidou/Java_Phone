/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it21996;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author artemis
 */
public class LandlineContract extends Contract{
    
    public static final int ADSL=0;
    public static final int VDSL=1;
    
    private int speedBooster;
    
    Scanner choice = new Scanner(System.in);
    
    public LandlineContract() {
        
        Contract.counterCode++;
        setContractCode(counterCode);
        
    }
    
    public String getSpeed() {
        if(this.speedBooster==ADSL) return "ADSL";
        if(this.speedBooster==VDSL) return "VDSL";
        return "Not registered";
    }
    
    public LandlineContract(int userDurationContract,BigInteger userPhoneNumber,LocalDateTime date){
        
        Contract.counterCode++;
        setContractCode(counterCode);
        
        
        this.setDate(date);
        this.setDurationContract(userDurationContract);
        this.setPhoneNumber(userPhoneNumber);
        
    }
    
    

    /*public LandlineContract(int speedBooster) {
        Contract.counterCode++;
        this.setContractCode(counterCode);
        this.speedBooster = speedBooster;
        this.
    }*/
    
   
    
    //Two methods for if customer wants internet and with what speed
    public void chooseNameSpeed()
    {
        boolean run;
        run=true;
        while(run){
            System.out.println("Do you want VDSL or ADSL?");
                switch(choice.next().toUpperCase()) 
                {
                    case "VDSL":
                        this.speedBooster=VDSL;
                        run=false;
                        break;
                    case "ADSL":
                        this.speedBooster=ADSL;
                        run=false;
                        break;
                    default:
                        System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                        break;
                }
        }
    }
    
    
    @Override
    public void printInfoContract()
    {
        
                
        System.out.println("Code:"+getContractCode());
        System.out.println("Start:"+getDate());
        System.out.println("Duration:"+getDurationContract()+"Months");
        System.out.println("Cost/month:"+getCostPerMonth());
        System.out.println("Type Account:"+printPaymentWay());
        System.out.println("Phone Num:"+ getPhoneNumber());
        System.out.println("Free calls for Landline:"+ getSumFreeCallsLand());
        System.out.println("Free calls for Mobile:"+ getSumFreeCallsMob());
        System.out.println("Speed:"+ this.speedBooster);
        
                   
    }

    @Override
    public int getMB() {
        System.out.println("MB does not exist here");
        return 1;
    }

    @Override
    public int getSMS() {
        System.out.println("SMS does not exist here");
        return 1;
    }

  
}
