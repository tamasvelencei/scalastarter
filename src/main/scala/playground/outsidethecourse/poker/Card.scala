package playground.outsidethecourse.poker

case object Card{
	def apply(name: String, value: Int, color: String): Card = new Card(name, value, color)
}

case class Card(color: String, value: Int, name: String) extends Ordered[Card]{
	override def toString: String = s"$name of $color"
	
	def canEqual(a: Any): Boolean = a.isInstanceOf[Card]
	override def equals(that: scala.Any): Boolean = {
		that match {
			case that: Card => that.canEqual(this) && this.value == that.value
			case _ => false
		}
	}
	
	override def compare(that: Card): Int = this.value compare that.value
}
