package lectures.part1basics

object DefaultArgs extends App{
	
	def trFact(n: Int, acc: Int = 1): Int = { //The second parameters get default value
		if (n <= 1) acc
		else trFact(n-1, n * acc)
	}
	
	val fact10 = trFact(10)
	val fact11 = trFact(10, 2)
	
	def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit =
		println("Saving picture in " + format + " width: " + width + " height: " + height)
	savePicture("bmp")
	savePicture(width = 800)
	savePicture(height = 1300, width = 1600)
	savePicture("", 100) // width will be 100
}
