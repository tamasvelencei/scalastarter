package execises.part2oop

abstract class MyGenericList[+A] {

	def head: A
	def tail: MyGenericList[A]
	def isEmpty: Boolean
	def add[B >: A](element: B): MyGenericList[B]
	//polymorphic call
	def printElements: String

	override def toString: String = "[" + printElements + "]"
}

object GenericEmpty extends MyGenericList[Nothing] {
	override def head: Nothing = throw new NoSuchElementException

	override def tail: MyGenericList[Nothing] = throw new NoSuchElementException
 
	override def isEmpty: Boolean = true

	override def add[B >: Nothing](element: B): MyGenericList[B] = new GenericCons(element, GenericEmpty)

	def printElements: String = ""
}

class GenericCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
	override def head: A = h

	override def tail: MyGenericList[A] = t

	override def isEmpty: Boolean = false

	override def add[B >: A](element: B): MyGenericList[B] = new GenericCons(element, this)

	override def printElements: String =
		if (t.isEmpty) "" + h
		else h + " " + t.printElements
}

object Test extends App{
	
	val listInt = new GenericCons(1, new GenericCons(2, new GenericCons(3.2, GenericEmpty)))
	val listStr = new GenericCons("A", new GenericCons("B", new GenericCons("C", GenericEmpty)))
	println(listInt.head)
	println(listInt.tail.head)
	println(listInt.add(4).head)
	println(listInt.isEmpty)
	println(listInt.toString)
	
	println(listStr.head)
	println(listStr.tail.head)
	println(listStr.add("D").head)
	println(listStr.isEmpty)
	println(listStr.toString)
	
}