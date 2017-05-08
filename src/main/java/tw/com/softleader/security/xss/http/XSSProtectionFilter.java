package tw.com.softleader.security.xss.http;

import lombok.*;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

/** @author Matt S.Y Ho */
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class XSSProtectionFilter extends OncePerRequestFilter {

  @Setter @NonNull private Function<String, String> escaper = Function.identity();

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
