public class Flush extends Hand {

  public Flush(Card[] hand){
    super(Util.FLUSH, hand);  
  }

  @Override
  protected String createString(Card[] hand){ 
    int[] orderedCards = new int[Util.HAND_SIZE];
    for (int i = 0; i < hand.length; i++){
      orderedCards[i] = Util.interpretValue(hand[i].getValue());
    }
    Util.sort(orderedCards, 0);
    String orderOfCards = "";
    for (int card: orderedCards){
      orderOfCards += Util.translateValueToChar(card);
    }
    return orderOfCards;
  }
  
}

