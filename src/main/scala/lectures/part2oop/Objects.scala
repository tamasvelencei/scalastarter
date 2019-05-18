package lectures.part2oop

object Objects extends App{
	
	//Scala does not have class-level functions ("static")
	//Objects are Singleton instances!
	object Person {
		// "static/"class" level functionality
		val N_EYES = 2
		def canFly: Boolean = false
		
		//factory method
		def apply(mother: Person, father: Person): Person = new Person("Bobby")
		//in practice, objects usually have factory method like this above
	}
	//in practice we usually define a class with the same name in the same scope to separate Instance level functionality
	//this pattern is COMPANIONS
	class Person(val name: String) {
		// instance level functionality
	}
	
	println(Person.N_EYES)
	println(Person.canFly)
	val mary = Person
	val tom = Person
	println(mary == tom)
	
	val mary2 = new Person("Mary")
	val tom2 = new Person("Tom")
	println(mary2 == tom2)
	
	val bobby = Person(mary2, tom2) //apply method like factory method
	println(bobby.name)
	
	
}
