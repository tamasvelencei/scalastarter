package lectures.part3fp

object AnonymousFunctions extends App{

	val doubler = (x: Int) => x * 2
	val doubler2: Int => Int = x => x * 2
	println(doubler(2) + doubler2(3))
	
	//multiple parameters
	val adder = (a: Int, b: Int) => a + b
	val adder2: (Int, Int) => Int = (a, b) => a + b
	println(adder(2, 2) + adder2(3, 3))
	
	//no params
	val doSomeThing: () => Int = () => 3
	
	println(doSomeThing) //it prints the function, if it was a method it would be correct
	println(doSomeThing()) //funcCall

	val stringToInt = { (str: String) => str.toInt }

	//MOAR syntactic sugar
	val niceIncrementer: Int => Int = (x: Int) => x + 1
	val niceIncrementer2: Int => Int = _ + 1
	
	val niceAdder: (Int, Int) => Int = (a, b) => a + b
	val niceAdder2: (Int, Int) => Int = _ + _ // it wont work without type definition and each underscore stands for different parameters
}
