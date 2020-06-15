

abstract class Dialogue (val p1: Human, val p2: Human, adv: Adventure) {
  
  def p(msg: String) = {
    Dialogue.p(msg)
  }
  
  def start(): Unit
  
}

object Dialogue {
  val waitTimePerWord = 200
  
  def p(msg: String) = {
    println(msg);
    Thread.sleep(waitTimePerWord * msg.split(" ").length)
  }
}

class WakeUp(p1: Human, p2: Human, adv: Adventure) extends Dialogue(p1, p2, adv) {
  def start() = {
    this.p("You wake up in an unfamiliar place.")
    p1.say("Where am I?")
    this.p("You hear the door open. A strangely dressed old man approaches you.")
    p2.say("Hello, young man! You seem better now.")
    p1.say("Sir, who are you? What is this place?")
    p2.say("My name is Husam. And this is our pretty village.")
    p2.changeName("Husam")
    p1.say("And... how did I end up here?")
    p2.say("I don't know. I thought you would know. I found you near the sea, and brought you here")
    p1.say("Seriously? I don't remember anything")
    p2.say("Do you remember your name?")
    println()
    p1.changeName(readLine("Your name: "))
    println()
    p1.say("Yes, my name is... " + p1.name + ".")
    p2.say(p1.name + "... Since you don't remember anything, here is your new home.")
    p1.say("What!? No, no, no! I can't stay here... Sir, could you tell me where the nearest bus station or the airport is?")
    p2.say("Airport? Never heard of such a thing. But you look like you are from the Earth.")
    p1.say("How!?!? Are you from Mars or what?")
    p2.say("This is my home planet: Esenyurt.")
    p1.say("What!? So here is not Earth??")
    p2.say("Definitely not!")
    p2.say("But don't worry. You are safe here. Get some rest, then you can have a tour in the village.")
    p1.say("Sir, I cannot stay here. Is there way for me to get back to the Earth?")
    p2.say("Sooo... Actually, there should be a spaceship in the castle near the village. I don't know, maybe it is still functional.")
    p1.say("It is worth trying. Can you take me there?")
    p2.say("No, I can't. Angevil set up a protection field there. No one can enter besides her.")
    p1.say("How come? Who is Angevil")
    p2.say("She is the queen of evil. You should find a way to beat her if you want to go back to Earth.")
    p2.say("I am sorry I have to go now. Good luck!")

  }
}
  
object Endgame {
  def end() = {
    Dialogue.p("You leave the village.")
    Dialogue.p("You defeat the evil powers to get the strength potion.")
    Dialogue.p("You use the potion to gain superhuman strength.")
    Dialogue.p("You fight with Angevil.")
    Dialogue.p("You defeat her.")
    Dialogue.p("You now have access to the spaceship.")
    Dialogue.p("Finally, you can now return to Earth.")
    Dialogue.p("Game over!")
  }
}