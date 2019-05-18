package lectures.part2oop

object AbstractDataTypes extends App{
	
	//abstract
	abstract class Animal {
		val creatureType: String
		def eat: Unit
	}
	
	class Dog extends Animal{
		val creatureType: String = "Canine"
		def eat: Unit = println("crunch crunch")
	}
	
	trait Carnivore {
		def eat(animal: Animal)
	}
	
	trait ColdBlooded
	
	class Crocodile extends Animal with Carnivore with ColdBlooded {
		override val creatureType: String = "croc"
		def eat: Unit = println("nomnom")
		def eat(animal: Animal): Unit = println(s"I'am a croc and eating a ${animal.creatureType}")
	}
	
	val dog = new Dog
	val croc = new Crocodile
	croc.eat(dog)
	
	// traits vs abstracts, both can have abstract or defined fields or methods
	// traits don't have constructor
	// inheritance rules like in Interface in Java
	// traits = behavior, abstract class = type of thing
	
	
	
	
}
