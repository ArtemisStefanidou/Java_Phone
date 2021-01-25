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
    
    //Constant Field Values for better understanding when someone read my code
    public static final int CIVILIAN = 0;
    public static final int STUDENT = 1;
    public static final int PROFESSIONAL = 2;

    //Variables that used in this class 
    private int userVatNumber;
    private String userId;
    private int tmp;
    private String userEmail;
    private int numberOfLandContracts,numberOfMobContracts;
    private int maxMB ,minMB,meanMB,maxSms ,minSms ,meanSms ;
    private static Customer userCust=new Customer();

    Scanner customerInfo = new Scanner(System.in);
   
    //list which contains all of the Costumers 
    public static ArrayList<Customer> customerList = new ArrayList<Customer>();
    
    /**
     * Print the menu for this program
     */
    public void Menu() {
        
        System.out.printf("\tPlease choose an option from 1 to 6\n");
        System.out.printf("\t1.Create A New Customer\n"
                + "\t2.Create A New Contract\n"
                + "\t3.Delete Contract\n"
                + "\t4.See active contracts and statistics\n"
                + "\t5.Exit\n");
    }
    
    /**
     * 
     * Create some customers and contracts to have at the start of the program 
     * so user can start the program from any option from menu
     *
     */
    public void customersContracts(){
        // two customers in the system
        Customer defaultCust1=new Customer(987654321,"Themou12","AW12345",CIVILIAN,"art@gmail.com");
        Customer defaultCust2=new Customer(987654322,"Themou151","AW12346",CIVILIAN,"art2000@gmail.com");
        customerList.add(defaultCust1);
        customerList.add(defaultCust2);
        
        //add first (landline) contract in first customer
        BigInteger number1 = new BigInteger("2106240818");
        LocalDateTime userDate1=LocalDateTime.of(2021, 11, 21,14,21);
        LandlineContract defaultLandline1=new LandlineContract(12,number1,userDate1);
        defaultCust1.getContractList().add(defaultLandline1);
        
        //add a second (mobile) contract in the first customer
        BigInteger number2 = new BigInteger("6956642013");
        LocalDateTime userDate2=LocalDateTime.of(2021, 12, 21,14,21);
        MobileContract defaultMob1=new MobileContract(12,number2,userDate2);
        defaultCust1.getContractList().add(defaultMob1);
        
        //add first (landline) contract in second customer
        BigInteger number3 = new BigInteger("2105210298");
        LocalDateTime userDate3=LocalDateTime.of(2021, 10, 21,14,21);
        LandlineContract defaultLandline3=new LandlineContract(12,number3,userDate3);
        defaultCust2.getContractList().add(defaultLandline3);
        
        //add a second (mobile) contract in the first customer
        BigInteger number4 = new BigInteger("6941642009");
        LocalDateTime userDate4=LocalDateTime.of(2021, 11, 21,14,21);
        MobileContract defaultMob4=new MobileContract(12,number4,userDate4);
        defaultCust2.getContractList().add(defaultMob4);
        
        
    }

    /**
     * 
     * set the variables of a costumer object and added in the list of 
     * the customers
     *
     */
    public void addCustomer() {

        boolean run;
        
        //a while to run this option until user gives valid input
        run=true;
        while(run){
            
            //VAT CUSTOMER
            System.out.println("\tPlease enter your Vat.");
           

            try{
                this.userVatNumber = customerInfo.nextInt();
            
            //To check the input of user and dont allow to put an not int type 
            }catch(InputMismatchException e){
                
                customerInfo.nextLine();
                System.out.println("Invalid input please only integers");
                
                continue;
            }
            
            //check if user gives a valid Vat
            if (!checkVat(this.userVatNumber)) {
               continue;
            }
           
            //check if already exist another customer with this vat
            if (this.findVat(this.userVatNumber) != null){
                System.out.printf("\tThis customer already exist.Please try something else\n");
                continue;
            }
            
            run=false;
        }
        //a while to run this option until user gives valid input
        run=true;
        while(run){
            
            //ID CUSTOMER
            System.out.printf("\tPlease enter your identity number.Like am1234...\n");
            this.userId = customerInfo.next();
            
            //check if user gives a valid id
            if(!checkId(userId)){
                continue;
            }
            //check if already exist another customer with this id
            if (this.findId(this.userId) != null) {
                System.out.printf("\tThis customer already exist.Please try something else\n");
                continue;
            }
            run=false;
        }
        //a while to run this option until user gives valid input
        run=true;
        while(run){
            
            //EMAIL CUSTOMER
            System.out.printf("\tPlease enter your email.\n");
            this.userEmail = customerInfo.next();
            
            //check if user gives a valid email
            if (!checkEmail(this.userEmail)) {
                continue;
            }
            run=false;
        }
        
        //ADDRESS CUSTOMER
        System.out.printf("\tPlease enter your address with no spaces between.\n");
        
        //Call the contrustor of the Customer class to saved all the date that user gives and don't call set and get so many times
        Customer customer = new Customer(this.userVatNumber,this.userId,this.userEmail,customerInfo.next());
        
        //a while to run this option until user gives valid input
        run=true;
        while(run){
            
            //PROPERTY CUSTOMER
            System.out.printf("\tPlease enter your property.\n"
                    + "for civilian press 0 \n for student press 1\n "
                    + "for professional press 2\n");
            
            //choose property
            switch (customerInfo.next()) {
                case "0":
                    customer.setProperty(CIVILIAN);
                    break;
                case "1":
                    customer.setProperty(STUDENT);
                    //add this discount amount to already discount of this user
                    customer.setDiscountAmount(15);
                    break;
                case "2":
                    customer.setProperty(PROFESSIONAL);
                    //add this discount amount to already discount of this user
                    customer.setDiscountAmount(10);
                    break;
                default:
                    System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                    continue;
            }
            run=false;
        }

        //add this customer that created in the list of existing customers
        customerList.add(customer);

    }

    /**
     * Search if this vat already used
     * and return the customers with this vat
     *
     * @param int userVat the vat that user gave
     * @return Customer that find in the Customer list
     */
    public Customer findVat(int userVat) {

        for (int i = 0; i < customerList.size(); i++) {

            if (customerList.get(i).getVatNumber() == userVat) {
                return customerList.get(i);
            }

        }
        //if user with this vat does not exist
        return null;
    }

    /**
     * Search if this id already used
     * and return the customers with this id
     *
     * @param String userId the id that user gave
     * @return Customer that find in the Customer list
     */
    public Customer findId(String userId) {

        for (int i = 0; i < customerList.size(); i++) {
            
            if (customerList.get(i).getId().compareTo(userId) == 0) {
                return customerList.get(i);
            }
        }
        //if user with this vat does not exist
        return null;
    }
    
    /**
     * Search if this phone number already used
     * 
     * @param BigInteger userPhone int userVat the phone and vat that user gave
     * @return true if everything went right and false when user gave a  not valid input
     */
    public boolean checkPhoneNumber(BigInteger userPhone,int userVat){
        for (int i = 0; i < customerList.size(); i++) {
            for(int j=0;j<customerList.get(i).getContractList().size();j++){
                    if(userPhone.toString().length()!=10){
                        System.out.println("Please give a valid number of 10-digit");
                        return false;
                    }
                    if(customerList.get(i).getContractList().get(j).getPhoneNumber().equals(userPhone) && userVat!=customerList.get(i).getVatNumber()){
                        System.out.println("This phone is already used from other customer.");
                        return false;
                    }
            }
        }
        return true;
    }

    /**
     * Search if user gave a valid vat
     * 
     * @param int userVat vat that user gave
     * @return true if everything went right and false when user gave a  not valid input
     */
    public boolean checkVat(int userVat) {

        if (100000000 > userVat || 999999999 < userVat) {
            System.out.println("The vat number is not valid.Please try something "
                    + "between 100000000 and 999999999");
            return false;
        }
        return true;
    }

     /**
     * Search if user gave a valid email like artemis@gmail.com ,a@ok.in
     * check if gave @ and . in the correct position
     * 
     * @param String userEmail email that user gave
     * @return true if everything went right and false when user gave a  not valid input
     */
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

    /**
     * 
     * setting the variables of a contract object and add in the list 
     * contracts of a user
     *
     */
    public void addContract() {
        
        boolean run;
        
        run=true;
        while(run){
            
            try{
                //VAT CUSTOMER
                System.out.println("\tPlease enter your Vat.");
                tmp = customerInfo.nextInt();
                
                //find the customer with the vat that user gave
                userCust = findVat(tmp);
                
                //search if exist this customer with the vat that user gave
                if (userCust == null) {
                    System.out.println("There is not a Customer with this Vat\nPlease try again");
                    continue;
                }
                run=false;
                
                //choose a contact beetwen this two landline or mobile
                userCust.chooseContract();
                
            //To check the input of user and dont allow to put an not int type 
            }catch(InputMismatchException e){
                System.out.println("Please give a valid Vat");
                
                //clear the buffer
                customerInfo.nextLine();
                continue;
                
            }
        }

    }

    /**
     * 
     *Delete a contract from customer's contract list
     *
     */
    public void deleteContract() {

        boolean run;
        
        //a while to run this option until user gives valid input
        run=true;
        while(run){
            
            try{
                Contract currentContract;
                //VAT CUSTOMER
                System.out.println("\tPlease enter your Vat.");
                tmp = customerInfo.nextInt();
                
                //find the customer with the vat that user gave
                userCust = findVat(tmp);
                
                //search if exist this customer with the vat that user gave
                if (userCust == null) {
                    System.out.println("There is not a Customer with this Vat\nPlease"
                            + "try again");
                    continue;
                }
                
                //check if customers has contract to delete
                if(userCust.getContractList().size()==0){
                    System.out.println("This customer has not contracts.");
                    return;
                }
                
                //print the contracts to decide what contract number want to delete
                userCust.printAllContracts();
                
                //a while to run this option until user gives valid input
                run=true;
                while(run){
                    
                    try{
                        //get the number of contract that user wants to delete
                        System.out.println("Give me the Contract NUMBER that you want to delete");
                        tmp = customerInfo.nextInt();

                        //check that user gave correct input
                        if (tmp > userCust.getContractList().size() || tmp<1) {
                            System.out.println("There is not this Contract Number.Please try something else");
                            continue;
                        }
                        
                        //delete the correct contract
                        userCust.getContractList().remove(tmp-1);
                        System.out.printf("The contract %d removed successfull from the list",tmp);
                        run=false;
                     
                    //To check the input of user and dont allow to put an not int type 
                    }catch(InputMismatchException e){
                        System.out.println("Please give a valid NUMBER");
                        
                        //clear the buffer
                        customerInfo.nextLine();
                        continue;
                    }
                }
            //To check the input of user and dont allow to put an not int type 
            }catch(InputMismatchException e){
                System.out.println("Please give a valid Vat");
                
                //clear the buffer
                customerInfo.nextLine();
            }
        }
    }

    /**
     * Search if user gave a valid id like am123423
     * 
     * @param String userId id that user gave
     * @return true if everything went right and false when user gave a  not valid input
     */
    public boolean checkId(String userId){
        
        if(userId.length()!=8){
            System.out.println("You must give a valid Id of 8 characters");
            return false;
        }
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
    
     /**
     * Check if the date that given by the user is valid 
     * and does not coincide with any other contract
     * 
     * @param LocalDateTime userDateTime,BigInteger userPhone that user gave and 
     * the phone of the contract that the date is deducted
     * @return true if everything went right and false when user gave a  not valid input
     */
    public boolean checkContractTime(LocalDateTime userDateTime,BigInteger userPhone){
        
        LocalDateTime vampireAge=LocalDateTime.now().plusYears(100);
        
        if((int)userDateTime.getYear() > (int)vampireAge.getYear()){
            System.out.println("Please give a valid year.In 100 years from now we will not know if the company exists.Νor if we exist ourselves");
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
                if(customerList.get(i).getContractList().get(j).getPhoneNumber().toString().startsWith("6")){
                    
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
        System.out.println("\tMax Min and Mean from Landline to LandLine or to Mobile");
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
        System.out.println("\tMax Min and Mean from Mobile to LandLine or  to Mobile");
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
        int sumMobContr=0;
        int landcontr=0;
        int mobcontr=0;
        
        
        for (int i = 0; i < customerList.size(); i++) {
            
            for(int j=0;j<customerList.get(i).getContractList().size();j++){
                if(customerList.get(i).getContractList().get(j).getPhoneNumber().toString().startsWith("6")){
                    //number
                    sumMobContr++;
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
            
           landcontr+=customerList.get(i).getNumberOfLand();
           mobcontr+=customerList.get(i).getNumberOfMob();
        }
        this.numberOfLandContracts=landcontr;
        this.numberOfMobContracts=mobcontr;
        meanMB=sumMB/this.numberOfMobContracts;
        meanSms=sumSms/this.numberOfMobContracts;
        //For MB
        this.maxMB=maxMB;
        this.minMB=minMB;
        this.meanMB=meanMB;
        System.out.println("\tFor Mobile Contract For GB");
        System.out.println("Max of GB is:"+this.maxMB);
        System.out.println("Min of GB is:"+this.minMB);
        System.out.println("Mean of GB is:"+this.meanMB);
        
        //For SMS
        this.maxSms=maxSms;
        this.minSms=minSms;
        this.meanSms=meanSms;
        System.out.println("\tFor Mobile Contract For SMS");
        System.out.println("Max of SMS is:"+this.maxSms);
        System.out.println("Min of SMS is:"+this.minSms);
        System.out.println("Mean of SMS is:"+this.meanSms);
    }
    
    
    /**
     * 
     * Print the statistics for sms mb and calls for the 4 choice of the menu
     * 
    */
    public void printStatistics(){
        searchMinMaxMeanMBSms();
        searchMinMaxMeanCalls();
    }
    
    /**
     * 
     * Print the statistics or the discount for the user or all user's contract
     * 
    */
    public void contractsStatistics(){
        
        boolean run;
        
        //a while to run this option until user gives valid input
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
                        run=false;
                        break;
                    case "2":
                        try{
                            //VAT CUSTOMER
                            System.out.println("\tPlease enter your Vat.");
                            tmp = customerInfo.nextInt();
                            
                            //find the customer with the vat that user gave
                            userCust = findVat(tmp);
                            
                            //search if exist this customer with the vat that user gave
                            if (userCust == null) {
                                System.out.println("There is not a Customer with this Vat\nPlease try again");
                                   continue;
                               }

                            userCust.printAllContracts();
                            run=false;
                            break;
                            //To check the input of user and dont allow to put an not int type 
                           }catch(InputMismatchException e){
                               System.out.println("Please give a valid Vat");

                               //clear the buffer
                               customerInfo.nextLine();
                               continue;
                           }
                        
                    case "3":
                        try{
                            //VAT CUSTOMER
                            System.out.println("\tPlease enter your Vat.");
                            tmp = customerInfo.nextInt();
                            
                            //find the customer with the vat that user gave
                            userCust = findVat(tmp);
                            
                            //search if exist this customer with the vat that user gave
                            if (userCust == null) {
                                System.out.println("There is not a Customer with this Vat\nPlease try again");
                                   continue;
                            }
                            userCust.checkNumberOfContracts();
                            userCust.calcDiscount();

                            System.out.println("Your Discount for all your Contracts is:"+userCust.getDiscountAmount()+"%");
                            run=false;
                          break; 
                          //To check the input of user and dont allow to put an not int type 
                           }catch(InputMismatchException e){
                               System.out.println("Please give a valid Vat");

                               //clear the buffer
                               customerInfo.nextLine();
                               continue;
                           }
                    default:
                      System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
                      continue;
                }
               
        }
    }
 
    
    
    
}
