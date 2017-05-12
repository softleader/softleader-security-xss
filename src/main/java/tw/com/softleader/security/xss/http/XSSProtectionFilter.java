package tw.com.softleader.security.xss.http;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

/** @author Matt S.Y Ho */
@AllArgsConstructor
public class XSSProtectionFilter extends OncePerRequestFilter {

  @NonNull protected final Function<String, String> escaper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request instanceof XSSHttpServletRequest) {
      filterChain.doFilter(request, response);
    } else {
      filterChain.doFilter(new XSSHttpServletRequest(request, escaper), response);
    }
  }
}
