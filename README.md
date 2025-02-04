# Silent Failure in Scala Futures

This example showcases an uncommon yet crucial error in Scala: silent failures when using Futures. The application continues running even after an exception occurs within a Future if not handled properly.

The `Bug.scala` file contains code that throws an exception within a Future.  The exception is caught, but the program doesn't halt or signal the failure prominently.

The `BugSolution.scala` demonstrates a better approach to handle such situations to prevent silent failures.