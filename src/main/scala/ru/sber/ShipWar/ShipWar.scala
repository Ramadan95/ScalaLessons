package ru.sber.ShipWar

import Naval.{Field, Fleet, Game, Ship}

object ShipWar {
	def main(args: Array[String]) {

		val field: Vector[Vector[Boolean]] = Vector(
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false),
			Vector(false, false, false, false, false, false, false, false, false, false))

		val fleet: Map[String, Ship] = Map()

		import scala.io.StdIn

		val valPos : (Field, (Int, Int)) => Vector[Vector[Boolean]] = (field, ship) => field
			.slice(ship._1 - 2, ship._1 + 1)
			.map(x => x slice(ship._2 - 2, ship._2 + 1))
		val bord : (Int, Int) => Boolean = (point1, point2) =>
		{(point1 > 0 && point1 <= 10) && (point2 > 0 && point2 <= 10)}
		def bordEq(list : List[(Int, Int)]): Boolean = list.length == list.count(x => bord(x._1, x._2))
		def partEq : List[Int] => Boolean = list => list.distinct.length == 1
		def parUnEq : List[Int] => Boolean = list =>
			list.zipWithIndex.map(x => x._1 - x._2).distinct.length == 1 ||
			list.zipWithIndex.map(x => x._1 + x._2).distinct.length == 1
		def validateShip(ship: Ship): Boolean = {
			val first = ship.map(x => x._1)
			val second = ship.map(x => x._2)
			if (ship.nonEmpty && ship.length <= 4 &&  bordEq(ship)) {
				if ((partEq(first) && parUnEq(second)) || (partEq(second) && parUnEq(first))) true else false
			} else false
		} // определить, подходит ли корабль по своим характеристикам

		def validatePosition(ship: Ship, field: Field): Boolean = {
			ship match {
				case ::(head, next) =>
					if (valPos(field, head).distinct == Vector(Vector(false, false))
						|| valPos(field, head).distinct == Vector(Vector(false, false, false)))
						validatePosition(next, field)
					else false
				case Nil 			=> true
			}
		} // определить, можно ли его поставить

		def enrichFleet(fleet: Fleet, name: String, ship: Ship): Fleet = {
			fleet + (name -> ship)
		} // добавить корабль во флот
		def markUsedCells(field: Field, ship: Ship): Field = {
			ship match {
				case ::(head, next) =>
					markUsedCells(field.zipWithIndex
						.map(y => y._1.zipWithIndex
						.map(x => if (((x._2 > head._2 - 3 && x._2 < head._2 + 1) || x._1) &&
							(y._2 > head._1 - 3 && y._2 < head._1 + 1)) true
						else false)),
						next)
				case Nil => field
			}
		} // пометить клетки, которые занимает добавляемый корабль

		def tryAddShip(game: Game, name: String, ship: Ship): Game = {
			val res: (Field, Fleet) = if (validateShip(ship) && validatePosition(ship, game._1)) {
				(markUsedCells(game._1, ship), game._2 ++ enrichFleet(game._2, name, ship))
			} else (game._1, game._2)
			res
		} // логика вызовов методов выше

		val ship1 = List((2, 2),(2, 3),(2, 4),(2, 5))
		val ship2 = List((1, 1))
//		println(field
//			.slice(ship1(3)._1 - 2, ship1(3)._1 + 1)
//			.map(x => x slice(ship1(3)._2 - 2, ship1(3)._2 + 1)))
//		if (validateShip(ship1)) println(validatePosition(ship1, field)) else println("Error")
//		println(markUsedCells(field, ship1).mkString("\n"))
		println(tryAddShip((field, fleet), "MillenniumFalcon1", ship1))
	}
}
