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
val pattern(answerString) = theUltimateAnswer
```
//> answerString  : String = 42

```scala
val answer = answerString.toInt
```
//> answer  : Int = 42

```scala
println(answer)
```
//> 42

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
}```

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

**Do** loops

```scala
x = 0
do { println(x); x+=1 } while (x <= 10)
```

**Expressions**
"Returns" the final value in a block automatically

```scala
{val x = 10; x + 20}

println({val x = 10; x + 20})
```

**Functions**
  
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