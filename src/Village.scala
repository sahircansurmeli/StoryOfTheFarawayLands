

object Village {
  
  val topLeft = new Area(Some(Home)) 
  var topMiddle = new Area(None)
  val topRight = new Area(Some(Tavern))
  val middleLeft = new Area(Some(WeaponShop))
  val middle = new Area(None)
  var middleRight = new Area(None)
  val bottomLeft = new Area(None)
  val bottomMiddle = new Area(Some(Quarry))
  val bottomRight = new Area(None)
  
  topLeft.setNeighbors(Vector("east" -> topMiddle, "south" -> middleLeft))
  topMiddle.setNeighbors(Vector("west" -> topLeft, "east" -> topRight, "south" -> middle))
  topRight.setNeighbors(Vector("west" -> topMiddle, "south" -> middleRight))
  middleLeft.setNeighbors(Vector("north" -> topLeft, "east" -> middle, "south" -> bottomLeft))
  middle.setNeighbors(Vector("north" -> topMiddle, "west" -> middleLeft, "east" -> middleRight, "south" -> bottomMiddle))
  middleRight.setNeighbors(Vector("north" -> topRight, "west" -> middle, "south" -> bottomRight))
  bottomLeft.setNeighbors(Vector("north" -> middleLeft, "east" -> bottomMiddle))
  bottomMiddle.setNeighbors(Vector("north" -> middle, "west" -> bottomLeft, "east" -> bottomRight))
  bottomRight.setNeighbors(Vector("north" -> middleRight, "west" -> bottomMiddle))

}