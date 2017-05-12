package tw.com.softleader.security.xss.json.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/** @author Matt S.Y Ho */
public class Body {
  private String str;
  private String[] strArray;
  private Collection<String> strCollection;

  public Body() {
  }

  public Body(String str, String[] strArray, Collection<String> strCollection) {
    this.str = str;
    this.strArray = strArray;
    this.strCollection = strCollection;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public String[] getStrArray() {
    return strArray;
  }

  public void setStrArray(String[] strArray) {
    this.strArray = strArray;
  }

  public Collection<String> getStrCollection() {
    return strCollection;
  }

  public void setStrCollection(Collection<String> strCollection) {
    this.strCollection = strCollection;
  }
}
