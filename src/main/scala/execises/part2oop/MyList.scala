package execises.part2oop

abstract class MyList {
	
	def head: Any
	def tail: MyList
	def isEmpty: Boolean
	def add(element: Int): MyList
	//polymorphic call
	def printElements: String
	
	override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList{
	override def head: Any = throw new NoSuchElementException
	
	override def tail: MyList = throw new NoSuchElementException
	
	override def isEmpty: Boolean = true
	
	override def add(element: Int): MyList = new Cons(element, Empty)
	
	def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
	override def head: Any = h
	
	override def tail: MyList = t
	
	override def isEmpty: Boolean = false
	
	override def add(element: Int): MyList = new Cons(element, this)
	
	override def printElements: String =
		if (t.isEmpty) "" + h
		else h + " " + t.printElements
}

object Run extends App{
	
	val list = new GenericCons(1, new GenericCons(2, new GenericCons(3, GenericEmpty)))
	println(list.head)
	println(list.tail.head)
	println(list.add(4).head)
	println(list.isEmpty)
	println(list.toString)
}