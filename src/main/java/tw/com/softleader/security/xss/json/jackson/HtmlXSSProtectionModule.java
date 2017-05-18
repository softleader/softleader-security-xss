package tw.com.softleader.security.xss.json.jackson;

import tw.com.softleader.security.xss.escaper.HtmlEscaper;

/**
 * @author Matt S.Y Ho
 */
public class HtmlXSSProtectionModule extends XSSProtectionModule {

  private static final long serialVersionUID = 3911654092609312310L;

  public HtmlXSSProtectionModule() {
    super(new HtmlEscaper());
  }
}
