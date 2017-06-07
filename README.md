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
´´´

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