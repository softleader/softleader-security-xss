package tw.com.softleader.security.xss.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** @author Matt S.Y Ho */
@Slf4j
public class XSSHttpServletRequest extends HttpServletRequestWrapper {

  private final Function<String, String> escaper;

  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request
   * @throws IllegalArgumentException if the request is null
   */
  protected XSSHttpServletRequest(HttpServletRequest request, Function<String, String> escaper) {
    super(request);
    this.escaper = Objects.requireNonNull(escaper, "escaper");
  }

  /**
   * Return the underlying native request object, if available.
   *
   * @param requiredType the desired type of request object
   * @return the matching request object, or {@code Optional.empty()} if none of that type is
   *     available
   * @see javax.servlet.http.HttpServletRequest
   */
  public <T> Optional<T> getNativeRequest(Class<T> requiredType) {
    return Optional.ofNullable(WebUtils.getNativeRequest(getRequest(), requiredType));
  }

  @Override
  public String getParameter(String name) {
    String before = super.getParameter(name);
    String escaped = Optional.ofNullable(before).map(escaper::apply).orElse(null);
    log.debug("Escaped request parameter '{}' from [{}] to [{}]", name, before, escaped);
    return escaped;
  }

  @Override
  public Map<String, String[]> getParameterMap() {
    Map<String, String[]> before = super.getParameterMap();
    Map<String, String[]> escaped =
        Optional.ofNullable(before)
            .map(Map::entrySet)
            .map(Collection::stream)
            .map(
                stream ->
                    stream.collect(
                        Collectors.toMap(
                            Map.Entry::getKey,
                            values ->
                                Stream.of(values.getValue())
                                    .map(escaper::apply)
                                    .toArray(String[]::new))))
            .orElse(null);
    log.debug("Escaped request parameter map from {} to {}", before, escaped);
    return escaped;
  }

  @Override
  public String[] getParameterValues(String name) {
    String[] before = super.getParameterValues(name);
    String[] escaped =
        Optional.ofNullable(before)
            .map(values -> Stream.of(values).map(escaper::apply).toArray(String[]::new))
            .orElse(null);
    log.debug(
        "Escaped request parameter values '{}' from {} to {}",
        name,
        Arrays.toString(before),
        Arrays.toString(escaped));
    return escaped;
  }
}
