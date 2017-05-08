package tw.com.softleader.security.xss.json.jackson;

import com.coverity.security.Escape;

/** @author Matt S.Y Ho */
public class CoverityXSSProtectionModule extends XSSProtectionModule {
  private static final long serialVersionUID = 8414956352375368636L;

  public CoverityXSSProtectionModule() {
    super(Escape::htmlText);
  }
}
