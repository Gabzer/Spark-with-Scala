#Spark-with-Scala course
=============================

**VALUES** are immutable constants. You can't change them once defined.

```scala
val hello: String = "Hola!"
```

Notice how Scala defines things backwards from other languages - you declare the
name, then the type.

**VARIABLES** are mutable

```scala
var helloThere: String = hello
helloThere = hello + " There!"
```

Some other **types**

```scala
val numberOne : Int = 1
val truth : Boolean = true
val letterA : Char = 'a'
val pi : Double = 3.14159265
val piSinglePrecision : Float = 3.14159265f
val bigNumber : Long = 1234567890l
val smallNumber : Byte = 127
```

**Concatenating** stuff with +:

```scala
println("Here is a mess: " + numberOne + truth + letterA + pi + bigNumber)
```

printf **style**:

```scala
println(f"Pi is about $piSinglePrecision%.3f")
```

*//> Pi is about 3,142*

```scala
println(f"Zero padding on the left: $numberOne%05d")
```

*//> Zero padding on the left: 00001*
											  
**Substituting** in variables:

```scala
println(s"I can use the s prefix to use variables like $numberOne $truth $letterA")
```

*//> I can use the s prefix to use variables like 1 true a*

**Substituting expressions** (with curly brackets):

```scala
println(s"The s prefix isn't limited to variables; I can include any expression. Like ${1+2}")
```

*//> The s prefix isn't limited to variables; I can include any expression. Like 3*
											 
Using **regular expressions**:

```scala
val theUltimateAnswer: String = "To life, the universe, and everything is 42."
val pattern = """.* ([\d]+).*""".r
val pattern(answerString) = theUltimateAnswer				//> answerString  : String = 42
val answer = answerString.toInt		                        //> answer  : Int = 42
println(answer)               								//> 42
```

**If / else** syntax

```scala
if (1 > 3) println("Impossible!") else println("The world makes sense.")

if (1 > 3) {
	println("Impossible!")
} else {
	println("The world makes sense.")
}
```

**Matching** - like switch in other languages:

```scala
val number = 3
number match {
	case 1 => println("One")
	case 2 => println("Two")
	case 3 => println("Three")
	case _ => println("Something else")
}
```

#Loops
========
**For** loops

```scala
for (x <- 1 to 4) {
	val squared = x * x
	println(squared)
}
```

**While** loops

```scala
var x = 10
while (x >= 0) {
	println(x)
	x -= 1
}
```

**Do** loops

```scala
x = 0
do { println(x); x+=1 } while (x <= 10)
```

#Expressions
=================

"Returns" the final value in a block automatically

```scala
{val x = 10; x + 20}

println({val x = 10; x + 20})
```

#Functions
=================
  
Format is def <function name>(parameter name: type...) : return type = { expression }

Don't forget the = before the expression!

```scala
def squareIt(x: Int) : Int = {
	x * x
}
def cubeIt(x: Int): Int = {x * x * x}
```

Functions can take other **functions as parameters**

```scala
def transformInt(x: Int, f: Int => Int) : Int = {
	f(x)
}
val result = transformInt(2, cubeIt)
```

**"Lambda functions", "anonymous functions", "function literals"**

You can declare functions inline without even giving them a name
This happens a lot in Spark.

```scala
transformInt(3, x => x * x * x)

transformInt(10, x => x / 2)

transformInt(2, x => {val y = x * 2; y * y})
```

#Data structures
=================  
**Tuples** (Also really common with Spark!!)

Immutable lists

Often thought of as database fields, or columns.

Useful for passing around entire rows of data.

```scala
val captainStuff = ("Picard", "Enterprise-D", "NCC-1701-D")
```

You refer to individual fields with their ONE-BASED index:

```scala
println(captainStuff._1)                        //> Picard
println(captainStuff._2)                        //> Enterprise-D
println(captainStuff._3)                        //> NCC-1701-D
```

You can create a key/value pair with ->

```scala
val picardsShip = "Picard" -> "Enterprise-D"     //> picardsShip  : (String, String) = (Picard,Enterprise-D)
println(picardsShip._2)                          //> Enterprise-D
```

You can mix different types in a tuple

```scala
val aBunchOfStuff = ("Kirk", 1964, true)
```

#Lists
==============
Like a tuple, but it's an actual Collection object that has more functionality.

Also, it cannot hold items of different types.

It's a singly-linked list under the hood.

```scala
val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine")
```

Access individual members using () with ZERO-BASED index (confused yet?)

```scala
println(shipList(1))                             //> Defiant
```

head and tail give you the first item, and the remaining ones.

```scala
println(shipList.head)                           //> Enterprise
println(shipList.tail) 				//> List(Defiant, Voyager, Deep Space Nine)
```

Iterating though a list

```scala
for (ship <- shipList) {println(ship)}        //> Enterprise
							  //| Defiant
							  //| Voyager
							  //| Deep Space Nine
```

Let's apply a function literal to a list! **map()** can be used to apply any function to every item in a collection.

```scala
val backwardShips = shipList.map( (ship: String) => {ship.reverse})
for (ship <- backwardShips) {println(ship)}      //> esirpretnE
							  //| tnaifeD
							  //| regayoV
							  //| eniN ecapS peeD
```

**reduce()** can be used to combine together all the items in a collection using some function.

```scala
val numberList = List(1, 2, 3, 4, 5)              //> numberList  : List[Int] = List(1, 2, 3, 4, 5)
val sum = numberList.reduce( (x: Int, y: Int) => x + y)
							  //> sum  : Int = 15
println(sum)                                      //> 15
```

**filter()** can remove stuff you don't want. Here we'll introduce wildcard syntax while we're at it.

```scala
val iHateFives = numberList.filter( (x: Int) => x != 5)
							  //> iHateFives  : List[Int] = List(1, 2, 3, 4)
val iHateThrees = numberList.filter(_ != 3)       //> iHateThrees  : List[Int] = List(1, 2, 4, 5)
```

Note that Spark has its own map, reduce, and filter functions that can distribute these operations. But they work the same way!
Also, you understand MapReduce now :)

**Concatenating** lists

```scala
val moreNumbers = List(6, 7, 8)                   //> moreNumbers  : List[Int] = List(6, 7, 8)
val lotsOfNumbers = numberList ++ moreNumbers     //> lotsOfNumbers  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
```

More list fun

```scala
val reversed = numberList.reverse                 //> reversed  : List[Int] = List(5, 4, 3, 2, 1)
val sorted = reversed.sorted                      //> sorted  : List[Int] = List(1, 2, 3, 4, 5)
val lotsOfDuplicates = numberList ++ numberList   //> lotsOfDuplicates  : List[Int] = List(1,2,3,4,5,1,2,3,4,5)
val distinctValues = lotsOfDuplicates.distinct    //> distinctValues  : List[Int] = List(1, 2, 3, 4, 5)
val maxValue = numberList.max                     //> maxValue  : Int = 5
val total = numberList.sum                        //> total  : Int = 15
val hasThree = iHateThrees.contains(3)            //> hasThree  : Boolean = false
```
