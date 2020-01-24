package ru.sber

import scala.io.StdIn


object Collections extends {
	def main(args: Array[String]) {
		println(Stream.continually(StdIn.readLine)
			.takeWhile(_ != "END")
			.zipWithIndex
			.collect{case (x, i) if (i + 1) % 2 == 0 => x.toInt * 2}.sum)
	}
}
