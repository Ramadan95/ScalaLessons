def isPrime(x : Long): Boolean =
	Stream.from(1).takeWhile(p => p * p <= x).forall(x % _ != 0)
isPrime(7)