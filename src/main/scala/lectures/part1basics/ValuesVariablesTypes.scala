package lectures.part1basics

object ValuesVariablesTypes extends App{

    // values
    val x: Int = 42 //val-s are immutable
//    x = 43
    val y      = 43 //compiler can infer types
    println(x, y)

    val aString: String = "Hello"
    val aBoolean: Boolean = false
    val aChar: Char = 'a'
    val aShort: Short = 2323
    val aLong: Long = 999999999999999999L //without L at the end, the compiler takes it as Int
    val aFloat: Float = 2.0f //It needs the f at the end, without it, compiler takes it Double
    val aDouble: Double = 3.0

    //variables

    var aVariable = 5 //the compiler will use :Int implicitly
    aVariable = 4
//    aVariable = "sffef" //can not assign to Int
    //Tips: prefer vals over var
}
