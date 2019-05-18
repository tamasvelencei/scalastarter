package lectures.part2oop

object Generics extends App{
	
	class MyList[+A]{
		def add[B >: A](element: B): MyList[B] = ???
	}
	

	// Variance problem
	class Animal
	class Cat extends Animal
	class Dog extends Animal
	
	//List[Cat] extends List[Animal] = COVARIANCE
	class CovariantList[+A]
	val animal: Animal = new Cat
	val animalList: CovariantList[Animal] = new CovariantList[Cat]
	
	//Invariance
	class InvariantList[A]
	val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
	
	//ContraVariance
	class ContraVariantList[-A]
	val contraVariantAnimalList: ContraVariantList[Cat] = new ContraVariantList[Animal]
	
	//bounded types
	class Cage[A <: Animal](animal: A) //It accepts typ which is subtype of Animal
	val cage = new Cage(new Dog)
	
	class Car
//	val newCage = new Cage(new Car) //it will cause a runtime error
}

