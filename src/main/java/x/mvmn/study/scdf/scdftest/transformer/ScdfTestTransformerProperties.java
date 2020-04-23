package x.mvmn.study.scdf.scdftest.transformer;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("mvmn-transformer")
@Data
public class ScdfTestTransformerProperties {
	private String someConfigProp;
}
