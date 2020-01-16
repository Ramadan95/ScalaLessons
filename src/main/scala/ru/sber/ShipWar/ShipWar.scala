package ru.sber.ShipWar

import Naval.{Field, Fleet, Game, Ship}

object ShipWar {
	def main(args: Array[String]) {

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
		def validatePosition(ship: Ship, field: Field): Boolean = ??? // определить, можно ли его поставить
		def enrichFleet(fleet: Fleet, name: String, ship: Ship): Fleet = ??? // добавить корабль во флот
		def markUsedCells(field: Field, ship: Ship): Field = ??? // пометить клетки, которые занимает добавляемый корабль

		def tryAddShip(game: Game, name: String, ship: Ship): Game = ??? // логика вызовов методов выше
		val lst = List((6,4),(7,4),(8,4),(9,4),(10,4))
		println(validateShip(lst))
	}
}
