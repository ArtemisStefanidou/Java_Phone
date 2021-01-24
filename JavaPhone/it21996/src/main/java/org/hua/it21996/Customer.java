/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it21996;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static org.hua.it21996.Contract.STANDING_ORDER_CARD;


/**
 *
 * @author artemis stefanidou it21996
 */
public class Customer {

    public static int counterCodeCustomer = 0;
    
    
    private int vatNumber;
    private int customerNumber;
    private String address;
    private String id;
    private int property;
    private String email;
    private int sumOfCost;
    private int discountAmount;
    private int numberOfLand;
    private int numberOfMob;
    
    private ArrayList<Contract> ContractList;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    
    public Customer(int vatNumber, String address, String id,int property, String email) {
        counterCodeCustomer++;
        this.vatNumber = vatNumber;
        this.customerNumber = counterCodeCustomer;
        this.address = address;
        this.id = id;
        this.property = property;
        this.email = email;
        
        
        ContractList = new ArrayList<>();
    }
    
    public Customer(int userVat,String userId,String userEmail,String userAddress) {

        counterCodeCustomer++;
        this.vatNumber = userVat;
        this.customerNumber = counterCodeCustomer;
        this.id = userId;
        this.email = userEmail;
        this.address=userAddress;
        this.discountAmount=0;
        ContractList = new ArrayList<>();
    }
    
    public Customer() {
        ContractList = new ArrayList<>();
    }
    //setters
  
    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
    
    public void setAddress(String userAddress) {

        this.address = userAddress;
    }

    public void setVatNumber(int userVatNumber) {
        this.vatNumber = userVatNumber;
    }

    public void setId(String userId) {
        this.id = userId;
    }

    public void setProperty(int userProperty) {
        this.property = userProperty;
    }

    public void setEmail(String userEmail) {
        this.email = userEmail;
    }
    
    public void setDiscountAmount(int Disc) {
        this.discountAmount+=Disc;
    }

    //geters
   
    public ArrayList<Contract> getContractList() {
        return ContractList;
    }

    public int getNumberOfLand() {
        return numberOfLand;
    }

    public int getNumberOfMob() {
        return numberOfMob;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }
    
    public String getAddress() {
        return this.address;
    }

    public int getVatNumber() {
        return this.vatNumber;
    }

    public String getId() {
        return this.id;
    }

    public String getProperty() {
        if(this.property==0) return "Civilian";
        if(this.property==1) return "Student";
        if(this.property==2) return "Professional";
        return "Not registered";
    }

    public String getEmail() {
        return this.email;
    }
    
    public int getDiscountAmount() {
        return discountAmount;
    }

    
    public void printAllContracts() {

      
        System.out.println("\t\t\t\tContracts Info\t\t\t\t\n");
        for (int i = 0; i < ContractList.size(); i++) {
            
            System.out.println(ANSI_CYAN+"NUMBER:"+(i+1)+ANSI_RESET);
            if(ContractList.get(i) instanceof LandlineContract){
                System.out.println("Contract Type:LandLine");
            }else{
                System.out.println("Contract Type:Mobile");
            }
            ContractList.get(i).printInfoContract();
            
            
        }
    }
    
    public void calcCost(){
        for(int i=0;i<ContractList.size();i++){
            this.sumOfCost+=ContractList.get(i).getCostPerMonth();
        }
    }
    
    public void calcDiscount(){
        if(discountAmount>45){
            discountAmount=45;
        }
    }
    
        
    public void chooseContract(){
         boolean run;
        run=true;
        while(run){
            //Επιλογή Συμβολαίο (για κινητά ή σταθερά?)
            Company Comp= new Company();

            System.out.printf("For landline contract press 1\nFor mobile contract press 2\n");
            Scanner choice = new Scanner(System.in);

            Scanner customerInfo = new Scanner(System.in);
      
            switch (choice.next()) {

                case "1":

                    LandlineContract landContr = new LandlineContract();


                   

                    run=true;
                    while(run){

                        try{
                            //Phone number CUSTOMER
                            System.out.printf("\tPlease enter your landline number.\n");
                            BigInteger tmp = new BigInteger(choice.next());

                            if (!tmp.toString().startsWith("2")) {
                                System.out.println("Please give a phone like 2103451209 starts with 2");
                                continue;
                            }

                            if(!Comp.checkPhoneNumber(tmp,this.vatNumber)){
                                continue;
                            }

                            landContr.setPhoneNumber(tmp);
                            run=false;

                        }catch(NumberFormatException e){
                            System.out.println("Please give a phone like 2103451209 starts with 2");
                        }

                    }

                    //User set the common info of two (Mobile,Landline)contracts
                    landContr.setContractInfo();

                    //User set speed of internet if he wants to
                    run=true;
                    while(run){
                        System.out.println("Do you want internet access?");
                        switch(choice.next().toUpperCase()) 
                        {
                            case "YES":
                                landContr.chooseNameSpeed();
                                run=false;
                                break;
                            case "NO":
                                System.out.println("Okey then...");
                                run=false;
                                break;
                            default:
                                System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                                break;
                        }
                    }

                    if(landContr.getSumFreeCalls()>1000){
                        
                        this.setDiscountAmount(8);

                    }

                    //Add the landline Contract in list with all contracts of the customer
                    this.getContractList().add(landContr);

                    System.out.println("\t\t\t\tContract Info\t\t\t\t\n");
                    landContr.printInfoContract();
                    System.out.println("Total Discount is:"+this.discountAmount);
                    break;
                case "2":

                    MobileContract mobContr = new MobileContract();

                    run=true;
                    while(run){

                        try{
                            //Phone number CUSTOMER
                            System.out.printf("\tPlease enter your mobile number.\n");
                            BigInteger tmp1 = new BigInteger(choice.next());

                            if (!tmp1.toString().startsWith("6")) {
                                System.out.println("Please give a phone like 6912830943 starts with 6");
                                continue;
                            }

                            if(!Comp.checkPhoneNumber(tmp1,this.vatNumber)){
                                continue;
                            }

                            mobContr.setPhoneNumber(tmp1);
                            run=false;
                        }catch(NumberFormatException e){
                            System.out.println("Please give a phone like 6912830943 starts with 6");
                        }
                    }

                    mobContr.freeGifts();

                    //User set the common info of two (Mobile,Landline)contracts
                    mobContr.setContractInfo();
                    if(mobContr.getTypeAccount()==Contract.ELECTRONIC_ACCOUNT)
                    {
                        this.setDiscountAmount(2);
                    }
                    if(mobContr.getTypeAccount()==Contract.CREDIT_CARD)
                    {
                        this.setDiscountAmount(5);
                    }
                    if(mobContr.getTypeAccount()==Contract.STANDING_ORDER_CARD)
                    {
                        this.setDiscountAmount(5);
                    }
                    if(mobContr.getSumFreeCalls()>1000){
                            
                        this.setDiscountAmount(11);  
                    }

                    //Add the landline Contract in list with all contracts of the customer
                    this.ContractList.add(mobContr);
                    System.out.println("\t\t\t\tContract Info\t\t\t\t\n");
                    mobContr.printInfoContract();
                    System.out.println("Total Discount is"+this.discountAmount);

                    break;
                default:

                    System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                    continue;
            }
           run=false;
        
        }
    }
    
       //Discounts
    public void checkNumberOfContracts(){
        
       
        LocalDateTime startOfContract,endOfContract;
        int counter=0;
        
            for(int j=0;j<ContractList.size();j++){
                
                //Check actives contracts for discounts
                startOfContract=ContractList.get(j).getDate();
                endOfContract=ContractList.get(j).getDate().plusMonths(ContractList.get(j).getDurationContract());
                if(LocalDateTime.now().isAfter(startOfContract) || LocalDateTime.now().isBefore(endOfContract)){
                        counter++;
                        
                }
                
                //Discount for the number of contracts
                if(counter==0){
                    this.setDiscountAmount(0);
                }else if(counter==1){
                    this.setDiscountAmount(5);
                }else if(counter==2){
                   this.setDiscountAmount(10);
                }else{
                    this.setDiscountAmount(15);
                }
                
                   
            }
            
    }
    
    public void MobLandContracts()
    {
        int counterMob=0;
        int counterLand=0;
        
            for(int j=0;j<ContractList.size();j++)
            {
                if(ContractList.get(j) instanceof LandlineContract){
                    counterLand++;
                }else{
                    counterMob++;
                }
            }
        this.numberOfLand=counterLand;
        this.numberOfMob=counterMob;
    }
}
