import scala.collection.mutable.ListBuffer

class Building(val name: String, val description: String) extends Place {
  
  def enter() = {
    (false, "")
  }
  
  var availableCommands = Vector("leave")
  
  def nameWithArticle(a: String) = 
    if (name == "Home")
    "your " + this.name
    else if (name == "Gate of the village")
      "the " + this.name
    else a + " " + this.name
  
  def fullDescription = "You are in " + this.nameWithArticle("the") + ".\n" + this.description + "\n" + this.enter()._2
  
}

object Home extends Building("Home", "The old man has left this house for you. You can sleep here.") {
  this.availableCommands = this.availableCommands :+ "sleep"
}

object WeaponShop extends Building("Weapon Shop", "You can buy a sword here.") {
  this.availableCommands = this.availableCommands :+ "buy"
  var swordAvailable = true
  def swordText = if (this.swordAvailable) (this.description + "\nThere is a sword available:\nStrength +10\nCost: 150 Coins\n") else "You already have a sword"
  override def fullDescription = "You are in " + this.nameWithArticle("the") + ".\n" + swordText
}

object Tavern extends Building("Tavern", "A place to socialize with the people of the village.") {
  this.availableCommands = this.availableCommands :+ "buydrinks"
}

class Sorcerer(private val witch: Human, private val adv: Adventure) extends Building("Witch's Home", "The mysterious witch of the village lives here.") {
  override def enter() = {
    if (this.adv.player.getCharisma < 10) {
      (false, "\nWitch: I have a secret, but I can't tell it to strangers.\n")
    }
    else if(!this.adv.player.hasTalkedWithWitch) {
      val dialogue = new ListBuffer[String]
      dialogue += ("Come here, and I will tell you my secret.")
      dialogue += ("You are ready to leave the village.")
      dialogue += ("I made a super strength potion.")
      dialogue += ("But unfortunately it is stolen by the evil powers.")
      dialogue += ("You need to defeat the monster in order to get it.")
      dialogue += ("You will need the potion to fight Angevil.")
      (true, "\n" + dialogue.map( "Witch: " + _ ).mkString("\n"))
    }
    else {
      (true, "Witch: Go on what are waiting for?")
    }
  } 
}

object Quarry extends Building("Quarry", "You can work here to earn money.") {
  this.availableCommands = this.availableCommands :+ "work"
}

class Gate(private val player: Player, private val adv: Adventure) extends Building("Gate of the village", "When you are ready, you can leave the village to complete the quest of defeating Angevil.") {
  override def enter() = {
    if (this.player.getStrength < 10) {
      player.leave()
      (false, "You don't have enough strength to fight the evil powers.")
    }
    else if (!this.player.hasTalkedWithWitch) {
      player.leave()
      (false, "You have to talk to the witch before you leave the village.")
    }
    else {
      this.player.leaveVillage()
      (true, "You are leaving the planet.")
    }
  }
}