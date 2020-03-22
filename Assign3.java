import java.util.Scanner;

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
      
      for (int i = 0; i < numOfPlayers; i++) {
         playersHand[i] = new Hand();
         for (int j = 0; j < numOfPlayers; j++) {
            deck.takeCard(deck.dealCard());
         }
      }
      
      for(int k = 0; k < 52; k++) {
         hand.toString[k];
         hand.resetHand[k];
      }
      
      deck.init();
      deck.shuffle();
      
      for (int n = 0; n < numOfPlayers; n++) {
         playersHand[n] = new Hand();
         for (int m = 0; m < numOfPlayers; m++) {
            deck.takeCard(deck.dealCard());
         }
      }
      
      for(int t = 0; t < 52; t++) {
         hand.toString[t];
         hand.resetHand[t];
      }
   }
   
}
class Deck{};
class Hand{};
