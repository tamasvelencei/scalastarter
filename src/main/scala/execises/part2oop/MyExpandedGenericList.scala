package execises.part2oop

object MyExpandedGenericList extends App{
	
	abstract class MyGenericList[+A] {
		
		def head: A
		def tail: MyGenericList[A]
		def isEmpty: Boolean
		def add[B >: A](element: B): MyGenericList[B]
		def map[B](transformer: MyTransformer[A, B]): MyGenericList[B]
		def filter(predicate: MyPredicate[A]): MyGenericList[A]
		def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]): MyGenericList[B]
		def ++[B >: A](list: MyGenericList[B]): MyGenericList[B]
		//polymorphic call
		def printElements: String
		override def toString: String = "[" + printElements + "]"
	}
	
	object GenericEmpty extends MyGenericList[Nothing] {
		override def head: Nothing = throw new NoSuchElementException
		override def tail: MyGenericList[Nothing] = throw new NoSuchElementException
		override def isEmpty: Boolean = true
		override def add[B >: Nothing](element: B): MyGenericList[B] = new GenericCons(element, GenericEmpty)
		
		def map[B](transformer: MyTransformer[Nothing, B]): MyGenericList[B] = GenericEmpty
		def filter(predicate: MyPredicate[Nothing]): MyGenericList[Nothing] = GenericEmpty
		def flatMap[B](transformer: MyTransformer[Nothing, MyGenericList[B]]): MyGenericList[B] = GenericEmpty
		def ++[B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list
		def printElements: String = ""
	}
	
	class GenericCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
		override def head: A = h
		override def tail: MyGenericList[A] = t
		override def isEmpty: Boolean = false
		override def add[B >: A](element: B): MyGenericList[B] = new GenericCons(element, this)
		
		def filter(predicate: MyPredicate[A]): MyGenericList[A] = {
			if (predicate.test((h))) new GenericCons(h, t.filter(predicate))
			else t.filter(predicate)
		}
		
		/*
			[1,2,3].map(n *2)
			= new GenericCons(2, [2,3].map(n * 2)
			= new GenericCons(2, new GenericCons(4, [3].map(n * 2)))
			= new GenericCons(2, new GenericCons(4, new GenericCons(6, Empty.map(n * 2))))
			= new GenericCons(2, new GenericCons(4, new GenericCons(6, Empty))))
		*/
		def map[B](transformer: MyTransformer[A, B]): MyGenericList[B] = {
			new GenericCons(transformer.transform(h), t.map(transformer))
		}
		
		/*
			[1,2] ++ [3,4,5]
			= new GenericCons(1, [2] ++ [3,4,5])
			= new GenericCons(1, new GenericCons(2, Empty ++ [3,4,5]))
			= new GenericCons(1, new GenericCons(2, new GenericCons(3, new GenericCons(4, new GenericCons(5)))))
		*/
		def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = new GenericCons(h, t ++ list)
		
		/*
			[1,2].flatMap(n => [n, n + 1])
			= [1,2] ++ [2].flatMap(n => [n, n + 1])
			= [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n + 1]
			= [1,2] ++ [2,3] ++ Empty
			= [1,2,2,3]
		 */
		def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]): MyGenericList[B] = {
			transformer.transform(h) ++ t.flatMap(transformer)
		}
		
		override def printElements: String =
			if (t.isEmpty) "" + h
			else h + " " + t.printElements
	}
	
	trait MyPredicate[-T]{
		def test(element: T): Boolean
	}
	
	trait MyTransformer[-A, B]{
		def transform(element: A): B
	}
	
	
	val listInt = new GenericCons(1, new GenericCons(2, new GenericCons(3, GenericEmpty)))
	val clonedListInt = new GenericCons(1, new GenericCons(2, new GenericCons(3, GenericEmpty)))
	val otherListInt = new GenericCons(4, new GenericCons(5, GenericEmpty))
	val listStr = new GenericCons("A", new GenericCons("B", new GenericCons("C", GenericEmpty)))
	
	println(listInt.toString)
	println(listStr.toString)
	
	println(listInt.map(new MyTransformer[Int, Int] {
		override def transform(element: Int): Int = element * 2
	}).toString)
	
	println(listInt.filter(new MyPredicate[Int] {
		override def test(element: Int): Boolean = element % 2 == 0
	}).toString)
	
	println((listInt ++ otherListInt).toString)
	println(listInt.flatMap(new MyTransformer[Int, MyGenericList[Int]] {
		override def transform(element: Int): MyGenericList[Int] = new GenericCons[Int](element, new GenericCons[Int](element + 1, GenericEmpty))
	}).toString)
	
	println(listInt == clonedListInt) //without 'case' definition we need to implement a recursive equals func
}
