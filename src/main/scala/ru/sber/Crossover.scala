package ru.sber

object Crossover {
	def main(args: Array[String]) {
		val points: List[Int] = Lesson.points
		val chr1: List[Char] = Lesson.chr1
		val chr2: List[Char] = Lesson.chr2
		println(points)
		println(chr1)
		println(chr2)
		println("---------------------------------")

		def crossover(points:List[Int], chr1:List[Char], chr2:List[Char], chChr1:Char, chChr2:Char): (List[Char], List[Char]) =
			points match {
				case head :: next =>
									chr1.zipWithIndex.collect{
					case (x, i) if i > head => if (x == chChr1) chChr2 else chChr1};
									chr2.zipWithIndex collect {
					case (x, i) if i > head => if (x == chChr1) chChr2 else chChr1};
									crossover(next, chr1, chr2, chChr1, chChr2)
				case Nil => (chr1, chr2)
		}

		val (a,  b) = crossover(points, chr1, chr2, chr1(0), chr2(0))
		println(points)
		println(a)
		println(b)
	}
}

object Lesson {
	val points	= List(2, 4, 5)
	val chr1		= List('x', 'x', 'x', 'x', 'x', 'x', 'x')
	val chr2		= List('y', 'y', 'y', 'y', 'y', 'y', 'y')
}
