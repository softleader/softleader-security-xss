package tw.com.softleader.security.xss.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

/** @author Matt S.Y Ho */
@Slf4j
public class XSSStringDeserializer extends StdScalarDeserializer<String> {

  private static final long serialVersionUID = -1184343455367339303L;
  private final Function<String, String> escaper;
  private final StringDeserializer delegate = StringDeserializer.instance;

  public XSSStringDeserializer(Function<String, String> escaper) {
    super(String.class);
    this.escaper = escaper;
  }

  @Override
  public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String before = delegate.deserialize(p, ctxt);
    String escaped = Optional.ofNullable(before).map(escaper::apply).orElse(before);
    log.debug("Escaped field '{}' from [{}] to [{}]", p.getCurrentName(), before, escaped);
    return escaped;
  }

  @Override
  public String deserializeWithType(
      JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
      throws IOException {
    return delegate.deserializeWithType(jp, ctxt, typeDeserializer);
  }

  @Override
  public boolean isCachable() {
    return delegate.isCachable();
  }
}
