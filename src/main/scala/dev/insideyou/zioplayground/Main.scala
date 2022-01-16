package dev.insideyou
package zioplayground

import ourzio.*

object Main extends scala.App {
  val trace = s"[${Console.BLUE}TRACE${Console.RESET}]"
  val program =
    for {
       _ <- console.putStrLn("─" * 11)
       _ <- console.putStrLn("name?")
       name <- ZIO.succeed("Felix")
       _ <- console.putStrLn(s"Hello, $name")
       _ <- console.putStrLn("─" * 11)
    } yield ()

  Runtime.default.unsafeRunSync(program)
}
