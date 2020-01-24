package ru.sber

import scala.io.Source._

object Reader {
	def main(args: Array[String]): Unit = {
		val fileName = "C:\\Users\\OUT-Kagermanov-RM\\Desktop\\Testik\\src\\main\\it.txt"
		val list = fromFile(fileName).getLines.toList.dropWhile(x => x.equals(""))

		println(list)
	}
}
