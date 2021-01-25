/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it21996;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Scanner;
import static org.hua.it21996.Contract.counterCode;

/**
 *
 * @author artemis
 */
public class MobileContract extends Contract {

    //Variables that used in this class 
    private int sumFreeGB;
    private int sumFreeSms;

   
    Scanner choice = new Scanner(System.in);
    
    //Constructors
    public MobileContract() {
        
        Contract.counterCode++;
        setContractCode(counterCode);
        
    }
    
    public MobileContract(int userDurationContract,BigInteger userPhoneNumber,LocalDateTime date){
        
        Contract.counterCode++;
        setContractCode(counterCode);
        
        this.setDate(date);
        this.setDurationContract(userDurationContract);
        this.setPhoneNumber(userPhoneNumber);
        this.sumFreeGB=5;
        super.setCostPerMonth(8);
        this.sumFreeSms=100;
        super.setCostPerMonth(5);
        super.setSumFreeCallsLand(600);
        super.setSumFreeCallsMob(350);
        super.setCostPerMonth(9);
        super.setTypeAccount(PRINTED_MATTER_ACCOUNT);
        super.setPaymentWay(CASH);
        
    }

    //getters
    public int getSumFreeGB() {
        return sumFreeGB;
    }

    public int getSumFreeSms() {
        return sumFreeSms;
    }

    
    public void freeGifts(){
        
        boolean run;
        
        //a while to run this option until user gives valid input
        run=true;
        while(run){
            System.out.println("Do you want internet access?");

            switch (choice.next().toUpperCase()) {

                case "YES":
                    howMuchGB();
                    run=false;
                    break;
                case "NO":
                    System.out.println("Thank you for your answer.Continue...");
                    this.sumFreeGB=0;
                    run=false;
                    break;
                default:
                    System.out.printf("\n\tChoice is not correct.Please write yes or no\n\n");
                    break;

            }
        }
        
        //a while to run this option until user gives valid input
        run=true;
        while(run){
            System.out.println("Do you want sms?");

            switch (choice.next().toUpperCase()) {

                case "YES":
                    howMuchSms();
                    run=false;
                    break;
                case "NO":
                    System.out.println("Thank you for your answer.Continue...");
                    this.sumFreeSms=0;
                    run=false;
                    break;
                default:
                    System.out.printf("\n\tChoice is not correct.Please write yes or no\n\n");
                    break;

            }
        }

    }
    
    public void howMuchGB(){
        
        System.out.println("How much GB do you want?");
        System.out.println("For 5GB the cost is 8€");
        System.out.println("For 10GB the cost is 15€");
        System.out.println("For 15GB the cost is 20€");
        
        
        //a while to run this option until user gives valid input
        boolean run;
        
        run=true;
        while(run){
            switch (choice.next().toUpperCase()) {

                case "5":
                    this.sumFreeGB=5;
                    this.setCostPerMonth(8);
                    run=false;
                    break;
                case "10":
                    this.sumFreeGB = 10;
                    this.setCostPerMonth(15);
                    run=false;
                    break;
                case "15":
                    this.sumFreeGB = 15;
                    this.setCostPerMonth(20);
                    run=false;
                    break;
                default:
                    System.out.printf("\n\tChoice is not correct.Please write 5 10 or 15\n\n");
                    break;

            }
        }
        
    }
    
     public void howMuchSms(){
         
        System.out.println("How much SMS do you want?");
        System.out.println("For 100sms the cost is 5€");
        System.out.println("For 60sms the cost is 3€");
        System.out.println("For 30sms the cost is 1€");
        
        
        //a while to run this option until user gives valid input
        boolean run;
        
        run=true;
        while(run){
            switch (choice.next()) {

                case "100":
                    this.sumFreeSms=100;
                    this.setCostPerMonth(5);
                    run=false;
                    break;
                case "60":
                    this.sumFreeSms = 60;
                    this.setCostPerMonth(3);
                    run=false;
                    break;
                case "30":
                    this.sumFreeSms = 30;
                    this.setCostPerMonth(1);
                    run=false;
                    break;
                default:
                    System.out.printf("\n\tChoice is not correct.Please write 100 60 or 30\n\n");
                    break;

            }
        }
    }

     
    //because of the extend i must override the abstract methods from the super class
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
        System.out.println("Free GB:"+ this.sumFreeGB );
        System.out.println("Free SMS:"+ this.sumFreeSms );
        
        
                   
    } 

    @Override
    public int getMB() {
       return this.sumFreeGB;
    }

    @Override
    public int getSMS() {
        return this.sumFreeSms;
    }

  
 
}
