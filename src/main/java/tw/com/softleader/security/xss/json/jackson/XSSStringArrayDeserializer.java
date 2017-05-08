//package tw.com.softleader.security.xss.json.jackson;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Optional;
//import java.util.function.Function;
//import java.util.stream.Stream;
//
///** @author Matt S.Y Ho */
//@Slf4j
//public class XSSStringArrayDeserializer extends StdDeserializer<String[]> {
//  private static final long serialVersionUID = 394058979540912299L;
//
//  private final Function<String, String> escaper;
//  private final StdDeserializer<String[]> delegate;
//
//  protected XSSStringArrayDeserializer(Function<String, String> escaper) {
//    super(String[].class);
//    this.escaper = escaper;
//    delegate = new StringArrayDeserializer();
//  }
//
//  @Override
//  public String[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//    String[] before = delegate.deserialize(p, ctxt);
//    String[] escaped =
//        Optional.ofNullable(before)
//            .map(values -> Stream.of(values).map(escaper::apply).toArray(String[]::new))
//            .orElse(null);
//    if (before != null && before.length > 0) {
//      log.debug(
//          "Escaped field '{}' from {} to {}",
//          p.getCurrentName(),
//          Arrays.toString(before),
//          Arrays.toString(escaped));
//    }
//    return escaped;
//  }
//}
