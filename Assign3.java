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
