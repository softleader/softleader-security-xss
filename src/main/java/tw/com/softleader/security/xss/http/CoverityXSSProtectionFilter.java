package tw.com.softleader.security.xss.http;

import com.coverity.security.Escape;

/** @author Matt S.Y Ho */
public class CoverityXSSProtectionFilter extends XSSProtectionFilter {

  public CoverityXSSProtectionFilter() {
    super(Escape::htmlText);
  }
}
