package tw.com.softleader.security.xss.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** @author Matt S.Y Ho */
@RequestMapping("/jackson")
@RestController
public class JacksonController {

  @RequestMapping(
    value = "/request-body",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public Object requestBody(@RequestBody Body body) throws JsonProcessingException {
    return body;
  }

  @RequestMapping(value = "/form-submit", method = RequestMethod.POST)
  public Object formSubmit(Body body) throws JsonProcessingException {
    return body;
  }
}
