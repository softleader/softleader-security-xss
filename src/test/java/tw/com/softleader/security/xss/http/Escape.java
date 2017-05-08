package tw.com.softleader.security.xss.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Matt S.Y Ho */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Escape {

  private Object before;
  private Object after;
}
