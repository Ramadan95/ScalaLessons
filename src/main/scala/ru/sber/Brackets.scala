package ru.sber

object Brackets {
  def main(args:Array[String]): Unit = {
	  val str = "scala +  ]  [Stepik] = love"

	  def indexOf(s: String, pattern: String): Option[Int] = {
		  Option(s.indexOf(pattern)).filter(_ >= 0)
	  }

	  def brackets(s: String): Option[(Int, Int)] = indexOf(s, "[").flatMap( opening =>
		  indexOf(s.substring(s.indexOf("[") + 1), "]")
				.filter(_ + opening > opening)
			  	.map(closing => (opening, closing + opening + 1))
	  )

	  def cutBrackets(s: String): Option[String] = {
		  brackets(s).map{
			  case (_@a, _@b) => s.substring(a + 1, b)
		  }
	  }

	  println(cutBrackets(str))

  }
}
