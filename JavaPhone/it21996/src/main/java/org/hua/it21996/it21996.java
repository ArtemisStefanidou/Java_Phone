/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.it21996;


import java.util.Scanner;

/**
 *
 * @author artemis
 */
public class it21996 {
   
    public static void main(String[] args)
    {
        

        boolean main;
        Company userComp= new Company();
        userComp.customersContracts();
        main=true;
        while(main){
        userComp.Menu();
       
        System.out.println("Only one number please.");
        Scanner choice = new Scanner(System.in);
        
        
        switch(choice.next()) 
        {
            case "1":

                userComp.addCustomer();
                break;
            case "2":
                
                userComp.addContract();
              break;
            case "3":
                
                userComp.deleteContract();
              break; 
            case "4":
                
                userComp.contractsStatistics();
                break;
            case "5":
                System.out.println("Exiting......");
                main=false;
                break;
            default:
              System.out.printf("\n\tChoice is not correct.Please try something different\n\n");
              break;
        }
        
    }
    }
}
