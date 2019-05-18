package lectures.part1basics

object Expressions extends App {

	//Instructions (DO) versus Expressions (VALUE)
	//IF Expression
	val aCondition = true
	val aConditionedValue = if(aCondition) 5 else 3
	println(aConditionedValue)
	println(if(aCondition) 5 else 3) //It prints a value, because it gives back a value
										// in Java, If is an Instruction
	 
	var i = 0
	val aWhile = while (i<10) { //while loop is an Expression also
		println(i)
		i += 1
	} //NEVER WRITE THIS AGAIN!!
	
	// Everything in Scala is an Expression
	var aVariable = 1
	val aWeirdValue = (aVariable = 3) //Unit return type like 'void' in Java
	println(aWeirdValue)
	println(aWhile)
	//side effects: println(), while, reassigning <- these returns Unit
	
	//Code blocks
	val aCodeBlock = {
		val y = 2
		val z = y + 1
		if (z > 2) "Hello" else "Bye" //The value of a code block is the last expression in it
	}
	println(aCodeBlock)
	
	//Expression vs Instructions
	// instructions are executed (like java), expressions are evaluated, prefer expressions in scala!
}
