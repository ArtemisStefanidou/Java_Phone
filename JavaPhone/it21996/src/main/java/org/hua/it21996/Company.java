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
import java.time.DateTimeException;

/**
 *
 * @author artemis
 */
public class Company {

    public static final int CIVILIAN = 0;
    public static final int STUDENT = 1;
    public static final int PROFESSIONAL = 2;

    private int userVatNumber;
    private String userId;
    private int tmp;
    private String userEmail;
    private int maxCallsLand1,minCallsLand1,meanCallsLand1;
    private int maxCallsMob1,minCallsMob1,meanCallsMob1;
    private int maxCallsLand2,minCallsLand2,meanCallsLand2;
    private int maxCallsMob2,minCallsMob2,meanCallsMob2;
    private int numberOfLandContracts,numberOfMobContracts;
    private int maxMB ,minMB,meanMB,maxSms ,minSms ,meanSms ;
    private static Customer userCust=new Customer();

    Scanner customerInfo = new Scanner(System.in);
   

    public static ArrayList<Customer> customerList = new ArrayList<Customer>();
    
    public void Menu() {
        
        System.out.printf("\tPlease choose an option from 1 to 6\n");
        System.out.printf("\t1.Create A New Customer\n"
                + "\t2.Create A New Contract\n"
                + "\t3.Delete Contract\n"
                + "\t4.Exit\n");
    }
    
    public void customersContracts(){
        // two customers in the system
        Customer defaultCust1=new Customer(987654321,"Themistokleous1","AW12345",STUDENT,"rouliscat2000@gmail.com");
        Customer defaultCust2=new Customer(987654322,"Themistokleous2","AW12346",STUDENT,"roulis2000@gmail.com");
        customerList.add(defaultCust1);
        customerList.add(defaultCust2);
        
        //add first contract in first customer
        BigInteger number1 = new BigInteger("2106140817");
        LocalDateTime userDate1=LocalDateTime.of(2021, 11, 21,14,21);
        LandlineContract defaultLandline1=new LandlineContract(12,number1,userDate1);
        defaultCust1.getContractList().add(defaultLandline1);
        
        //add a second contract in the first customer
        BigInteger number2 = new BigInteger("6941642009");
        LocalDateTime userDate2=LocalDateTime.of(2021, 12, 21,14,21);
        MobileContract defaultMob1=new MobileContract(12,number2,userDate2);
        defaultCust1.getContractList().add(defaultMob1);
        
        
    }

    public void addCustomer() {

        boolean run;
        
        run=true;
        while(run){
            
            //VAT CUSTOMER
            System.out.println("\tPlease enter your Vat.");
           

            try{
                this.userVatNumber = customerInfo.nextInt();
               
            }catch(InputMismatchException e){
                
                customerInfo.nextLine();
                System.out.println("Invalid input please only integers");
                
                continue;
            }
            
            if (!checkVat(this.userVatNumber)) {
               continue;
            }
           
            if (this.findVat(this.userVatNumber) != null){
                System.out.printf("\tThis customer already exist.Please try something else\n");
                continue;
            }
            
            run=false;
        }
        run=true;
        while(run){
            
            //ID CUSTOMER
            System.out.printf("\tPlease enter your identity number.Like am1234...\n");
            this.userId = customerInfo.next();
            
            if(!checkId(userId)){
                continue;
            }
            if (this.findId(this.userId) != null) {
                System.out.printf("\tThis customer already exist.Please try something else\n");
                continue;
            }
            run=false;
        }
        run=true;
        while(run){
            //EMAIL CUSTOMER
            System.out.printf("\tPlease enter your email.\n");
            this.userEmail = customerInfo.next();
            if (!checkEmail(this.userEmail)) {
                continue;
            }
            run=false;
        }
        //ADDRESS CUSTOMER
        System.out.printf("\tPlease enter your address with no spaces between.\n");
        
        
        Customer customer = new Customer(this.userVatNumber,this.userId,this.userEmail,customerInfo.next());
        
        run=true;
        while(run){
            //PROPERTY CUSTOMER
            System.out.printf("\tPlease enter your property.\n"
                    + "for civilian press 0 \n for student press 1\n "
                    + "for professional press 2\n");

            switch (customerInfo.next()) {
                case "0":
                    customer.setProperty(CIVILIAN);
                    break;
                case "1":
                    customer.setProperty(STUDENT);
                    customer.setDiscountAmount(15);
                    break;
                case "2":
                    customer.setProperty(PROFESSIONAL);
                    customer.setDiscountAmount(10);
                    break;
                default:
                    System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                    continue;
            }
            run=false;
        }

        
        customerList.add(customer);

    }

    public Customer findVat(int userVat) {

        for (int i = 0; i < customerList.size(); i++) {

            if (customerList.get(i).getVatNumber() == userVat) {
                return customerList.get(i);
            }

        }
        return null;
    }

    public Customer findId(String userId) {

        for (int i = 0; i < customerList.size(); i++) {
            
            if (customerList.get(i).getId().compareTo(userId) == 0) {
                return customerList.get(i);
            }
        }
        return null;
    }
    
    public boolean checkPhoneNumber(BigInteger userPhone,int userVat){
        for (int i = 0; i < customerList.size(); i++) {
            for(int j=0;j<customerList.get(i).getContractList().size();j++){
                
                
                    
                    if(customerList.get(i).getContractList().get(j).getPhoneNumber().equals(userPhone) && userVat!=customerList.get(i).getVatNumber()){
                        System.out.println("This phone is already used from other customer.");
                        return false;
                    }
              
            }
        }
        return true;
    }

    public boolean checkVat(int userVat) {

        if (100000000 > userVat || 999999999 < userVat) {
            System.out.println("The vat number is not valid.Please try something "
                    + "between 100000000 and 999999999");
            return false;
        }
        return true;
    }

    public boolean checkEmail(String userEmail) {

        String[] tmpEmail1 = userEmail.split("@");

        if (tmpEmail1.length != 2) {
            System.out.println("Not valid email.PLease enter a email like artemisstefanidou@gmail.com\n");
            return false;
        }

        String[] tmpEmail2 = tmpEmail1[1].split("\\.");

        if (tmpEmail2.length != 2) {

            System.out.println("Not valid email.PLease enter a email like artemisstefanidou@gmail.com\n");
            return false;
        }

        return true;

    }

    public void addContract() {
        
        boolean run;
        
        run=true;
        while(run){
            
            try{
                //VAT CUSTOMER
                System.out.println("\tPlease enter your Vat.");
                tmp = customerInfo.nextInt();
                userCust = findVat(tmp);
                if (userCust == null) {
                    System.out.println("There is not a Customer with this Vat\nPlease try again");
                    continue;
                }
                run=false;
                userCust.chooseContract();
            }catch(InputMismatchException e){
                System.out.println("Please give a valid Vat");
            }
        }

    }


    public void deleteContract() {

        boolean run;
        
        run=true;
        while(run){
            
            try{
                Contract currentContract;
                //VAT CUSTOMER
                System.out.println("\tPlease enter your Vat.");
                tmp = customerInfo.nextInt();
                userCust = findVat(tmp);
                if (userCust == null) {
                    System.out.println("There is not a Customer with this Vat\nPlease"
                            + "try again");
                    continue;
                }
                if(userCust.getContractList().size()==0){
                    System.out.println("This customer has not contracts.");
                    return;
                }
                userCust.printAllContracts();
                
                run=true;
                while(run){
                    try{
                        //get the number of contract that user wants to delete
                        System.out.println("Give me the Contract NUMBER that you want to delete");
                        tmp = customerInfo.nextInt();

                        if (tmp > userCust.getContractList().size() || tmp<1) {
                            System.out.println("There is not this Contract Number.Please try something else");
                            continue;
                        }
                        userCust.getContractList().remove(tmp-1);
                        System.out.printf("The contract %d removed successfull from the list",tmp);
                        run=false;
                    }catch(InputMismatchException e){
                        System.out.println("Please give a valid NUMBER");
                        customerInfo.nextLine();
                        continue;
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please give a valid Vat");
                customerInfo.nextLine();
            }
        }
    }

    public Contract findContractCode(int userCode) {
        for (int i = 0; i < userCust.getContractList().size(); i++) {
            if (userCust.getContractList().get(i).getContractCode() == userCode) {
                return userCust.getContractList().get(i);
            }

        }
        return null;
    }
    
    public boolean checkId(String userId){
        
        char character1 = userId.charAt(0);
        char character2 = userId.charAt(1);
        if(!(Character.isLetter(character1) && Character.isLetter(character1))) {
            System.out.println("You must give a valid Id");
            return false;
        }
        
        for(int i=2;i<userId.length();i++){
            
            char character=userId.charAt(i);
            
            if(!Character.isDigit(character)){
                
                System.out.println("You must give a valid Id.Only first two of id must be a letter");
                return false;
                
            }
            
        }
        
        return true;
    }
    
    public boolean checkContractTime(LocalDateTime userDateTime,BigInteger userPhone){
        
        LocalDateTime vampireAge=LocalDateTime.now().plusYears(100);
        System.out.println(vampireAge);
        System.out.println((int)userDateTime.getYear());
        System.out.println((int)vampireAge.getYear());
        if((int)userDateTime.getYear() > (int)vampireAge.getYear()){
            System.out.println("Please give a valid year.In 100 years from now we will not know if the company exists ");
            return false;
        }
        LocalDateTime  startOfContract,endOfContract;
        if(userDateTime.isBefore(LocalDateTime.now())){
            System.out.println("You must give a valid Time that you want to start your contract");
            return false;
        }
        
        for (int i = 0; i < userCust.getContractList().size(); i++) {
            
            if(userCust.getContractList().get(i).getPhoneNumber().equals(userPhone) ){
                
                startOfContract=userCust.getContractList().get(i).getDate();
                endOfContract=userCust.getContractList().get(i).getDate().plusMonths(userCust.getContractList().get(i).getDurationContract());
         
                if (userDateTime.isAfter(startOfContract) && userDateTime.isBefore(endOfContract)) {
                    System.out.println("You can not set this date because an other contract is active");
                    return false;
                }
            }
        }
        return true;
    }

    public void searchMinMaxMeanCalls(){
        //1 for landline contracts and 2 for mobile contracts
        int maxLand1=0,maxMob1=0;
        int minLand1=1000000,minMob1=1000000;
        int sumLand1=0,sumMob1=0;
        int meanLand1=0,meanMob1=0;
        
        int maxLand2=0,maxMob2=0;
        int minLand2=1000000,minMob2=1000000;
        int sumLand2=0,sumMob2=0;
        int meanLand2=0,meanMob2=0;
        
        for (int i = 0; i < customerList.size(); i++) {
            
            for(int j=0;j<customerList.get(i).getContractList().size();j++){
                
                //for landLine contract
                
                
                if(customerList.get(i).getContractList().get(j).getPhoneNumber().toString().startsWith("2")){
                    //number of land line contract
                    this.numberOfLandContracts++;
                    //for landline free minutes
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsLand() < minLand1){
                        minLand1=customerList.get(i).getContractList().get(j).getSumFreeCallsLand();
                    }
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsLand() > maxLand1){
                        maxLand1=customerList.get(i).getContractList().get(j).getSumFreeCallsLand();
                    }
                    sumLand1+=customerList.get(i).getContractList().get(j).getSumFreeCallsLand();

                    //for mobile free minutes
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsMob() < minMob1){
                        minMob1=customerList.get(i).getContractList().get(j).getSumFreeCallsMob();
                    }
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsMob() > maxMob1){
                        maxMob1=customerList.get(i).getContractList().get(j).getSumFreeCallsMob();
                    }
                    sumMob1+=customerList.get(i).getContractList().get(j).getSumFreeCallsMob();
                }
                //for mobile contract
                this.numberOfMobContracts++;
                if(customerList.get(i).getContractList().get(j).getPhoneNumber().toString().startsWith("6")){
                    //number of mobile contrats
                    this.numberOfMobContracts++;
                    //for landline free minutes
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsLand() < minLand2){
                        minLand2=customerList.get(i).getContractList().get(j).getSumFreeCallsLand();
                    }
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsLand() > maxLand2){
                        maxLand2=customerList.get(i).getContractList().get(j).getSumFreeCallsLand();
                    }
                    sumLand2+=customerList.get(i).getContractList().get(j).getSumFreeCallsLand();

                    //for mobile free minutes
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsMob() < minMob2){
                        minMob2=customerList.get(i).getContractList().get(j).getSumFreeCallsMob();
                    }
                    if(customerList.get(i).getContractList().get(j).getSumFreeCallsMob() > maxMob2){
                        maxMob2=customerList.get(i).getContractList().get(j).getSumFreeCallsMob();
                    }
                    sumMob2+=customerList.get(i).getContractList().get(j).getSumFreeCallsMob();
                }
                
            } 
            
        }
        System.out.println();
        //for landline contract
        System.out.println("Max Min an Mean from Landline to LandLine or Mobile");
        //for landline numbers
        meanLand1=sumLand1/this.numberOfLandContracts;
        System.out.println("Max Calls to LandLine:"+maxLand1);
        System.out.println("Min Calls to LandLine:"+minLand1);
        System.out.println("Mean Calls to LandLine:"+meanLand1);
        
        //for mobile numbers
        meanMob1=sumMob1/this.numberOfLandContracts;
        System.out.println("Max Calls to Mobile:"+maxMob1);
        System.out.println("Min Calls to Mobile:"+minMob1);
        System.out.println("Mean Calls to Mobile:"+meanMob1);
        
        //for mobile contract
        System.out.println("Max Min an Mean from Mobile to LandLine or Mobile");
        //for landline numbers
        meanLand2=sumLand2/this.numberOfMobContracts;
        System.out.println("Max Calls to LandLine:"+maxLand2);
        System.out.println("Min Calls to LandLine:"+minLand2);
        System.out.println("Mean Calls to LandLine:"+meanLand2);
        
        //for mobile numbers
        meanMob2=sumMob2/this.numberOfMobContracts;
        System.out.println("Max Calls to Mobile:"+maxMob2);
        System.out.println("Min Calls to Mobile:"+minMob2);
        System.out.println("Mean Calls to Mobile:"+meanMob2);
        
    }
    
    public void searchMinMaxMeanMBSms(){
        
        int maxMB=0,maxSms=0;
        int minMB=1000000,minSms=1000000;
        int sumMB=0,sumSms=0;
        int meanMB=0,meanSms=0;
        
        
        for (int i = 0; i < customerList.size(); i++) {
            
            for(int j=0;j<customerList.get(i).getContractList().size();j++){
                if(customerList.get(i).getContractList().get(j).getPhoneNumber().toString().startsWith("6")){
                    //for MB
                    if(customerList.get(i).getContractList().get(j).getMB()>maxMB){
                        maxMB=customerList.get(i).getContractList().get(j).getMB();
                    }  
                    if(customerList.get(i).getContractList().get(j).getMB()<minMB){
                        minMB=customerList.get(i).getContractList().get(j).getMB();
                    }

                    //For SMS
                    if(customerList.get(i).getContractList().get(j).getSMS()>maxSms){
                        maxSms=customerList.get(i).getContractList().get(j).getSMS();
                    }
                    if(customerList.get(i).getContractList().get(j).getSMS()<minSms){
                        minSms=customerList.get(i).getContractList().get(j).getSMS();
                    }
                    sumMB+=customerList.get(i).getContractList().get(j).getMB();
                    sumSms+=customerList.get(i).getContractList().get(j).getSMS();
                }
            }
           customerList.get(i).MobLandContracts();
           this.numberOfLandContracts+=customerList.get(i).getNumberOfLand();
           this.numberOfMobContracts+=customerList.get(i).getNumberOfMob();
        }
        
        meanMB=sumMB/this.numberOfMobContracts;
        meanSms=sumSms/this.numberOfMobContracts;
        //For MB
        this.maxMB=maxMB;
        this.minMB=minMB;
        this.meanMB=meanMB;
        System.out.println("Max of MB is:"+this.maxMB);
        System.out.println("Min of MB is:"+this.minMB);
        System.out.println("Mean of MB is:"+this.meanMB);
        
        //For SMS
        this.maxSms=maxSms;
        this.minSms=minSms;
        this.meanSms=meanSms;
        System.out.println("Max of SMS is:"+this.maxSms);
        System.out.println("Min of SMS is:"+this.minSms);
        System.out.println("Mean of SMS is:"+this.meanSms);
    }
    
    public void printStatistics(){
        searchMinMaxMeanMBSms();
        searchMinMaxMeanCalls();
    }
    
    
    
    
    
    public void contractsStatistics(){
           boolean run;
        
        run=true;
        while(run){
            
                System.out.println("What do you want to see?");
                System.out.println("Press 1 to see the statistics"+"\n"
                        +"Press 2 to see the details of your contracts\n"
                        +"Press 3 to see your discounts");
                Scanner choice = new Scanner(System.in);
        
        
                switch(choice.next()) 
                {
                    case "1":

                        printStatistics();
                        break;
                    case "2":
                        try{
                            //VAT CUSTOMER
                            System.out.println("\tPlease enter your Vat.");
                            tmp = customerInfo.nextInt();
                            userCust = findVat(tmp);
                            if (userCust == null) {
                                System.out.println("There is not a Customer with this Vat\nPlease try again");
                                   continue;
                               }

                            userCust.printAllContracts();
                            run=false;
                            break;
                        }catch(InputMismatchException e){
                            System.out.println("Please give a valid Vat");
                            continue;
                        }
                        
                    case "3":
                        try{
                            //VAT CUSTOMER
                            System.out.println("\tPlease enter your Vat.");
                            tmp = customerInfo.nextInt();
                            userCust = findVat(tmp);
                            if (userCust == null) {
                                System.out.println("There is not a Customer with this Vat\nPlease try again");
                                   continue;
                            }
                            userCust.checkNumberOfContracts();
                            userCust.calcDiscount();

                            System.out.println("Your Discounnt for all your Contracts is: -"+userCust.getDiscountAmount()+"%");
                            run=false;
                          break; 
                         }catch(InputMismatchException e){
                            System.out.println("Please give a valid Vat");
                            continue;
                        }
                    default:
                      System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                      continue;
                }
               
        }
    }
 
    
    
    
}
