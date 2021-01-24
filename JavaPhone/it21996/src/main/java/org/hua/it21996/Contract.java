/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it21996;


import java.math.BigInteger;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;


/**
 *
 * @author artemis
 */
public abstract class Contract {

    public static final int ELECTRONIC_ACCOUNT = 0;
    public static final int PRINTED_MATTER_ACCOUNT = 1;

    public static final int CREDIT_CARD = 0;
    public static final int CASH = 1;
    public static final int STANDING_ORDER_CARD = 2;

    public static int counterCode=0;
    
    private int contractCode;
    private int durationContract;
    private int sumFreeCalls;
    private int typeAccount;
    private int paymentWay;
    private BigInteger phoneNumber;
    private int costPerMonth;
    private int freeCallsLand;
    private int freeCallsMob;
    private LocalDateTime date;
    

  
    //setters
     public void setSumFreeCallsMob(int userFreeCallsMob) {
        this.freeCallsMob =userFreeCallsMob;
    }
     
    public void setSumFreeCallsLand(int userFreeCallsLand) {
        this.freeCallsLand =userFreeCallsLand;
    }
     public void setCostPerMonth(int costPerMonth) {
        this.costPerMonth += costPerMonth;
    }
     
    public void setContractCode(int userCode) {

        this.contractCode = userCode;
    }

    public void setDurationContract(int userDurationContr) {
        this.durationContract = userDurationContr;
    }

    public void setSumFreeCalls(int sumFreeCalls) {
        this.sumFreeCalls = sumFreeCalls;
    }

    

    public void setTypeAccount(int usertypeAccount) {
        this.typeAccount = usertypeAccount;
    }

    public void setPaymentWay(int userpaymentWay) {
        this.paymentWay = userpaymentWay;
    }

    public void setPhoneNumber(BigInteger userPhoneNumber) {
        this.phoneNumber = userPhoneNumber;
    }
    
     public void setDate(LocalDateTime  date) {
        this.date = date;
    }

    //getters
    public int getSumFreeCallsMob() {
        return this.freeCallsMob ;
    }
     
    public int getSumFreeCallsLand() {
        return this.freeCallsLand ;
    }
    public int getCostPerMonth() {
        return costPerMonth;
    }
    
    public LocalDateTime  getDate() {
       return date;
    }

    public int getContractCode() {

        return this.contractCode;
    }

    public int getDurationContract() {
        return durationContract;
    }

    public int getSumFreeCalls() {
        return sumFreeCalls;
    }

    public int getTypeAccount() {
        return typeAccount;
    }

    public int getPaymentWay() {
        return paymentWay;
    }

    

    public String printTypeAccount() {
        if(this.typeAccount==0) return "Electronic Account";
        if(this.typeAccount==1) return "Printed Matter Account";
        return "Not registered";
    }

    public String printPaymentWay() {
        if(this.paymentWay==0) return "Credit Card";
        if(this.paymentWay==1) return "Cash";
        if(this.paymentWay==2) return "Standing Order Card";
        return "Not registered";
    }

    public BigInteger getPhoneNumber() {
        return this.phoneNumber;
    }

    

    //Μια μέθοδος που πρέπει να υπάρχει και στα δύο contract(landline,mobile)
    //και θετει όλες τις πληροφοριες του γενικου contract παίρνοντας input απο το χρηστη
    public void setContractInfo() {
        Scanner choice = new Scanner(System.in);
        Company userComp=new Company();

        int userYear, userMonth, userDays,userMinutes,userHours;
        
        
        boolean run;
        run=true;
        while(run){
            //type account
            System.out.printf("\tPlease enter the type account of this contract.\n"
                    + "for electronic account press 0\n"
                    + "for printed matter account press 1\n");

            switch (choice.next()) {
                case "0":

                    this.typeAccount = ELECTRONIC_ACCOUNT;
                    //the discount for electronic account is in the customer class
                    run=false;
                    break;
                case "1":

                    this.typeAccount = PRINTED_MATTER_ACCOUNT;
                    run=false;
                    break;

                default:
                    System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                    continue;
            }
        }
        
       
        run=true;
        while(run){
            //Duration Contract
            System.out.printf("\tPlease enter the duration of this contract.\n"
                    + "for 12 months press 0\n"
                    + "for 24 months press 1\n");
            switch (choice.next()) {
                case "0":

                    this.durationContract = 12;
                    run=false;
                    break;
                case "1":

                    this.durationContract = 24;
                    run=false;
                    break;

                default:
                    System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                   continue;
            }
        }

        run=true;
        while(run){
            //Payment Way
            System.out.printf("\tPlease enter how do you want to pay this contract.\n"
                    + "for credit card press 0\n"
                    + "for cash  press 1\n"
                    + "for standing order card press 2\n");
            switch (choice.next()) {
                case "0":

                    this.paymentWay = CREDIT_CARD;
                    //the discount for CREDIT_CARD is in the customer class
                    run=false;
                    break;
                case "1":

                    this.paymentWay = CASH;
                    run=false;
                    break;

                case "2":

                    this.paymentWay = STANDING_ORDER_CARD;
                    //the discount for CREDIT_CARD is in the customer class
                    run=false;
                    break;

                default:
                    System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                    continue;
            }
        }
        run=true;
        while(run){
            
            Scanner ch = new Scanner(System.in);
            //The day that customer choose to start a contract
            System.out.println("\tPlease enter the date you wish to start your contract.Enter the year:");
            userYear = ch.nextInt();
            System.out.println("\tEnter the month:");
            userMonth = ch.nextInt();
            System.out.println("\tEnter the days:");
            userDays = ch.nextInt();
            System.out.println("\tEnter the hours:");
            userHours = ch.nextInt();
            System.out.println("\tEnter the minutes:");
            userMinutes = ch.nextInt();

            try{
                LocalDateTime userDate=LocalDateTime.of(userYear, userMonth, userDays,userHours,userMinutes);
                
                    if(!userComp.checkContractTime(userDate,this.phoneNumber)){
                        continue;
                        }
                run=false;
                setDate(userDate);
            }catch(DateTimeException e){
                System.out.println("Give a valid Date.");
            }
            
        }
            
        
        
        
        System.out.println("How much min for landline phones and mobiles you want?");
        System.out.println("Press 1 for 500min for landline phones and 550min for mobiles with 12euros per month");
        System.out.println("Press 2 for 600min for landline phones and 350min for mobiles with 9euros per month");
        System.out.println("Press 3 for 400min for landline phones and 700min for mobiles with 20euros per month");
        
        
        run=true;
        while(run){
            switch (choice.next()) {

                case "1":
                    this.freeCallsLand=500;
                    this.freeCallsMob=550;
                    this.sumFreeCalls=this.freeCallsLand+this.freeCallsMob;
                    this.setCostPerMonth(12);
                    run=false;
                    break;
                case "2":
                    this.freeCallsLand=600;
                    this.freeCallsMob=350;
                    this.sumFreeCalls=this.freeCallsLand+this.freeCallsMob;
                    this.setCostPerMonth(9);
                    run=false;
                    break;
                case "3":
                    this.freeCallsLand=400;
                    this.freeCallsMob=700;
                    this.sumFreeCalls=this.freeCallsLand+this.freeCallsMob;
                    this.setCostPerMonth(20);
                    run=false;
                    break;
                default:
                    System.out.printf("\n\tChoice is not correct.Please write 1 2 or 3\n\n");
                    break;

            }
        }
       
        
    }

    
   public abstract void printInfoContract();
    
   public abstract int getMB();
   
   public abstract int getSMS();
    
  
}
