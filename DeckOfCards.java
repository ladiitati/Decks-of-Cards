public class DeckOfCards {
    public static void main(String[] args) {
        testCard();
        testHand();
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

        card3.set('1', Card.Suit.diamonds);
        card1.set('Z', Card.Suit.clubs);

        System.out.println("\n" + testStatement);
    }

    public static void testHand() {
        Card card1 = new Card('9', Card.Suit.spades);
        Card card2 = new Card('3', Card.Suit.diamonds);
        Card card3 = new Card('A', Card.Suit.clubs);

        Hand hand = new Hand();

        for (int i = 0; i < hand.MAX_CARDS / 5; i++) {
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

        for (int i = hand.getNumCards(); i > 0; i--) {
            System.out.println("Playing " + hand.playCard());
        }
        System.out.println("After playing all cards:\n" + hand);

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
        char[] validValues = new char[]{'2', '3', '4', '5', '6', '7', '8', '9', 'A', 'K', 'Q', 'J'};

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
    public int MAX_CARDS = 50;

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
        for (int i = 0; i < numCards; i++) {
            output += myCards[i] + ", ";
            if (i > 1 && i % 6 == 0) {
                output += "\n";
            }
        }
        output += " )";
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
