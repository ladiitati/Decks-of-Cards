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
        testDeck();
        int numOfPlayers = 0;
        //takes users input
        do {
            System.out.println("Enter how many players (1 - 10):");
            numOfPlayers = scannerObject.nextInt();

        } while (numOfPlayers < 1 || numOfPlayers > 10);

        scannerObject.close();

        System.out.println("Number of players: " + numOfPlayers + "\n");

        Deck deck = new Deck(1);
        
        Hand[] playersHand = new Hand[numOfPlayers];
        //instantiate players hands
        for(int i = 0; i < numOfPlayers; i++) {
            playersHand[i] = new Hand();
         }
        
        int deckSize = deck.getTopCard();
              
        //deals players card, print hands and resets them twice
        for(int dealLoop = 0; dealLoop < 2; dealLoop++) {
           
         //deals cards to player
           int counter = 0;

           while (counter < deckSize) {
               for (int j = 0; j < numOfPlayers; j++) {
                  playersHand[j].takeCard(deck.dealCard());
                  if (++counter == deckSize) {
                      j = numOfPlayers;
                  }
               }
            }
   
           //prints out players hand and resets them
           if (dealLoop == 0) {
               System.out.println("Here are the hands from an unshuffled deck:");
           }
           else {
               System.out.println("Here are the hands from a SHUFFLED deck:");
           }
           int playerCount = 1;
           for (Hand player: playersHand) {
              int up = playerCount - 1;
              System.out.println("Player " + playerCount + " " + player);
              playersHand[up].resetHand();          
              playerCount++;
           }
           //Reset deck for shuffle
           deck = new Deck(1);
           deck.shuffle();
        }
    }
    
    //Deck test function from Phase 3
    public static void testDeck(){
        System.out.println("\nPrinting out double deck in-order:");
        //Declare deck with a size of two packs and output cards
        Deck testingDeck = new Deck(2);
        int testingDeckSize = testingDeck.getTopCard();
        for (int i = 0; i < testingDeckSize; i++) {
            Card outputCard = testingDeck.dealCard();
            System.out.print(outputCard.toString() + " / ");
        }
        System.out.println("\nDouble deck deal complete.");
        System.out.println("Let's reset and shuffle.");
        //Reset the deck and shuffle, then output cards
        testingDeck = new Deck(2);
        testingDeck.shuffle();
        for (int i = 0; i < testingDeckSize; i++) {
            Card outputCard = testingDeck.dealCard();
            System.out.print(outputCard.toString() + " / ");
        }
        System.out.println("\nShuffled double deck deal complete.");
        
        System.out.println("\nPrinting out single deck in-order:");
        //Declare deck with a size of one pack and output cards
        testingDeck = new Deck(1);
        testingDeckSize = testingDeck.getTopCard();
        for (int i = 0; i < testingDeckSize; i++) {
            Card outputCard = testingDeck.dealCard();
            System.out.print(outputCard.toString() + " / ");
        }
        System.out.println("\nSingle deck deal complete.");
        System.out.println("Let's reset and shuffle.");
        //Reset the deck and shuffle, then output cards
        testingDeck = new Deck(1);
        testingDeck.shuffle();
        for (int i = 0; i < testingDeckSize; i++) {
            Card outputCard = testingDeck.dealCard();
            System.out.print(outputCard.toString() + " / ");
        }
        System.out.println("\nShuffled single deck deal complete.\n");
    }

}

class Card {
    enum Suit {clubs, diamonds, hearts, spades}

    char value;
    Suit suit;
    boolean errorFlag;

    Card(char value, Suit suit) {
        set(value, suit);
    }

    // overload for no parameters case
    Card() {
        set('A', Suit.spades);
    }

    public boolean set(char value, Suit suit) {
        if (isValid(value, suit)) { // only set if input is valid
            this.value = value;
            this.suit = suit;
            setErrorFlag(false);
            return true;
        }
        setErrorFlag(true);
        return false;
    }

    public String toString() {
        if (errorFlag) {
            return "[invalid]";
        }
        return value + " of " + suit;
    }

    public boolean equals(Card card) {
        boolean equalValues = value == card.getValue();
        boolean sameSuit = suit == card.getSuit();

        return equalValues && sameSuit;
    }

    public char getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    private boolean isValid(char value, Suit suit) {
        char[] validValues = new char[]
                {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'A', 'K', 'Q', 'J'};

        // search every valid value for a match - return true as soon as match found
        for (int i = 0; i < validValues.length; i++) {
            if (value == validValues[i]) {
                return true;
            }
        }
        return false;
    }
}

class Hand {
    public int MAX_CARDS = 100;

    private Card[] myCards;
    private int numCards;

    public Hand() {
        myCards = new Card[MAX_CARDS];
        numCards = 0;
    }

    public void resetHand() {
        numCards = 0;
    }

    public boolean takeCard(Card card) {
       
        Card newCard = new Card(card.getValue(), card.getSuit());
        if (numCards > MAX_CARDS) {
            return false;
        } else {
            myCards[numCards] = newCard;
            numCards++;
            return true;
        }
    }

    public Card playCard() {
        numCards--;
        Card card = new Card(myCards[numCards].getValue(),
                myCards[numCards].getSuit());
        return card;
    }

    public String toString() {
       String output = new String();
       output = "Hand: ( ";
       //add each card to output string
       for (int i = 0; i < numCards; i++) {
           output += myCards[i];
           if (i + 1 != numCards) {
               output += ", ";
           }
       }
       output += " )\n";
       //add newline every 100 characters
       for (int i = 100; i <= output.length(); i += 100) {
           output = output.substring(0, i) + "\n" + output.substring(i);
       }
       return output;
    }

    public int getNumCards() {
        return numCards;
    }

    public Card inspectCard(int k) {
        Card card = new Card();

        if (k > numCards) {
            card.setErrorFlag(true);
        } else {
            card.set(myCards[k].getValue(), myCards[k].getSuit());
        }

        return card;
    }
}

class Deck {
    public static final int MAX_CARDS = 6 * 52;

    private static Card[] masterPack;
    private Card[] cards = new Card[MAX_CARDS];
    private int topCard;

    //Constructor that populates the Card array
    public Deck(int numPacks) {
        allocateMasterPack();
        init(numPacks);
    }

    //Overload when no parameters
    public Deck() {
        allocateMasterPack();
        init(1);
    }

    //Re-populates cards[] with the designated number of packs of cards
    public void init(int numPacks) {
        //Find total number of cards
        topCard = (52 * numPacks);
        if (topCard <= MAX_CARDS) {
            //Create number of cards required from how many packs needed
            cards = new Card[52 * numPacks];
            int j = 0;
            //Loop for the amount of packs required
            for (int i = 0; i < numPacks; i++) {
                //Loop through every Card object of masterPack array to add to deck
                for (Card card : masterPack) {
                    cards[j] = card;
                    j++;
                }
            }
        }
    }

    //Shuffling the cards using random number generator
    public void shuffle() {
        Random rand = new Random();
        for (int j = 0; j <= cards.length - 1; j++) {
            //Find next random card position between 0 and total # of cards
            int randIndex = rand.nextInt(cards.length);
            //Swap selected card with current card
            Card temp = cards[randIndex];
            cards[randIndex] = cards[j];
            cards[j] = temp;
        }
    }

    //Returns and removes the card at top position of cards[]
    public Card dealCard() {
        //Check if cards are still available
        if (topCard < 0) {
            return null;
        }
        //Move onto next card
        topCard--;
        //Get card information
        Card dealtCard = cards[topCard];
        //Delete card info and return it
        cards[topCard] = null;
        return dealtCard;
    }

    //Accessor for topCard
    public int getTopCard() {
        return topCard;
    }

    //Access for an individual card
    public Card inspectCard(int k) {
        Card card = new Card();
        if (k < 0 || k > topCard) {
            card.setErrorFlag(true);
        } else {
            card = cards[k];
        }

        return card;
    }

    //Generating the deck
    private static void allocateMasterPack() {
        if (masterPack != null) {
            return;
        }

        masterPack = new Card[52];
        char[] valueArray = new char[]
                {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

        int i = 0;
        //Use for-each loop to go through all suits in the enum
        for (Card.Suit suit : Card.Suit.values()) {
            //Use for-each loop to assign a card with each value in the current suit
            for (char value : valueArray) {
                masterPack[i] = new Card(value, suit);
                i++;
            }
        }

    }
}
