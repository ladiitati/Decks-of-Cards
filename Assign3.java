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
     // Hand hand = new Hand();
      
      Hand [] playerHands = new Hand [numOfPlayers];
      
      for (int i = 0; i < numOfPlayers; i++) {
         playerHands[i].
         for (int i = 0; i < numOfPlayers; i++)
          // playerHands[i].dealCard();
      }
   }
   
}
class Deck{};
