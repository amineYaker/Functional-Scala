import cats._
import cats.implicits._

sealed trait Validated[+A]

object Validated {
  case class Valid[+A](a: A) extends Validated[A]
  case class Invalid(errors: List[String]) extends Validated[Nothing]

  implicit val applicative: Applicative[Validated] = new Applicative[Validated] {
    override def pure[A](x: A): Validated[A] = new Valid(x)

    override def ap[A, B](vf: Validated[A => B])(va: Validated[A]): Validated[B] =
      //(vf,va) match {
        //case (Valid(f), Valid(a)) => Valid(f(a))
        //case (Invalid(e1), Valid(a)) => Invalid(e1)
        //case (Valid(f), Invalid(e2)) => Invalid(e2)
        //case (Invalid(e1), Invalid(e2)) => Invalid(e1 ++ e2)
      //}
      map2(vf, va) ((f, a) => f(a))
    override def map2[A, B, Z](fa: Validated[A], fb: Validated[B])(f: (A, B) => Z): Validated[Z] = 
      //(fa, fb) match {
        //case (Valid(a), Valid(b)) => Valid(f(a,b))
        //case (Invalid(e1), Invalid(e2)) => Invalid(e1 ++ e2)
        //case (Invalid(e1), Valid(b)) => Invalid(e1)
        //case (Valid(a), Invalid(e2)) => Invalid(e2)
      //}
      ap(ap(pure(f.curried))((fa)))( fb)
      def tupled[A,B] (va : Validated[A], vb : Validated[B]) : Validated[(A,B)] =
        map2(va,vb) ( (a,b) => (a,b))

  }
  

}
val listApplicative : Applicative[List] = new Applicative[List] {
  def pure[A](x: A): List[A] = List(x)
  def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] = 
    (ff, fa) match {
      case ((hdff :: tlff) , (hdfa:: tlfa)) => (hdfa :: tlfa).fmap(hdff) ++ ap(tlff)(hdfa :: tlfa)
      case (_) => Nil
    }
}
