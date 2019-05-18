package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App{
	
	def factorial(n: Int): Int = {
		if (n <= 1) 1
		else {
			println("Computing factorial of " + n + " - I first need factorial of " + (n -1))
			val result = n * factorial(n - 1)
			println("Computed factorial of " + n)
			result
		}
	}
	println(factorial(10))
//	println(factorial(5000)) // StackOverFlow Error
	
	def smartFactorial(n: Int): BigInt = { //TAIL RECURSIVE function does not throw stackoverflow error
		@tailrec //this controller annotation helps to check if the function is tail recursive and throws error if it is not
		def factHelper(x: Int, accumulator: BigInt): BigInt =
			if (x <= 1) accumulator
			else factHelper(x - 1, x * accumulator) //TAIL RECURSION = use recursive call as the LAST expression
		
		factHelper(n, 1)
	}
	println(smartFactorial(20000))
	//USE TAIL RECURSIVE FUNCTIONS INSTEAD OF LOOPS
	
	/*
		smartFactorial(10) = factHelper(10, 1)
		= factHelper(9, 10 * 1)
		= factHelper(8, 9 * 10 * 1)
		= factHelper(7, 8 * 9 * 10 * 1)
		= ...
		= factHelper(1, 2 * 3 * 4 * ... * 10)
		= 1 * 2 * 3 * ... * 10
	 */
	
	//Tasks:
	def wrapperConFunc(n: Int, t: String): String = {
		@tailrec
		def concatStrings(n: Int, accumulator: String): String = {
			if (n > 1) concatStrings(n - 1, t + accumulator)
			else accumulator
		}
		concatStrings(n, t)
	}
	println(wrapperConFunc(200, "a"))
	
	def wrapperPrimeTest(n: Int): Boolean = {
		@tailrec
		def test(i: Int, isStillPrime: Boolean): Boolean =  {
			if (!isStillPrime) false
			else if (i <= 1) true
			else test(i - 1, n % i != 0 && isStillPrime)
		}
		test(n / 2, true)
	}
	println(wrapperPrimeTest(9004591))
	
	def wrapperPrimeTestInt(n: Int): Int ={
		@tailrec
		def test(i: Int): Int = {
			if (i == 1 || (n % i == 0)) i
			else test(i - 1)
		}
		test(n / 2)
	}
	println(wrapperPrimeTestInt(9004591))
	println(9004591 % wrapperPrimeTestInt(9004591))
	
	def fibonacciTailRec(n: Int): Int = {
		@tailrec
		def test(i: Int, last: Int, nextToLast: Int): Int = {
			if (i >= n) last
			else test(i + 1, last + nextToLast, last)
		}
		if (n <= 2) 1
		else test(2, 1, 1)
	}
	println(fibonacciTailRec(5))
	
	
	
	
	
}
