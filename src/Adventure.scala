

class Adventure {
  
  val oldMan = new Human("Old Man", this)
  val witch = new Human("Witch", this)
  val village = Village
  val player = new Player("You", this, Some(Home))
  village.middleRight.addBuilding(Some(new Sorcerer(this.witch, this)))
  village.topMiddle.addBuilding(Some(new Gate(this.player, this)))
  var gameRunning = true
  
  def welcome() = {
    println("Welcome to the Stories of the Faraway Lands")
    println("Type 'help' to see available commands.")
    println("Type 'exit' to quit the game")
    println("Press 'Enter' to start")
    val i = readLine()
    i != "uzatmagec"
  }
  
  def wakeUp() = {
    val wu = new WakeUp(this.player, this.oldMan, this)
    wu.start()
  }
  
  def isOver = !this.gameRunning
  
  def playTurn(cmd: String) = {
    val action = new Action(cmd)
    val outcomeReport = action.execute(this.player)
    outcomeReport.getOrElse("Unknown command: \"" + cmd + "\".")
  }
  
}