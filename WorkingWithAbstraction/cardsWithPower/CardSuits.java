package WorkingWithAbstraction.cardsWithPower;

public enum CardSuits {
    CLUBS(0),DIAMONDS(13),HEARTS(26),SPADES(39);

    public int getSuitPower() {
        return suitPower;
    }

    public void setSuitPower(int suitPower) {
        this.suitPower = suitPower;
    }

    private int suitPower;

    CardSuits(int suitPower) {
        this.suitPower = suitPower;
    }
}
