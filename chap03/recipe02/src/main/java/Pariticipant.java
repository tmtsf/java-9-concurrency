import java.util.concurrent.TimeUnit;

public class Pariticipant implements Runnable{
  private VideoConference conference;
  private String name;

  public Pariticipant(VideoConference conference, String name) {
    this.conference = conference;
    this.name = name;
  }

  @Override
  public void run() {
    long duration = (long)(Math.random() * 10);
    try {
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    conference.arrive(name);
  }
}
