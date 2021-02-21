package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.state.PlayingCard;

import java.util.List;

public class Dealer extends Human {
    private static final String DEALER_NAME = "딜러";
    private Cards cards;

    private Count winCount = new Count(0);
    private Count lossCount = new Count(0);

    public Dealer() {
        super(DEALER_NAME);
    }

    public Integer getWinCount() {
        return winCount.getCount();
    }

    public Integer getLossCount() {
        return lossCount.getCount();
    }

    public Cards getCards() {
        return cards;
    }


    // 카드 뭉치에서 한 장을 꺼내
    // pop : 카드 뭉치에서 한 장을 뽑아 플레이어/딜러 에게 주는 것.
    public static PlayingCard popAndGiveCard() {
        PlayingCard playingCard = CardDeck.getPlayingCard();
        return playingCard;
    }

    public void winResult(List<Player> players) {
        // 얘는 humans를 받아 반복
        for (Player player : players) {
            resultOnce(player);
        }
    }

    private void resultOnce(Player player) {
        if (!player.getIsWin()) {
            winCount.increase();
            return;
        }
        lossCount.increase();
    }

    public Cards initCard() {
        Cards cards = new Cards();
        cards.add(popAndGiveCard());
        cards.add(popAndGiveCard());
        return cards;
    }

    public void initDealerCards() {
        cards = initCard();
    }

    public void drawMoreCard() {
        cards.add(popAndGiveCard());
    }
}