trait ByteEncoder[A] {
  def encode(a: A): Array[Byte]
}

object ByteEncoder {
  implicit val stringByteEncoder: ByteEncoder[String] = new ByteEncoder[String] {
    override def encode(s: String): Array[Byte] =
      s.getBytes
  }

  def instance[A](f: A => Array[Byte]): ByteEncoder[A] = new ByteEncoder[A] {
    def encode(a: A): Array[Byte] = f(a)
  }
}

implicit object Rot3StringByteEncoder extends ByteEncoder[String] {
  override def encode(s: String): Array[Byte] =
    s.getBytes.map(b => (b+3).toByte)
}

trait ByteDecoder[A] {

  def decode(b : Array[Byte]) : Option[A]
}

object ByteDecoder {

  def instance[A] (f: Array[Bytes] => Option[A]) = new ByteDecoder[A] {
    override def decode(b: Array[Byte]): Option[A] = f(b)
  }
  implicit val stringByteDecoder : ByteDecoder[String] = instance[String](b => String.valueOf(b.map(Char(_))))

  def apply[A] (implicit ev : ByteDecoder[A]) : ByteDecoder[A] = ev
}

val res = ByteDecoder[String].decode(Array(98,105,101,110,32,58,41))
