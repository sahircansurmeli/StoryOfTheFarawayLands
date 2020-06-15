import scala.collection.mutable.Map

class Area(var building: Option[Building]) extends Place {
  private val neighbors = Map[String, Area]()
  var availableCommands = Vector("go", "enter")
  
  def neighbor(direction: String) = this.neighbors.get(direction)
  
  def setNeighbor(direction: String, neighbor: Area) = {
    this.neighbors += direction -> neighbor
  }
  
  def setNeighbors(exits: Vector[(String, Area)]) = {
    this.neighbors ++= exits
  }
  
  def addBuilding(b: Option[Building]) = {
    this.building = b
  }
  
  def fullDescription = {
    var buildingString = ""
    if (this.building.isDefined)
      buildingString = "You see " + this.building.get.nameWithArticle("a") + " here."
    else
      buildingString = "This place seems quite empty."
    val exitList = "\nExits available: " + this.neighbors.keys.mkString(" ")
    buildingString + exitList
  }
  
}