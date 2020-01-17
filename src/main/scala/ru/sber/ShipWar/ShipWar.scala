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

		val valPos : (Field, (Int, Int)) => Vector[Vector[Boolean]] = (field, ship) => field
			.slice(ship._1 - 2, ship._1 + 1)
			.map(x => x slice(ship._2 - 2, ship._2 + 1))
			.distinct
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
			if (bordEq(ship)) {
				if ((partEq(first) && parUnEq(second)) || (partEq(second) && parUnEq(first))) true else false
			} else false
		} // определить, подходит ли корабль по своим характеристикам

		def validatePosition(ship: Ship, field: Field): Boolean = {
			ship match {
				case ::(head, next) =>
					if (valPos(field, head) == Vector(Vector(false, false))
						|| valPos(field, head) == Vector(Vector(false, false, false)))
						validatePosition(next, field)
					else false
				case Nil 			=> true
			}
		} // определить, можно ли его поставить
		def enrichFleet(fleet: Fleet, name: String, ship: Ship): Fleet = ??? // добавить корабль во флот
		def markUsedCells(field: Field, ship: Ship): Field = ??? // пометить клетки, которые занимает добавляемый корабль

		def tryAddShip(game: Game, name: String, ship: Ship): Game = ??? // логика вызовов методов выше

		val ship = List((2, 2),(2, 3),(2, 4),(1, 3))
//		println(field
//			.slice(ship(3)._1 - 2, ship(3)._1 + 1)
//			.map(x => x slice(ship(3)._2 - 2, ship(3)._2 + 1)))
		println(validatePosition(ship, field))
	}
}
