package WorkingWithAbstraction.cardsWithPower;

public class Card {
    private CardSuits cardSuit;
    private CardRanks cardRanks;
    private int power;

    public Card(CardSuits cardSuit, CardRanks cardRanks) {
        this.cardSuit = cardSuit;
        this.cardRanks = cardRanks;
    }

    public CardSuits getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(CardSuits cardSuit) {
        this.cardSuit = cardSuit;
    }

    public CardRanks getCardRanks() {
        return cardRanks;
    }

    public void setCardRanks(CardRanks cardRanks) {
        this.cardRanks = cardRanks;
    }

    public int getPower() {
        return this.cardSuit.getSuitPower() + this.cardRanks.getRankPower();
    }

    public void setPower(int power) {
        this.power = power;
    }
}
