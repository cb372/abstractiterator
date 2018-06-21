import scala.collection._

class ExtendsTrait[A] extends Iterator[A] {

  def hasNext: Boolean = false

  def next(): A = throw new NoSuchElementException()

}
