package dev.insideyou
package zioplayground

// import zio.*

object ourzio {
  final case class ZIO[A](thunk: () => A) {
    def flatMap[B](azb: A => ZIO[B]): ZIO[B] =
      ZIO.succeed {
        val a = thunk()
        val zb = azb(a)
        val b = zb.thunk()
        b
      }

    def map[B](ab: A => B): ZIO[B] =
      ZIO.succeed {
        val a = thunk()
        val b = ab(a)
        b
      }
  }

  object ZIO {
    def succeed[A](a: => A): ZIO[A] =
      ZIO(() => a)
  }

  object console {
    def putStrLn(line: => String) =
      ZIO.succeed(println(line))
    val getStrLn =
      ZIO.succeed(scala.io.StdIn.readLine())
  }

  object Runtime {
    object default {
      def unsafeRunSync[A](zio: => ZIO[A]): A =
        zio.thunk()
    }
  }
}
