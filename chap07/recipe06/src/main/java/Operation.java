import java.util.Date;

public class Operation {
  private String user;
  private String operation;
  private Date time;

  public void setUser(String user) {
    this.user = user;
  }

  public String getUser() {
    return user;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getOperation() {
    return operation;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Date getTime() {
    return time;
  }
}
