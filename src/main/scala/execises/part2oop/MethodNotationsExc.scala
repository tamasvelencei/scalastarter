package execises.part2oop

object MethodNotationsExc extends App{

	class Person(val name: String, val age: Int = 0, val favouriteMovie: String = ""){
		
		def +(title: String): Person = new Person(s"$name ($title)", age, favouriteMovie)
		def unary_+ : Person = new Person(name, age + 1, favouriteMovie)
		def learns(subject: String): String = s"$name learns $subject."
		def learnScala: String = this learns "Scala"
		def apply(number: Int): String = s"$name watched $favouriteMovie $number times."
	}
	
	val mary = new Person("Mary", favouriteMovie = "Inception")
	
	println(mary + "the rockstar" equals mary)
	println(mary + "the rockstar" name)
	println((+mary).age)
	println(mary learnScala)
	println(mary(2))
}
