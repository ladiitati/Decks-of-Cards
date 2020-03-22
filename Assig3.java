import java.util.Random;

public class Assig3 {
    public static void main(String[] args) {
        testCard();
        testHand();
        testDeck();
    }

    /**
     * this function exists solely for testing the Card class.
     * It can be safely removed later.
     */
    public static void testCard() {
        Card card1 = new Card('K', Card.Suit.clubs);
        Card card2 = new Card('A', Card.Suit.hearts);
        Card card3 = new Card('D', Card.Suit.diamonds);

        String testStatement = card1.toString() + "\n" + card2.toString() + "\n" + card3.toString();
        System.out.println(testStatement);

        card1.set('Z', Card.Suit.clubs);

        System.out.println("\n" + testStatement);
    }
    
    public static void testHand()
    {
       Card card1 = new Card('9', Card.Suit.spades);
       Card card2 = new Card('3', Card.Suit.diamonds);
       Card card3 = new Card('A', Card.Suit.clubs);
       
       Hand hand = new Hand();
       
       for (int i = 0; i < hand.MAX_CARDS/5; i++)
       {
          hand.takeCard(card3);
          hand.takeCard(card1);
          hand.takeCard(card2);
          hand.takeCard(card3);
          hand.takeCard(card1);
       }    
       
       System.out.println(hand);
       
       System.out.println("Testing inspectCard()");
       System.out.println(hand.inspectCard(4));
       System.out.println(hand.inspectCard(99));
       
       for(int i = hand.getNumCards(); i > 0; i--)
       {
          System.out.println("Playing " + hand.playCard()); 
       }
       System.out.println("After playing all cards:\n" + hand);
       
    }
    
    public static void testDeck(){
    	System.out.println("\nPrinting out double deck in-order:");
    	Deck deck = new Deck(2);
    	for (int i = 0; i < 104; i++) {
    		Card outputCard = deck.dealCard();
    		System.out.print(outputCard.toString() + " / ");
    	}
    	System.out.println("\nDouble deck deal complete.");
    	System.out.println("Reset and shuffle!");
    	deck = new Deck(2);
    	deck.shuffle();
    	for (int i = 0; i < 104; i++) {
    		Card outputCard = deck.dealCard();
    		System.out.print(outputCard.toString() + " / ");
    	}
    	System.out.println("\nShuffled double deck deal complete.");
    	
    	System.out.println("\nPrinting out single deck in-order:");
    	deck = new Deck(1);
    	for (int i = 0; i < 52; i++) {
    		Card outputCard = deck.dealCard();
    		System.out.print(outputCard.toString() + " / ");
    	}
    	System.out.println("\nSingle deck deal complete.");
    	System.out.println("Reset and shuffle!");
    	deck = new Deck(1);
    	deck.shuffle();
    	for (int i = 0; i < 52; i++) {
    		Card outputCard = deck.dealCard();
    		System.out.print(outputCard.toString() + " / ");
    	}
    	System.out.println("\nShuffled single deck deal complete.");
    	
        System.out.println("\nTesting inspectCard()");
    	deck = new Deck(1);
        System.out.println(deck.inspectCard(0));
        System.out.println(deck.inspectCard(51));
        System.out.println(deck.inspectCard(99));
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
        char[] validValues = new char[]{'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'A', 'K', 'Q', 'J'};

        // search every valid value for a match - return true as soon as match found
        for (int i = 0; i < validValues.length; i++) {
            if (value == validValues[i]) {
                return true;
            }
        }
        return false;
    }
}

class Hand
{
   public int MAX_CARDS = 50;
   
   private Card[] myCards;
   private int numCards;
   
   public Hand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }
   
   public void resetHand()
   {
      numCards = 0;
   }
   
   public boolean takeCard(Card card)
   {
      Card newCard = new Card(card.getValue(), card.getSuit());
      
      if (numCards > MAX_CARDS)
      {
         return false;
      }
      else
      {
         myCards[numCards] = newCard;
         numCards++;  
         return true;
      }
   }
   
   public Card playCard()
   {
      numCards--;
      Card card = new Card(myCards[numCards].getValue(), 
            myCards[numCards].getSuit());
      return card;
   }
   
   public String toString()
   {
      String output = new String();
      output = "Hand: ( ";
      for (int i = 0; i < numCards; i++)
      {
         output += myCards[i] + ", ";
         if (i > 1 && i % 6 == 0)
         {
            output += "\n";
         }
      }
      output += " )";
      return output;
   }
   
   public int getNumCards()
   {
      return numCards;
   }
   
   public Card inspectCard(int k)
   {
      Card card = new Card();
      
      if (k > numCards)
      {
         card.setErrorFlag(true);
      }
      else
      {
         card.set(myCards[k].getValue(), myCards[k].getSuit());
      }
      
      return card;
   }
} 

class Deck {
	public static final int MAX_CARDS = 6* 52;
	
	private static Card[] masterPack;
	private Card[] cards;
	private int topCard;
	
	public Deck(int numPacks) {
		allocateMasterPack();
		init(numPacks);
	}
	
	//Overload when no parameters
	public Deck() {
		init(1);
	}
	
	public void init(int numPacks) {
		topCard = (52 * numPacks);
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
	
	//Shuffling the cards using random number generator
	public void shuffle() {
                Random rand = new Random();
                for (int j = 0; j < 3; j ++ ) {
                   for (int i = 0; i < cards.length; i ++ ){
                      int randIndex = rand.nextInt(cards.length);
                      Card temp = cards[randIndex];
                      cards[randIndex] = cards[i];
                      cards[i] = temp;
                   }
                }
    }	
	//Returns and removes the card at top position of cards[]
	public Card dealCard() {
		//Check if cards are still available
		if (topCard <= 0) {
			return null;
		}
		//Move onto next card
		topCard --;
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
			}
	      else{
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
		char[] valueArray = new char[]{'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
		
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