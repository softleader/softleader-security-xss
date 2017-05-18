package tw.com.softleader.security.xss.json.jackson;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tw.com.softleader.security.xss.WebMvcApp;
import tw.com.softleader.security.xss.escaper.HtmlEscaper;

/**
 * @author Matt S.Y Ho
 */
public class XSSProtectionModuleTest extends WebMvcApp {

  private String content;

  private String escaped;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    String input = "<script>alert('Hi Matt!');</script>";
    escaped = new HtmlEscaper().apply(input);
    Body vo = new Body(input, new String[]{input}, Lists.newArrayList(input));
    content = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(vo);
  }

  @Test
  public void testRequestBody() throws Exception {
    mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/jackson/request-body")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.str").value(escaped))
            .andExpect(MockMvcResultMatchers.jsonPath("$.strArray[0]").value(escaped))
            .andExpect(MockMvcResultMatchers.jsonPath("$.strCollection[0]").value(escaped));
  }
}
