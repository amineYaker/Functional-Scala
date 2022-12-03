
package ammonite
package $file.src.main.scala
import _root_.ammonite.interp.api.InterpBridge.{
  value => interp
}
import _root_.ammonite.interp.api.InterpBridge.value.{
  exit,
  scalaVersion
}
import _root_.ammonite.interp.api.IvyConstructor.{
  ArtifactIdExt,
  GroupIdExt
}
import _root_.ammonite.compiler.CompilerExtensions.{
  CompilerInterpAPIExtensions,
  CompilerReplAPIExtensions
}
import _root_.ammonite.runtime.tools.{
  browse,
  grep,
  time,
  tail
}
import _root_.ammonite.compiler.tools.{
  desugar,
  source
}
import _root_.mainargs.{
  arg,
  main
}
import _root_.ammonite.repl.tools.Util.{
  PathRead
}


object Monoid{
/*<script>*/import cats._
import cats.implicits._

case class Speed(metersPerSecond: Double) {
  def kilometersPerSec: Double = metersPerSecond / 1000.0
  def milesPerSec: Double = metersPerSecond / 1609.34
}

object Speed {
  def addSpeeds(s1: Speed, s2: Speed): Speed =
    Speed(s1.metersPerSecond + s2.metersPerSecond)

  implicit val eqSpeed: Eq[Speed] = Eq.fromUniversalEquals
  implicit val monoidSpeed: Monoid[Speed] = Monoid.instance(Speed(0), addSpeeds)
}

/*<amm>*/val res_4 = /*</amm>*/Monoid[Speed].combine(Speed(1000), Speed(2000))
/*<amm>*/val res_5 = /*</amm>*/Monoid[Speed].empty
/*<amm>*/val res_6 = /*</amm>*/Monoid[Speed].combine(Speed(1000), Monoid[Speed].empty)
/*<amm>*/val res_7 = /*</amm>*/Speed(1000) |+| Speed(2000)
/*<amm>*/val res_8 = /*</amm>*/Monoid[Speed].combineAll(List(Speed(100), Speed(200), Speed(300)))
/*<amm>*/val res_9 = /*</amm>*/List(Speed(100), Speed(200), Speed(300)).combineAll
/*<amm>*/val res_10 = /*</amm>*/Monoid[Speed].isEmpty(Speed(100))
/*<amm>*/val res_11 = /*</amm>*/Monoid[Speed].isEmpty(Speed(0))

val sumMonoid: Monoid[Int] = Monoid.instance(0, (x,y) => x + y)
val minMonoid: Monoid[Int] = Monoid.instance(Int.MaxValue, (x, y) => if (x < y ) x else y)
def listMonoid[A]: Monoid[List[A]] = Monoid.instance(Nil, (l1, l2) => (l1 ++ l2).combineAll)
val stringMonoid: Monoid[String] = Monoid.instance("", (x, y) => x + y)

/*<amm>*/val res_16 = /*</amm>*/minMonoid.combine(1,2)/*</script>*/ /*<generated>*/
def $main() = { scala.Iterator[String]() }
  override def toString = "Monoid"
  /*</generated>*/
}
