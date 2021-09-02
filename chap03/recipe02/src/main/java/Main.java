public class Main {
  public static void main(String[] args) {
    var conference = new VideoConference(10);
    var conferenceThread = new Thread(conference);
    conferenceThread.start();

    for (int i = 0; i < 10; i++) {
      var p = new Pariticipant(conference, "Participant " + i);
      var t = new Thread(p);
      t.start();
    }
  }
}
