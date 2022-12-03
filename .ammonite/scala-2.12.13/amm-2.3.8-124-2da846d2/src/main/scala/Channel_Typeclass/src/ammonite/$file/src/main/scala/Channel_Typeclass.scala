
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


object Channel_Typeclass{
/*<script>*/import java.io.FileOutputStream
import java.nio.ByteBuffer
import scala.util.Using

trait ByteEncoder[A] {
  def encode(a: A): Array[Byte]
}

trait Channel {
  def write[A](obj: A, enc: ByteEncoder[A]): Unit
}

object FileChannel extends Channel {
  override def write[A](obj: A, enc: ByteEncoder[A]): Unit = {
    val bytes: Array[Byte] = enc.encode(obj)

    Using(new FileOutputStream("fp-course/test")) { os =>
      os.write(bytes)
      os.flush()
    }
  }
}

object IntByteEncoder extends ByteEncoder[Int] {
  override def encode(n: Int): Array[Byte] = {
    val bb = ByteBuffer.allocate(4)
    bb.putInt(n)
    bb.array()
  }
}

//FileChannel.write[Int](5, IntByteEncoder)

object StringEncoder extends ByteEncoder[String] {
  override def encode(a: String): Array[Byte] = a.getBytes()
}/*</script>*/ /*<generated>*/
def $main() = { scala.Iterator[String]() }
  override def toString = "Channel_Typeclass"
  /*</generated>*/
}
