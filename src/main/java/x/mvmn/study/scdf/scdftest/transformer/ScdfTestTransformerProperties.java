package x.mvmn.study.scdf.scdftest.transformer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mvmn-transformer")
public class ScdfTestTransformerProperties {
	private String someConfigProp;

	// TODO: use Lombok
	public String getSomeConfigProp() {
		return someConfigProp;
	}

	public void setSomeConfigProp(String someConfigProp) {
		this.someConfigProp = someConfigProp;
	}
}
