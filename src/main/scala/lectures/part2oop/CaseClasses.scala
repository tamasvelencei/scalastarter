package lectures.part2oop

object CaseClasses extends App{
	
	/*
		equals, hashCode, toString needs to reimplement every time, that's why we need case classes
	 */
	
	case class Person(name: String, age: Int)
	
	//1. class params ara fields
	val jim = new Person("Jim", 34)
	println(jim.name)
	
	//2. sensible toString
	println(jim)
	
	//equals and hashCode implemented OutOfTheBox
	val jim2 = new Person("Jim", 34)
	println(jim == jim2)
	
	//4. CCs have handy copy method
	val jim3 = jim.copy(age = 45)
	println(jim3)
	
	//5. CCs have companion objects
	val thePerson = Person
	val mary = Person("Mary", 22)
	
	//6. CCs are serializable
	// Akka -> messages are generally CCs
	
	//7. CCs have extractor patterns = CCs can be used in Pattern Matching
	case object UnitedKingdom {
		def name: String = "The UK of GB and NI"
	}
	
	
}
