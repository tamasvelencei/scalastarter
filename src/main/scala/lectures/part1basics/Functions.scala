package lectures.part1basics

object Functions extends App{

	def aFunction(a: String, b: Int): String =
		a + " " + b
	
	def aFunction2(a: String, b: Int) = {
		a + " " + b
	}
	
	println(aFunction("hello", 3))
	
	def aParameterLessFunction(): Int = 42
	println(aParameterLessFunction())
	println(aParameterLessFunction)
	
	def aRepeatedFunction(aString: String, n: Int): String = { //Recursive functions need explicit return types
		if (n == 1) aString
		else aString + aRepeatedFunction(aString, n - 1)
	}
	//USE RECURSION INSTEAD OF LOOPS
	println(aRepeatedFunction("hello", 3))
	
	def aFunctionWithSideEffects(aString: String) = println(aString)
	aFunctionWithSideEffects("FuncWithSideEffect")
	
	def aBigFunction(n: Int): Int = {
		def aSmallerFunction(a: Int, b: Int): Int = a + b
		
		aSmallerFunction(n, n - 1)
	}
	println(aBigFunction(5))
	
	//Tasks
	def aGreetingFunction(name: String, age: Int): String = {
		"Hi, my name is " + name + " and I am " + age + " years old."
	}
	println(aGreetingFunction("Bob", 32))
	
	def aFactorialFunction(n: Int): Int = {
		if (n <= 0) 1
		else n * aFactorialFunction(n - 1)
	}
	println(aFactorialFunction(5))
	
	def aFibonacciFunction(n: Int): Int = {
		if (n <= 2) 1
		else aFibonacciFunction(n - 1) + aFibonacciFunction(n - 2)
	}
	// 1 1 2 3 5 8 13 21
	println(aFibonacciFunction(8))
	
	def aPrimeTestFunction(i: Int): Boolean = {
		def test(z: Int): Boolean = {
			if (z <= 1) true
			else i % z != 0 && test(z - 1)
		}
		test(i / 2)
	}
	println(aPrimeTestFunction(13))
}
