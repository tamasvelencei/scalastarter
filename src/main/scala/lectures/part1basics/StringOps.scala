package lectures.part1basics

object StringOps extends App{
	
	val str: String = "Hello, I am learning Scala"
	
	//common with Java
	println(str.charAt(2))
	println(str.substring(7, 11))
	println(str.split(" ").toList)
	println(str.startsWith("Hello"))
	println(str.replace(" ", "-"))
	println(str.toLowerCase())
	println(str.length)
	
	val aNumberString = "2"
	val aNumber = aNumberString.toInt
	println('a' +: aNumberString :+ 'z') //scala specific operator
	println('a' + aNumberString + 'z') //same
	println(str.reverse)
	println(str.take(2))
	
	//scala specific
	// S-interpolation
	val name = "David"
	val age = 12
	val greeting = s"Hello, my name is $name and i am $age years old."
	val anotherGreeting = s"Hello, my name is $name and i will be turning ${age+1} years old."
	println(anotherGreeting)

	// F-interpolator
	val speed = 1.2f
	val myth = f"$name can eat $speed%1.2f burgers per minute." //%1.2f means 2 characters total, minimum with 2 decimal precision
	println(myth)
	
	// raw-interpolator
	println(raw"This is a \n newline")
	val escaped = "This is a \n newline"
	println(raw"$escaped")
}
