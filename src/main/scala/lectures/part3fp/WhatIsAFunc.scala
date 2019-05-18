package lectures.part3fp

object WhatIsAFunc extends App{
	
	//Dream: use func as first class elements
	//problem: oop

	val doubler = new MyFunction[Int, Int] {
		override def apply(element: Int): Int = element * 2
	}
	println(doubler(2)) //looks like a function
	

	//function types = Function[A, B]
	val stringToIntConverter = new Function[String, Int] {
		override def apply(v1: String): Int = v1.toInt
	}
	println(stringToIntConverter("3") + 4)
	
	val adder = new Function2[Int, Int, Int] {
		override def apply(v1: Int, v2: Int): Int = v1 + v2
	}
	//syntactic sugar
	val adder2: ((Int, Int) => Int) = (v1, v2) => v1 + v2
	println(adder(2,2) + adder2(3,3))
	
	// Function types Function[A, B, R] ===(A,B) => R
	
	//ALL SCALA FUNCTIONS ARE OBJECTS
	
	val concatenate: (String, String) => String = (v1, v2) => v1 + v2
	println(concatenate("Hello ", "World!"))
	
	// type of Function1[Int, Function1[Int, Int]
	val function1: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
		override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
			override def apply(v2: Int): Int = v1 * v2
		}
	}
	println(s"Func in func: ${function1(2)(3)}") //its called 'curried function'
	
	val function1nicer: Int => ((Int) => Int) = v1 => (v2) => v1 * v2
	println(s"Nicer Func in func: ${function1nicer(2)(3)}")
	
	val function1nicer2 = (v1: Int) => (v2: Int) => v1 * v2
	println(s"Nicer2 Func in func: ${function1nicer2(2)(3)}")
	
	val function1nicer3: Int => ((Int) => Int) = _1 => _2 => _1 * _2
	println(s"Nicer3 Func in func: ${function1nicer3(2)(3)}")
}

//in oop when we use a method, we need to instantiate or use it anonymously
class Action {
	def execute(element: Int): String = ???
}

trait MyFunction[A, B] {
	def apply(element: A): B
}