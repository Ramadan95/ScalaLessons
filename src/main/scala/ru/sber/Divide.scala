package ru.sber

object Divide extends App {

	val a = (3,4)
	val b = (2,4)

	def divide(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)] = {
		if (math.abs(p._2) < math.abs(p._1) || math.abs(q._2) < math.abs(q._1)) Left("Invalid input")
		else if (p._2 == 0 || q._2 == 0 || q._1 == 0) 							Left("Zero divisor")
		else if (math.abs(p._1 * q._2) > math.abs(p._2 * q._1)) 				Left("Improper result")
		else {
			val div = (p._1 * q._2, p._2 * q._1)
			val gcd = BigInt(div._1) gcd div._2
			Right((div._1 / gcd).toInt, (div._2 / gcd).toInt)
		}
	}
	println(divide(a)(b))
}
