package tw.com.softleader.security.xss.json.jackson;

import tw.com.softleader.security.xss.LightweightEscaper;

/**
 * @author Matt S.Y Ho
 */
public class LightweightXSSProtectionModule extends XSSProtectionModule {

  private static final long serialVersionUID = -4772342322538717373L;

  public LightweightXSSProtectionModule() {
    super(new LightweightEscaper());
  }
}
