package execises.part2oop

object GenericListPredicateFunc extends App{
	
	abstract class MyGenericListFunc[+A] {
		
		def head: A
		def tail: MyGenericListFunc[A]
		def isEmpty: Boolean
		def add[B >: A](element: B): MyGenericListFunc[B]
		
		//higher order functions
		def map[B](transformer: A => B): MyGenericListFunc[B]
		def filter(predicate: A => Boolean): MyGenericListFunc[A]
		def flatMap[B](transformer: A => MyGenericListFunc[B]): MyGenericListFunc[B]
		def ++[B >: A](list: MyGenericListFunc[B]): MyGenericListFunc[B]
		
		
		//polymorphic call
		def printElements: String
		override def toString: String = "[" + printElements + "]"
	}
	
	case object GenericEmpty$Func extends MyGenericListFunc[Nothing] {
		override def head: Nothing = throw new NoSuchElementException
		override def tail: MyGenericListFunc[Nothing] = throw new NoSuchElementException
		override def isEmpty: Boolean = true
		override def add[B >: Nothing](element: B): MyGenericListFunc[B] = new GenericConsFunc(element, GenericEmpty$Func)
		
		def map[B](transformer: Nothing => B): MyGenericListFunc[B] = GenericEmpty$Func
		def filter(predicate: Nothing => Boolean): MyGenericListFunc[Nothing] = GenericEmpty$Func
		def flatMap[B](transformer: Nothing => MyGenericListFunc[B]): MyGenericListFunc[B] = GenericEmpty$Func
		def ++[B >: Nothing](list: MyGenericListFunc[B]): MyGenericListFunc[B] = list
		def printElements: String = ""
	}
	
	case class GenericConsFunc[+A](h: A, t: MyGenericListFunc[A]) extends MyGenericListFunc[A] {
		override def head: A = h
		override def tail: MyGenericListFunc[A] = t
		override def isEmpty: Boolean = false
		override def add[B >: A](element: B): MyGenericListFunc[B] = new GenericConsFunc(element, this)
		
		def filter(predicate: A => Boolean): MyGenericListFunc[A] = {
			if (predicate(h)) new GenericConsFunc(h, t.filter(predicate))
			else t.filter(predicate)
		}
		
		/*
			[1,2,3].map(n *2)
			= new GenericCons(2, [2,3].map(n * 2)
			= new GenericCons(2, new GenericCons(4, [3].map(n * 2)))
			= new GenericCons(2, new GenericCons(4, new GenericCons(6, Empty.map(n * 2))))
			= new GenericCons(2, new GenericCons(4, new GenericCons(6, Empty))))
		*/
		def map[B](transformer: A => B): MyGenericListFunc[B] = {
			new GenericConsFunc(transformer(h), t.map(transformer))
		}
		
		/*
			[1,2] ++ [3,4,5]
			= new GenericCons(1, [2] ++ [3,4,5])
			= new GenericCons(1, new GenericCons(2, Empty ++ [3,4,5]))
			= new GenericCons(1, new GenericCons(2, new GenericCons(3, new GenericCons(4, new GenericCons(5)))))
		*/
		def ++[B >: A](list: MyGenericListFunc[B]): MyGenericListFunc[B] = new GenericConsFunc(h, t ++ list)
		
		/*
			[1,2].flatMap(n => [n, n + 1])
			= [1,2] ++ [2].flatMap(n => [n, n + 1])
			= [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n + 1]
			= [1,2] ++ [2,3] ++ Empty
			= [1,2,2,3]
		 */
		def flatMap[B](transformer: A => MyGenericListFunc[B]): MyGenericListFunc[B] = {
			transformer(h) ++ t.flatMap(transformer)
		}
		
		override def printElements: String =
			if (t.isEmpty) "" + h
			else h + " " + t.printElements
	}
	
	val listInt = new GenericConsFunc(1, new GenericConsFunc(2, new GenericConsFunc(3, GenericEmpty$Func)))
	val cloneListInt = new GenericConsFunc(1, new GenericConsFunc(2, new GenericConsFunc(3, GenericEmpty$Func)))
	val otherListInt = new GenericConsFunc(4, new GenericConsFunc(5, GenericEmpty$Func))
	val listStr = new GenericConsFunc("A", new GenericConsFunc("B", new GenericConsFunc("C", GenericEmpty$Func)))
	
	println(listInt.toString)
	println(listStr.toString)
	
	println(listInt.map(_ * 2).toString)
	
	println(listInt.filter(_ % 2 == 0).toString)
	
	println((listInt ++ otherListInt).toString)
	println(listInt.flatMap(element => new GenericConsFunc[Int](element, new GenericConsFunc[Int](element + 1, GenericEmpty$Func))).toString)
	
	println(cloneListInt == listInt) //without 'case' definition we have to implement a recursive equals function
}
