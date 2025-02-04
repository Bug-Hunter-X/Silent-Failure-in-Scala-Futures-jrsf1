```scala
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class MyClass {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  def myMethod(i: Int): Future[Int] = Future { 
    if (i == 0) throw new Exception("Zero is not allowed") 
    i * 2 
  }

  def myOtherMethod(i: Int): Future[Unit] = {
    myMethod(i).transform { 
      case Success(value) => Success(println(s"Success: $value"))
      case Failure(exception) => Failure(exception) // Re-throw to be handled by the caller
    }
  }
}

object Main extends App {
  val myClass = new MyClass()
  myClass.myOtherMethod(0).failed.foreach(e => println(s"Failed with: ${e.getMessage}"))
  myClass.myOtherMethod(5).onComplete { case _ => println("Completed") }
}
```