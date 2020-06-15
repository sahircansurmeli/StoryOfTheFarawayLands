object UI extends App {
  
  private val game = new Adventure 
  
  private def run() = {
    val includeDialogue = this.game.welcome()
    if (includeDialogue)
      this.game.wakeUp()
    while (!game.isOver) {
      this.playTurn
    }
  }
  
  private def playTurn() = {
    println()
    if (this.game.player.insideOf.isDefined) 
      println(this.game.player.insideOf.get.fullDescription)
    else
      println(this.game.player.location.fullDescription)
    val command = readLine("Command: ")
    val turnReport = this.game.playTurn(command)
    if (!turnReport.isEmpty) {
      println(turnReport)
    }
  }
  
  this.run()
}