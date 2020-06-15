

class Player(name: String, adv: Adventure, startingPlace: Option[Building]) extends Human(name, adv) {
  
  val availableCommands = Vector("stats")
  
  private var strength = 0
  private var charisma = 0
  private var money = 0
  private var needSleep = false
  
  private var building: Option[Building] = startingPlace
  private var currentLocation = this.adv.village.topLeft
  
  private var talkedWithWitch = false
  
  def insideOf = this.building
  
  def sleepy = needSleep
  
  private def sleepyMessage = "You are too tired to perform this action. You need to go home and sleep."
  
  def help = {
    println("\nAvailable commands:")
    (this.location.availableCommands ++ this.availableCommands).mkString("\n")
  }
  
  def stats = s"\nYour stats\n------------\nStrength: ${this.strength}\nCharisma: ${this.charisma}\nCoins: ${this.money}\n"
  
  def getCharisma = this.charisma
  def getStrength = this.strength
  
  def hasTalkedWithWitch = this.talkedWithWitch
  def talkToWitch(b: Boolean) = {
    if (b) {
      this.talkedWithWitch = true
    }
  }
  
  def leave() = {
    this.insideOf match {
      case Some(b) => {
        val oldBuilding = this.insideOf.get
        this.building = None
        "\nYou have left " + oldBuilding.nameWithArticle("the") + ".\nYou are in the village."
      }
      case None => "You are not inside a building."
    }
  }
  
  def location =
    if (this.insideOf.isDefined)
      this.insideOf.get
    else this.currentLocation
  
  def go(direction: String) = {
    val destination = this.currentLocation.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if (destination.isDefined) {
      "\nYou go " + direction + "."
    }
    else
      "You can't go " + direction + "."
  }
  
  def leaveVillage() = {
    Endgame.end()
    this.adv.gameRunning = false
  }
  
  def enter() = {
    if (this.currentLocation.building.isDefined) {
      if (this.sleepy) {
        if (this.currentLocation.building.get == Home) {
          this.building = this.currentLocation.building
          "You enter " + this.building.get.nameWithArticle("the") + "."
        }
        else
          this.sleepyMessage
      }
      else {
       this.building = this.currentLocation.building
       val enterResult = this.building.get.enter()
       if (this.building.isDefined) {
         this.talkToWitch(enterResult._1)
         if (!this.adv.isOver) {
           "You enter " + this.building.get.nameWithArticle("the") + "." + "\n" + enterResult._2
         }
         else {
           ""
         }
       }
       else {
         this.adv.village.topMiddle.building.get.enter()._2
       }
      }
    }
    else
      "There is nowhere to enter here."
  }
  
  def work() = {
    if (this.sleepy)
      this.sleepyMessage
    else {
      this.money += 100
      this.needSleep = true
      "You have worked at the quarry, and earned 100 coins.\nYou are tired now. You need to go home and sleep."
    }
  }
  
  def sleep() = {
    if (this.sleepy) {
      this.needSleep = false
      println("You have fallen asleep.")
      Thread.sleep(800)
      "You have woken up very well rested."
    }
    else {
      "You are not tired at the moment."
    }
  }
  
  def buyDrinks() = {
    if (this.money >= 50) {
      this.money -= 50
      this.charisma += 10
      "You have bought drinks for everyone in the tavern.\n-50 coins\n+10 charisma"
    }
    else {
      "You don't have enough money to buy drinks for everyone."
    }
  }
  
  def buy() = {
    if (this.money >= 150) {
      this.money -= 150
      WeaponShop.swordAvailable = false
      this.strength += 10
      "You have bought a sword."
    }
    else {
      "You don't have enough money to buy the sword."
    }
  }
  
  def exit() = {
    val r = readLine("\nType 'y' if you want to quit the game: ")
    if (r == "y" || r == "yes") {
      this.adv.gameRunning = false
      "\nYou quit the game.\nThank you for playing!!"
    }
    else {
      "You continue playing the game."
    }
  }
  
  /* These are the cheat codes of the game */
  def banaparaver() = {
    this.money += 1000
    "\nCheat code activated.\n+1000 coins"
  }
  
  def guckuvvet() = {
    this.strength += 10
    "\nCheat code activated.\n+10 strength"
  }
  
  def karizmatik() = {
    this.charisma += 10
    "\nCheat code activated.\n+10 charisma"
  }
  
  def buyucu() = {
    this.banaparaver()
    this.guckuvvet()
    this.karizmatik()
    "\nCheat code activated.\n+1000 coins\n+10 strength\n+10 charisma"
  }
  
  def macerayahazirim() = {
    this.buyucu()
    this.talkedWithWitch = true
    "\nCheat code activated.\nYou are ready to leave the village."
  }
}