```scala
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class MyClass {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  def myMethod(i: Int): Future[Int] = Future { 
    if (i == 0) throw new Exception("Zero is not allowed") 
    i * 2 
  }

  def myOtherMethod(i: Int): Unit = {
    myMethod(i).onComplete { 
      case Success(value) => println(s"Success: $value") 
      case Failure(exception) => println(s"Failure: ${exception.getMessage}")
    }
  }
}

object Main extends App {
  val myClass = new MyClass()
  myClass.myOtherMethod(0) //this will fail but the program will not terminate
  myClass.myOtherMethod(5) //this will succeed
}
```