package ch2

object ex {
  def factorial(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)
    go(n, 1)
  }

  def fib(n: Int): Int = {
    def go(n: Int, prev: Int, curr: Int): Int = {
      if (n == 0) prev
      else go (n - 1, curr, prev + curr)
    }
    
    go (n, 0, 1)
  }
  
  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    def go (ind: Int): Boolean = {
      if (ind >= (as.length - 1)) true
      else if (gt(as(ind + 1), as(ind))) go (ind + 1)
      else false
    }
    go (0) 
  }
}
