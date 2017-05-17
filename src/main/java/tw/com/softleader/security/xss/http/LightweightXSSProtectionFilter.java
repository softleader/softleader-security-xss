package tw.com.softleader.security.xss.http;

import tw.com.softleader.security.xss.LightweightEscaper;

/**
 * @author Matt S.Y Ho
 */
public class LightweightXSSProtectionFilter extends XSSProtectionFilter {

  public LightweightXSSProtectionFilter() {
    super(new LightweightEscaper());
  }
}