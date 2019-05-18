package lectures.part2oop

object Inheritance extends App{
	
	// single class inheritance lika java
	sealed class Animal {
		val creatureType = "wild"
		val legs = 0
		def eat = println("nomnom")
	}
	
	class Cat extends Animal {
		def crunch = {
			eat
			println("crunch crunch")
		}
	}
	
	val cat = new Cat
	cat.crunch
	
	//constructors
	class Person(name: String, age: Int)
	class Adult(name:String, age: Int, idCard: String) extends Person(name, age)
	
	//overriding
	class Dog(override val creatureType: String, legsNumber: Int = 0) extends Animal {
		//override val creatureType = "domestic"
		override val legs: Int = legsNumber
		override def eat: Unit = println("crunch crunch")
	}
	
	val dog = new Dog("K9")
	dog.eat
	println(dog.creatureType)
	
	// type substitution
	val unknownAnimal: Animal = new Dog("K9")
	unknownAnimal.eat
	
	//preventing overrides like in Java with final keyword
	// or use the 'sealed' keyword in Scala. 'sealed' protects from overriding only from another file
}
