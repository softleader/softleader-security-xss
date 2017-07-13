package tw.com.softleader.security.xss.escaper;

import org.springframework.web.util.HtmlUtils;

import java.util.function.Function;

/**
 * 使用 Spring 的 {@link HtmlUtils#htmlEscape(String)} 來跳脫
 *
 * @author Matt S.Y Ho
 */
public class HtmlEscaper implements Function<String, String> {

  @Override
  public String apply(String s) {
    return HtmlUtils.htmlEscape(s);
  }
}
