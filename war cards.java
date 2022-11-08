import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Card {
    private int suit;

    private int card;

    public Card( int suit, int card ) {
      super( );
      this.suit = suit;
      this.card = card;
    }

    public int getCard( ) {
      return card;
    }

    public void setCard( int card ) {
      this.card = card;
    }

    @Override
    public String toString( ) {

      StringBuilder result = new StringBuilder( );
      switch ( card ) {
        case 11 :
          result.append( "Jack" );
          break;
        case 12 :
          result.append( "Queen" );
          break;
        case 13 :
          result.append( "King" );
          break;
        case 14 :
          result.append( "Ace" );
          break;
        default :
          result.append( card );
      }
      result.append( " of " );
      switch ( suit ) {
        case 0 :
          result.append( "hearts" );
          break;
        case 1 :
          result.append( "diamonds" );
          break;
        case 2 :
          result.append( "clubs" );
          break;
        case 3 :
          result.append( "spades" );
          break;
      }
      return result.toString( );

    }

  }


public class War {

  public static void main( String[] args ) {

    List<Card> deck = new ArrayList<Card>( );

    for ( int i = 0; i < 4; i++ ) {
      for ( int j = 2; j < 15; j++ ) {
        deck.add( new Card( i, j ) );
      }
    }

    Collections.shuffle( deck, new Random( System.nanoTime( ) ) );

    LinkedList<Card> player1Cards = new LinkedList<Card>( );
    player1Cards.addAll( deck.subList( 0, 25 ) );

    LinkedList<Card> player2Cards = new LinkedList<Card>( );
    player2Cards.addAll( deck.subList( 26, deck.size( ) ) );

    while ( true ) {

      Card player1Card = player1Cards.pop( );
      Card player2Card = player2Cards.pop( );

      System.out.println( "Player 1: " + player1Card.toString( ) );
      System.out.println( "Player 2: " + player2Card.toString( ) );

      if ( player1Card.getCard( ) > player2Card.getCard( ) ) {
        player1Cards.addLast( player2Card );
        player1Cards.addLast( player1Card );
      } else if ( player1Card.getCard( ) < player2Card.getCard( ) ) {
        player2Cards.addLast( player2Card );
        player2Cards.addLast( player1Card );
      } else {
        System.out.println( "War" );
        List<Card> warCards1 = new ArrayList<Card>( );
        List<Card> warCards2 = new ArrayList<Card>( );
        for ( int i = 0; i < 3; i++ ) {
          if(player1Cards.size( )==0 || player2Cards.size( )==0){
            break;
          }
          warCards1.add( player1Cards.pop( ) );
          warCards2.add( player2Cards.pop( ) );
        }
        System.out.println("Player 1 war: " + warCards1.get( 0 ).toString( ) );
        System.out.println("Player 2 war: " + warCards2.get( 0 ).toString( ) );
        if ( warCards1.get( 0 ).getCard( ) > warCards2.get( 0 ).getCard( ) ) {
          player1Cards.addAll( warCards2 );
          player1Cards.addAll( warCards1 );
          System.out.println( "Player 1 wins war!" );
        } else {
          player2Cards.addAll( warCards2 );
          player2Cards.addAll( warCards1 );
          System.out.println( "Player 2 wins war!" );
        }
      }

      if ( player1Cards.size( ) == 0 ) {
        System.out.println( "Player 1 wins!" );
        break;
      } else if ( player2Cards.size( ) == 0 ) {
        System.out.println( "Player 2 wins!" );
        break;
      }
    }
  }
}