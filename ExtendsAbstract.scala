import scala.collection._

class ExtendsAbstract[A] extends AbstractIterator[A] {

  def hasNext: Boolean = false

  def next(): A = throw new NoSuchElementException()

}
