package tw.com.softleader.security.xss.http;

import tw.com.softleader.security.xss.escaper.HtmlEscaper;

import java.util.function.Function;

/**
 * @author Matt S.Y Ho
 */
public class HtmlXSSProtectionFilter extends XSSProtectionFilter{

  public HtmlXSSProtectionFilter() {
    super(new HtmlEscaper());
  }
}
