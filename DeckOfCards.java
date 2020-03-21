public class DeckOfCards {
    public static void main(String[] args) {
        testCard();
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
