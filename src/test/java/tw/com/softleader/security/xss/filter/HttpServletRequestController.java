package tw.com.softleader.security.xss.filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @author Matt S.Y Ho */
@RestController
@RequestMapping("/http-servlet-request")
public class HttpServletRequestController {

  public static final String PARAM_NAME = "in";

  @RequestMapping("/request-param")
  public Escape requestParam(@RequestParam(PARAM_NAME) String in) {
    return new Escape(null, in);
  }

  @RequestMapping("/get-parameter")
  public Escape getParameter(HttpServletRequest request) {
    String beforeEscape =
        ((XSSHttpServletRequest) request)
            .getNativeRequest(HttpServletRequest.class)
            .map(nativeRequest -> nativeRequest.getParameter(PARAM_NAME))
            .orElseThrow(IllegalArgumentException::new);
    return new Escape(beforeEscape, request.getParameter(PARAM_NAME));
  }

  @RequestMapping("/get-parameter-map")
  public Escape getParameterMap(HttpServletRequest request) {
    Map<String, String[]> beforeEscape =
        ((XSSHttpServletRequest) request)
            .getNativeRequest(HttpServletRequest.class)
            .map(ServletRequest::getParameterMap)
            .orElseThrow(IllegalArgumentException::new);

    return new Escape(beforeEscape, request.getParameterMap());
  }

  @RequestMapping("/get-parameter-values")
  public Escape getParameterValues(HttpServletRequest request) {
    String[] beforeEscape =
        ((XSSHttpServletRequest) request)
            .getNativeRequest(HttpServletRequest.class)
            .map(nativeRequest -> nativeRequest.getParameterValues(PARAM_NAME))
            .orElseThrow(IllegalArgumentException::new);

    return new Escape(beforeEscape, request.getParameterValues(PARAM_NAME));
  }
}
