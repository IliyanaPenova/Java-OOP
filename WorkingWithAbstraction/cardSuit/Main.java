package WorkingWithAbstraction.cardSuit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Suits: ");
        for (CardSuits cardSuits:CardSuits.values()) {

            System.out.printf("Ordinal value: %d; Name value: %s%n",cardSuits.ordinal(),cardSuits.name());
            
        }
    }
}
