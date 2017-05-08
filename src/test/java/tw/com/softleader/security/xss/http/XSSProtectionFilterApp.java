package tw.com.softleader.security.xss.http;

import com.coverity.security.Escape;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tw.com.softleader.security.xss.WebMvcApp;

/** @author Matt S.Y Ho */
public class XSSProtectionFilterApp extends WebMvcApp {

  private String input;

  private String escaped;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    input = "<script>alert('Hi Matt!');</script>";
    escaped = Escape.htmlText(input);
  }

  @Test
  public void testGetParameter() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/http-servlet-request/get-parameter")
                .param(HttpServletRequestController.PARAM_NAME, input)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.before").value(input))
        .andExpect(MockMvcResultMatchers.jsonPath("$.after").value(escaped));
  }

  @Test
  public void testGetParameterMap() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/http-servlet-request/get-parameter-map")
                .param(HttpServletRequestController.PARAM_NAME, input)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.before").isMap())
        .andExpect(MockMvcResultMatchers.jsonPath("$.after").isMap());
    // TODO: check value
  }

  @Test
  public void testGetParameterValues() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/http-servlet-request/get-parameter-values")
                .param(HttpServletRequestController.PARAM_NAME, input)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.before[0]").value(input))
        .andExpect(MockMvcResultMatchers.jsonPath("$.after[0]").value(escaped));
  }
}
