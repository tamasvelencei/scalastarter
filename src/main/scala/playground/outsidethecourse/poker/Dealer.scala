package playground.outsidethecourse.poker

object Dealer {
	def apply(deck: Deck): Dealer = new Dealer(deck)
}

class Dealer(val deck: Deck){
	
	def shuffle(times: Int): Array[Card] = deck shuffleMore times
	
	def deal: Array[Card] = {
		val dealtCards: Array[Card] = new Array[Card](7)
		deck.deck.copyToArray(dealtCards)
		dealtCards
	}
}