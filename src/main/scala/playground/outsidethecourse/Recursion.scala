package playground.outsidethecourse

object Recursion extends App {
	
	val intArr: Array[Array[Int]] = InitArr()
	
	def sum(arr: Array[Int]): Int = {
		def inner(index: Int, sum: Int): Int = {
			if (index >= arr.length) sum
			else inner(index + 1, arr.apply(index) + sum)
		}
		inner(0, 0)
	}
	
	def outer(arr: Array[Int], index: Int = 0, sum: Int = 0): Int = {
		if (index >= arr.length) sum
		else outer(arr, index + 1, arr.apply(index) + sum)
	}
	outer(intArr.apply(0), 0, 0)
	
	def sumMultiArr(arrays: Array[Array[Int]]): Int = {
		def inner(index: Int, agg: Int): Int = {
			if (index < arrays.length) inner(index + 1, agg + sum(arrays.apply(index)))
			else agg
		}
		inner(0, 0)
	}
	
	println(sum(intArr.apply(0)))
	println(outer(intArr.apply(0), 0, 0))
	println(outer(intArr.apply(0)))
	println(sumMultiArr(intArr))
	
	object InitArr {
		
		def apply(): Array[Array[Int]] = new InitArr().ints
	}
	
	class InitArr {
		
		val ints: Array[Array[Int]] = new Array[Array[Int]](5)
		
		ints.update(0, new Array[Int](5))
		ints.update(1, new Array[Int](5))
		ints.update(2, new Array[Int](5))
		ints.update(3, new Array[Int](5))
		ints.update(4, new Array[Int](5))
		
		ints.apply(0).update(0, 1)
		ints.apply(0).update(1, 2)
		ints.apply(0).update(2, 3)
		ints.apply(0).update(3, 4)
		ints.apply(0).update(4, 5)
		
		ints.apply(1).update(0, 1)
		ints.apply(1).update(1, 2)
		ints.apply(1).update(2, 3)
		ints.apply(1).update(3, 4)
		ints.apply(1).update(4, 5)
		
		ints.apply(2).update(0, 1)
		ints.apply(2).update(1, 2)
		ints.apply(2).update(2, 3)
		ints.apply(2).update(3, 4)
		ints.apply(2).update(4, 5)
		
		ints.apply(3).update(0, 1)
		ints.apply(3).update(1, 2)
		ints.apply(3).update(2, 3)
		ints.apply(3).update(3, 4)
		ints.apply(3).update(4, 5)
		
		ints.apply(4).update(0, 1)
		ints.apply(4).update(1, 2)
		ints.apply(4).update(2, 3)
		ints.apply(4).update(3, 4)
		ints.apply(4).update(4, 5)
	}
	
}
