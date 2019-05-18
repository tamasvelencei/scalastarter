package lectures.part2oop

object MethodNotations extends App{

	class Person(val name: String, favoriteMovie: String){
		
		def likes(movie: String): Boolean = movie == favoriteMovie
		def +(person: Person): String = s"${this.name} is hanging with ${person.name}"
		def unary_! : String = "What da hack?"
		def isAlive: Boolean = true
		def apply(): String = s"Hi, my name is $name and I like $favoriteMovie."
	}
	
	//types of 'syntactic sugars'
	
	val mary = new Person("Mary", "Inception")
	println(mary.likes("Inception"))
	println(mary likes "Inception" )
	//infix notation = operator notation (only works with single parameter method)
	
	val tom = new Person("Tom", "Fight Club")
	println(mary + tom)
	println(mary.+(tom))
	println(1.+(2)) //all operators are methods
	
	//prefix notation
	val x = -1 //equivalent with 1.unary_-
	val y = 1.unary_-
	//unary_ prefix only works with - + ~ !
	
	println(!mary)
	println(mary.unary_!)
	
	//postfix notation
	println(mary.isAlive)
	println(mary isAlive) //only available with methods without parameters
	//apply
	println(mary.apply())
	println(mary()) //equivalent
}
