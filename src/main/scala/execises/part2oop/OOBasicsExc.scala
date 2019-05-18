package execises.part2oop

object OOBasicsExc extends App{

	val writer = new Writer()
	val novel = new Novel("Scanner Darkly", 2005, writer)
	println(writer.fullName())
	println(novel.authorAge())
	println(novel.isWrittenBy(writer))
	println(novel.isWrittenBy(new Writer("Robert", "Redford", 1957)))
	val newRelease = novel.copy(2010)
	println(newRelease.yearOfRelease)
	println(novel.equals(newRelease))
	
	val counter: Counter = new Counter(1)
	println(counter.increment().value == 2)
	println(counter.increment(4).value == 5)
	println(counter.decrement().value == 0)
	println(counter.decrement(2).value == -1)
	println(counter.currentValue() == 1)
	
	println(counter.incrementNTimes(5).currentValue())
	println(counter.decrementNTimes(5).currentValue())
}

class Writer(val firstName: String = "Philip", val surname: String = "K. Dick", val year: Int = 1963){
	def fullName(): String = s"$firstName $surname"
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer){
	
	def authorAge(): Int = yearOfRelease - author.year
	def isWrittenBy(writer: Writer): Boolean = this.author.equals(writer)
	def copy(newYearOfRelease: Int): Novel = new Novel(this.name, newYearOfRelease, this.author)
}

class Counter(val value: Int){
	
	def currentValue(): Int = this.value
	
	def increment(): Counter = {
		println("incrementing")
		new Counter(this.value + 1)
	}
	
	def increment(amount: Int): Counter = new Counter(this.value + amount)
	
	def incrementNTimes(amount: Int): Counter = {
		if (amount <= 0) this
		else increment().increment(amount - 1)
	}
	
	def decrement(): Counter = {
		println("decrementing")
		new Counter(this.value - 1)
	}
	
	def decrement(amount: Int): Counter = new Counter(this.value - amount)
	
	def decrementNTimes(amount: Int): Counter = {
		if (amount <= 0) this
		else decrement().decrement(amount - 1)
	}
}
