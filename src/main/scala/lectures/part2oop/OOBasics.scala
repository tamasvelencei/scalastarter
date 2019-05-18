package lectures.part2oop

object OOBasics extends App{
	
	val person = new Person("John", 26)

//	println(person.name) // Wont work because class parameters are NOT FIELDS
	println(person.age)
	person.greet("Daniel")
}

class Person(name: String, val age: Int){ //constructor, in this case age is a field parameter and name is a class parameter
	
	//body
	val x = 2
	
	println("it runs when Person created!")
	
	//method
	def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
	
	//overloading
	def greet(): Unit = println(s"Hi, i am $name")
	
	//multiple constructors
	def this(name: String) = this(name, 0)
	def this() = this("John Doe")
	
	

}