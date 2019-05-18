package playground.outsidethecourse

import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import playground.outsidethecourse.poker.{Card, Evaluator}

@RunWith(classOf[Theories])
class EvaluatorTest{
	
	@Theory
	def testStraight(hand: List[Card]): Unit ={
		val evaluator: Evaluator = Evaluator(hand)
		println(s"Straight: ${evaluator.hasStraight}")
	}
	
	@Theory
	def testFlush(hand: List[Card]): Unit ={
		val evaluator: Evaluator = Evaluator(hand)
		println(s"Flush: ${evaluator.hasFlush()}")
	}
	
	@Theory
	def testStraightFlush(hand: List[Card]): Unit ={
		val evaluator: Evaluator = Evaluator(hand)
		println(s"StraightFlush: ${evaluator.hasStraightFlush}")
	}
	
	@Theory
	def testRoyalFlush(hand: List[Card]): Unit ={
		val evaluator: Evaluator = Evaluator(hand)
		println(s"RoyalFlush: ${evaluator.hasRoyalFlush}")
	}
	
	@Theory
	def testOnePair(hand: List[Card]): Unit = {
		val evaluator: Evaluator = Evaluator(hand)
		evaluator.hasOnePair
		println(s"OnePair: ${evaluator.hasOnePair}")
	}
	
	@Theory
	def testTwoPair(hand: List[Card]): Unit = {
		val evaluator: Evaluator = Evaluator(hand)
		println(s"TwoPair: ${evaluator.hasTwoPair}")
	}
	
	@Theory
	def testThreeOfAKind(hand: List[Card]): Unit = {
		val evaluator: Evaluator = Evaluator(hand)
		evaluator.hasThreeOfAKind
		println(s"ThreeOfAKind: ${evaluator.hasThreeOfAKind}")
	}
	
	@Theory
	def testFullHouse(hand: List[Card]): Unit = {
		val evaluator: Evaluator = Evaluator(hand)
		println(s"FullHouse: ${evaluator.hasFullHouse}")
	}
	
	@Theory
	def testFourOfAKind(hand: List[Card]): Unit = {
		val evaluator: Evaluator = Evaluator(hand)
		println(s"FourOfAKind: ${evaluator.hasFourOfAKind}")
	}
}

object EvaluatorTest {
	
	@DataPoints
	def straights(): Array[List[Card]] = {
		
		val straightFlush: List[Card] = List(
			new Card("spade", 14, "Ace")
			, new Card("spade", 13, "King")
			, new Card("spade", 10, "Ten")
			, new Card("spade", 9, "Nine")
			, new Card("spade", 8, "Eight")
			, new Card("spade", 7, "Seven")
			, new Card("spade", 6, "Six")
		)
		
		val royalFlush: List[Card] = List(
			new Card("spade", 14, "Ace")
			, new Card("spade", 13, "King")
			, new Card("spade", 12, "Queen")
			, new Card("spade", 11, "Jack")
			, new Card("spade", 10, "Ten")
			, new Card("spade", 8, "Eight")
			, new Card("spade", 7, "Seven")
		)
		
		val straight: List[Card] = List(
			new Card("spade", 14, "Ace")
			, new Card("spade", 12, "Queen")
			, new Card("spade", 11, "Jack")
			, new Card("heart", 10, "Ten")
			, new Card("spade", 9, "Nine")
			, new Card("spade", 8, "Eight")
			, new Card("spade", 6, "Six")
		)
		
		val straightWithTwoPair: List[Card] = List(
			new Card("spade", 14, "Ace")
			, new Card("spade", 5, "Five")
			, new Card("heart", 5, "Five")
			, new Card("club", 4, "Four")
			, new Card("spade", 4, "Four")
			, new Card("spade", 3, "Three")
			, new Card("spade", 2, "Two")
		)
		
		val notStraightButPair: List[Card] = List(
			new Card("spade", 14, "Ace")
			, new Card("spade", 12, "Queen")
			, new Card("heart", 10, "Ten")
			, new Card("spade", 10, "Ten")
			, new Card("spade", 8, "Eight")
			, new Card("spade", 7, "Seven")
			, new Card("spade", 6, "Six")
		)
		
		val notFlushButFullHouse: List[Card] = List(
			new Card("heart", 14, "Ace")
			, new Card("heart", 12, "Queen")
			, new Card("club", 8, "Eight")
			, new Card("club", 12, "Queen")
			, new Card("spade", 8, "Eight")
			, new Card("spade", 14, "Ace")
			, new Card("diamond", 8, "Eight")
		)
		
		val fourOfAKind: List[Card] = List(
			new Card("heart", 14, "Ace")
			, new Card("heart", 10, "Ten")
			, new Card("club", 8, "Eight")
			, new Card("club", 10, "Ten")
			, new Card("spade", 8, "Eight")
			, new Card("spade", 10, "Ten")
			, new Card("diamond", 10, "Ten")
		)
		
		List(straightFlush, royalFlush, straight, straightWithTwoPair, notStraightButPair, notFlushButFullHouse, fourOfAKind).toArray
	}
}
