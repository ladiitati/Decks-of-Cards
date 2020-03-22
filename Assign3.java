/**************************************************************
 Tatiana Adams, Ryan Barrett, Matthew Taylor, Rowena Terrado
 24 March 2020
 CST 338 Software Design
 Assignment 3: Deck of Cards
 
 This program is a base program for other card game programs. There are three 
 main classes which are Card, Hand, and Deck. The card class is used to 
 instantiate a single card object setting the value and the suite with 
 validation. The Deck Hand class is used to instantiate each players hand as an 
 individual object. This class uses the card and deck class to populate each of
 these objects. This class also handles the functionality of removing cards 
 from a player’s hands like "playing a card" and drawing a card from the deck. 
 As well as validation. The Deck class instantiates n amount of decks for the 
 game. It uses the card class to populate the set number of decks. It is
 responsible for the functionality of shuffling and dealing cards. The deck 
 class uses a static array that holds every card reference of the deck. 
 ***************************************************************/
import java.util.Scanner;
import java.util.Random;

public class Assign3 {

   public static void main(String[] args) {
      
      Scanner scannerObject = new Scanner(System.in);

      int numOfPlayers = 0;
      
      do {
         System.out.println("Enter How Many Players (1 - 10)");
             numOfPlayers = scannerObject.nextInt();
         
      }while(numOfPlayers < 1 || numOfPlayers > 10);
      
      scannerObject.close();
      
      System.out.println("Number of Players " + numOfPlayers);
      
      Deck deck = new Deck();
      
      Hand[] playersHand = new Hand [numOfPlayers];
      int dealLoop = (52 / numOfPlayers) + (52 % numOfPlayers);
      
      for (int i = 0; i < dealLoop; i++) {
         playersHand[i] = new Hand();
         for (int j = 0; j < numOfPlayers; j++) {
            playersHand[j].takeCard(deck.dealCard());
         }
      }
      
      for (int i = 0; i < playersHand[i].getNumCards(); i++) {
         System.out.println("Player " + i + "Hand ( ");
         System.out.println(playersHand[i].toString());
         System.out.println(" )");
         
         playersHand[i].resetHand();
      }
      
         deck.shuffle();
      
         for (int i = 0; i < dealLoop; i++) {
            playersHand[i] = new Hand();
            for (int j = 0; j < numOfPlayers; j++) {
               playersHand[j].takeCard(deck.dealCard());
            }
         }
         
         for (int i = 0; i < playersHand[i].getNumCards(); i++) {
            System.out.println("Player " + i + "Hand ( ");
            System.out.println(playersHand[i].toString());
            System.out.println(" )");
            
            playersHand[i].resetHand();
         }
   }
   
}
