package tw.com.softleader.security.xss.http;

import com.coverity.security.Escape;

import java.util.function.Function;

/** @author Matt S.Y Ho */
public class CoverityXSSProtectionFilter extends XSSProtectionFilter {

  public CoverityXSSProtectionFilter() {
    super(Escape::htmlText);
  }

  @Override
  public final void setEscaper(Function<String, String> escaper) {
    throw new UnsupportedOperationException("Use XSSProtectionFilter to customize escaper");
  }
}
