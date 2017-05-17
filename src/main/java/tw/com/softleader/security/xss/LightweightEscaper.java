package tw.com.softleader.security.xss;

import java.util.function.Function;

/**
 * 輕量級的跳脫字元 (Coverity跳太多了, 會影響記憶查詢相關的功能) <br>
 */
public class LightweightEscaper implements Function<String, String> {

  @Override
  public String apply(String s) {
    if (s == null) {
      return null;
    } else {
      int length = s.length();
      StringBuilder output = new StringBuilder((s.length() << 1) + 1);

      for (int i = 0; i < length; ++i) {
        char c = s.charAt(i);
        switch (c) {
          case '<':
            output.append("&lt;");
            break;
          case '>':
            output.append("&gt;");
            break;
          default:
            output.append(c);
        }
      }

      return output.toString();
    }
  }
}
