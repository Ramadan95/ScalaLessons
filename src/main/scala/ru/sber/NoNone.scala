package ru.sber

import scala.util.Random

object NoNone extends App {
	val randomList =
		List.fill(Random.nextInt(100))(Random.nextInt(1000))
	def merge(as: List[Int], bs: List[Int], acc: List[Int] = Nil): List[Int] = {
		as match {
			case List() => acc.reverse ++ bs
			case a +: restA => bs match {
				case List() => acc.reverse ++ as
				case b +: restB =>
					if (a < b) merge(restA, bs, a :: acc)
					else merge(as, restB, b :: acc)
			}
		}
	}
	val a = List(1,2,3)
	val b = List(4,5,6)

}
