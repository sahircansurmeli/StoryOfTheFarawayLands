class Human(private var currentName: String, val adv: Adventure) {
  
  def name = this.currentName
  
  def changeName(newName: String) = {
    this.currentName = newName
  }
  
  def say(msg: String) = {
    Dialogue.p(this.name + ": " + msg)
  }
  
}