package playground.outsidethecourse.poker

object Evaluator {
	def apply(dealtCards: List[Card]): Evaluator = new Evaluator(dealtCards)
}

class Evaluator(dealtCards: List[Card]){
	
	def hasStraight: List[Card] = {
		def inner(filteredList: List[Card], straight: List[Card], count: Int = 0): List[Card] = {
			if (count == 4) straight.+:(filteredList.head).reverse
			else if (filteredList.size <= 1) Nil
			else if (filteredList.head.value - 1 == filteredList.apply(1).value)
				inner(filteredList.takeRight(filteredList.size - 1), straight.+:(filteredList.head), count + 1)
			else
				inner(filteredList.takeRight(filteredList.size - 1), List())
		}
		var filtered: List[Card] = dealtCards.toSet.toList
		filtered = filtered.sortBy(_.value).reverse
		if (filtered.count(x => x.value == 14 || x.value == 5 || x.value == 4 || x.value == 3 || x.value == 2) == 5) //TODO: Pattern Matching
			filtered.filter(x => x.value == 14 || x.value == 5 || x.value == 4 || x.value == 3 || x.value == 2)
		else inner(filtered, List())
	}
	
	def hasFlush(getHighest: Boolean = true): List[Card] = {
		def inner(colors: List[String]): List[Card] = {
			val list = dealtCards.filter(x => x.color equals colors.head).sortBy(_.value)
			if (list.size > 4 && getHighest) list.sortBy(_.value).takeRight(5).reverse
			else if (list.size > 4 && !getHighest) list.sortBy(_.value).reverse
			else if (colors.size > 1) inner(colors.takeRight(colors.size - 1))
			else Nil
		}
		inner(List[String]("spade", "club", "diamond", "heart"))
	}
	
	def hasStraightFlush: List[Card] = {
		val list = hasFlush(false)
		if (list.size > 4) Evaluator(list).hasStraight
		else Nil
	}
	
	def hasRoyalFlush: List[Card] = {
		val list = hasStraightFlush
		if (list.size == 5 && list.head.value == 14 && list.apply(1).value == 13) list
		else Nil
	}
	
	def hasOnePair: List[Card] = {
		val list = sortByFrequencies
		if (list.head._2 == 2) list.map(x => x._1)
		else Nil
	}
	
	def hasTwoPair: List[Card] = {
		val list = sortByFrequencies
		if (list.head._2 == 2 && list.apply(4)._2 == 2) list.map(x => x._1)
		else if (list.head._2 == 2 && list.apply(2)._2 == 2) list.map(x => x._1)
		else Nil
	}
	
	def hasThreeOfAKind: List[Card] = {
		val list = sortByFrequencies
		if (list.head._2 == 3) list.map(x => x._1)
		else Nil
	}
	
	def hasFullHouse: List[Card] = {
		val list = sortByFrequencies
		if (list.head._2 == 3 && list.apply(3)._2 == 2) list.map(x => x._1)
		else Nil
	}
	
	def hasFourOfAKind: List[Card] = {
		val list = sortByFrequencies
		if (list.head._2 == 4) list.map(x => x._1)
		else Nil
	}
	
	def sortByFrequencies: List[(Card, Int)] = {
		dealtCards.map(x => (x, dealtCards.count(_.value == x.value))).sortBy(_._2).reverse
	}
}