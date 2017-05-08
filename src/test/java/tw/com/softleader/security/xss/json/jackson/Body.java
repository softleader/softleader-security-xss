package tw.com.softleader.security.xss.json.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/** @author Matt S.Y Ho */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Body {
  private String str;
  private String[] strArray;
  private Collection<String> strCollection;
}
