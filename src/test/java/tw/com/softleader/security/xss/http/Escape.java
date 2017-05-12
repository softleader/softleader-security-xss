package tw.com.softleader.security.xss.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Matt S.Y Ho */
public class Escape {

  private Object before;
  private Object after;

  public Escape() {
  }

  public Escape(Object before, Object after) {
    this.before = before;
    this.after = after;
  }

  public Object getBefore() {
    return before;
  }

  public void setBefore(Object before) {
    this.before = before;
  }

  public Object getAfter() {
    return after;
  }

  public void setAfter(Object after) {
    this.after = after;
  }
}
