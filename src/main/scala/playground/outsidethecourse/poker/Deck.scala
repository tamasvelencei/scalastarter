package playground.outsidethecourse.poker

import scala.util.Random

object Deck {
	def apply(): Deck = {
		new Deck().init
	}
}

class Deck{
	val deck: Array[Card] = new Array[Card](52)
	
	def shuffleOnce(index: Int = 0, doIt: Boolean = true): Array[Card] = {
		if (index < 51 && doIt){
			val card1 = deck.apply(index)
			val card2 = deck.apply(index + 1)
			deck.update(index, card2)
			deck.update(index + 1, card1)
			shuffleOnce(index + 1, Random.nextBoolean())
		}
		else if (index < 52 && !doIt) shuffleOnce(index + 1, !doIt)
		else deck
	}
	
	def shuffleMore(repeat: Int, acc: Int = 0): Array[Card] = {
		if (acc < repeat) {
			shuffleOnce()
			shuffleMore(repeat, acc + 1)
		}
		else deck
	}
	
	def init: Deck = {
		deck.update(0, Card("spade", 14, "Ace"))
		deck.update(1, Card("spade", 13, "King"))
		deck.update(2, Card("spade", 12, "Queen"))
		deck.update(3, Card("spade", 11, "Jack"))
		deck.update(4, Card("spade", 10, "Ten"))
		deck.update(5, Card("spade", 9, "Nine"))
		deck.update(6, Card("spade", 8, "Eight"))
		deck.update(7, Card("spade", 7, "Seven"))
		deck.update(8, Card("spade", 6, "Six"))
		deck.update(9, Card("spade", 5, "Five"))
		deck.update(10, Card("spade", 4, "Four"))
		deck.update(11, Card("spade", 3, "Three"))
		deck.update(12, Card("spade", 2, "Two"))
		
		deck.update(13, Card("heart", 14, "Ace"))
		deck.update(14, Card("heart", 13, "King"))
		deck.update(15, Card("heart", 12, "Queen"))
		deck.update(16, Card("heart", 11, "Jack"))
		deck.update(17, Card("heart", 10, "Ten"))
		deck.update(18, Card("heart", 9, "Nine"))
		deck.update(19, Card("heart", 8, "Eight"))
		deck.update(20, Card("heart", 7, "Seven"))
		deck.update(21, Card("heart", 6, "Six"))
		deck.update(22, Card("heart", 5, "Five"))
		deck.update(23, Card("heart", 4, "Four"))
		deck.update(24, Card("heart", 3, "Three"))
		deck.update(25, Card("heart", 2, "Two"))
		
		deck.update(26, Card("diamond", 14, "Ace"))
		deck.update(27, Card("diamond", 13, "King"))
		deck.update(28, Card("diamond", 12, "Queen"))
		deck.update(29, Card("diamond", 11, "Jack"))
		deck.update(30, Card("diamond", 10, "Ten"))
		deck.update(31, Card("diamond", 9, "Nine"))
		deck.update(32, Card("diamond", 8, "Eight"))
		deck.update(33, Card("diamond", 7, "Seven"))
		deck.update(34, Card("diamond", 6, "Six"))
		deck.update(35, Card("diamond", 5, "Five"))
		deck.update(36, Card("diamond", 4, "Four"))
		deck.update(37, Card("diamond", 3, "Three"))
		deck.update(38, Card("diamond", 2, "Two"))
		
		deck.update(39, Card("club", 14, "Ace"))
		deck.update(40, Card("club", 13, "King"))
		deck.update(41, Card("club", 12, "Queen"))
		deck.update(42, Card("club", 11, "Jack"))
		deck.update(43, Card("club", 10, "Ten"))
		deck.update(44, Card("club", 9, "Nine"))
		deck.update(45, Card("club", 8, "Eight"))
		deck.update(46, Card("club", 7, "Seven"))
		deck.update(47, Card("club", 6, "Six"))
		deck.update(48, Card("club", 5, "Five"))
		deck.update(49, Card("club", 4, "Four"))
		deck.update(50, Card("club", 3, "Three"))
		deck.update(51, Card("club", 2, "Two"))
		this
	}
}