package tw.com.softleader.security.xss.json.jackson;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Objects;
import java.util.function.Function;

/** @author Matt S.Y Ho */
public class XSSProtectionModule extends SimpleModule {
  private static final long serialVersionUID = 8414956352375368636L;

  public XSSProtectionModule(Function<String, String> escaper) {
    super(PackageVersion.VERSION);

    Objects.requireNonNull(escaper, "escaper");

    addDeserializer(String.class, new XSSStringDeserializer(escaper));
  }
}
