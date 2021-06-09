package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends CasinoPerson {
    private final String DEALER_NAME = "딜러";
    private final int SCORE_LIMIT = 21;
    private List<Card> holdingCards = new ArrayList<>();
    private int addedResult;
    private final int DEALER_SCORE_LIMIT = 16;
    private int winCount = 0;
    private int loseCount = 0;


    public Dealer() {
    }

    public Dealer(List<Card> holdingCards) {
        this.holdingCards = holdingCards;
    }


    @Override
    boolean isDrawCard(int score) {
        return score <= DEALER_SCORE_LIMIT;
    }

    @Override
    public void getCard(Card get_card) {
        this.holdingCards.add(get_card);
    }

    public int sumCards() {
        addedResult = 0;
        for (Card card : holdingCards) {
            addedResult += findCardNumber(card.getCardNumber());
        }
        return addedResult;
    }


    private int findCardNumber(CardNumber cardNumber) {
        if (cardNumber.equals(CardNumber.ACE)) {
            return findAceCondition(addedResult);
        }
        return cardNumber.getScore();
    }

    private int findAceCondition(int addedResult) {
        int aceScore = 0;
        int minScore = SCORE_LIMIT - (addedResult + CardNumber.ACE.getMinScore());
        int maxScore = SCORE_LIMIT - (addedResult + CardNumber.ACE.getMaxScore());
        aceScore = gap(minScore, maxScore);
        return aceScore;
    }

    private int gap(int minScore, int maxScore) {
        if (minScore <= maxScore) {
            return CardNumber.ACE.getMinScore();
        }
        return CardNumber.ACE.getMaxScore();
    }

    public void winOrLose(boolean playerResult) {
        bust();
        if (playerResult == true) {
            loseCount++;
        }
        if (playerResult == false) {
            winCount++;
        }
    }

    private void bust() {
        if (this.sumCards() > SCORE_LIMIT) {
            loseCount++;
        }
    }

    public List<Card> getHoldingCards() {
        return holdingCards;
    }

    public String getDEALER_NAME() {
        return DEALER_NAME;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
