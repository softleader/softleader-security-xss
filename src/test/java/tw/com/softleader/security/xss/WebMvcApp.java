package tw.com.softleader.security.xss;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tw.com.softleader.security.xss.http.CoverityXSSProtectionFilter;
import tw.com.softleader.security.xss.http.XSSProtectionFilter;
import tw.com.softleader.security.xss.json.jackson.CoverityXSSProtectionModule;

import java.util.List;

/** @author Matt S.Y Ho */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebMvcApp.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "tw.com.softleader")
@WebAppConfiguration
@Configuration
public class WebMvcApp extends WebMvcConfigurerAdapter {

  @Autowired WebApplicationContext wac;

  protected MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc =
        MockMvcBuilders.webAppContextSetup(wac)
            .alwaysDo(MockMvcResultHandlers.print())
            .addFilters(filter())
            .build();
  }

  @Bean
  public XSSProtectionFilter filter() {
    return new CoverityXSSProtectionFilter();
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters(converters);
    converters.add(new MappingJackson2HttpMessageConverter(mapper()));
  }

  @Bean
  public ObjectMapper mapper() {
    return Jackson2ObjectMapperBuilder.json().modules(new CoverityXSSProtectionModule()).build();
  }
}
