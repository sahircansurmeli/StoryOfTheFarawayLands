
class Action(input: String) {
  
  private val cheatCodes = Vector("banaparaver", "guckuvvet", "karizmatik", "buyucu", "macerayahazirim")
  
  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim
  
  def execute(actor: Player) =  {
    if (this.verb == "exit" || this.verb == "quit") {
      Some(actor.exit())
      
    }
    else if (this.verb == "help") {
      Some(actor.help)
    }
    else if ((actor.location.availableCommands ++ actor.availableCommands).contains(this.verb) || this.cheatCodes.contains(this.verb)) {
      this.verb match {
        case "leave" => Some(actor.leave())
        case "go" => Some(actor.go(this.modifiers))
        case "enter" => Some(actor.enter())
        case "work" => Some(actor.work())
        case "stats" => Some(actor.stats)
        case "sleep" => Some(actor.sleep())
        case "buydrinks" => Some(actor.buyDrinks())
        case "buy" => Some(actor.buy())
        /* Cheat codes */
        case "banaparaver" => Some(actor.banaparaver())
        case "guckuvvet" => Some(actor.guckuvvet())
        case "karizmatik" => Some(actor.karizmatik())
        case "buyucu" => Some(actor.buyucu())
        case "macerayahazirim" => Some(actor.macerayahazirim())
        case other => None
      }
    }
    else {
      None
    }
  }
  
}