package x.mvmn.study.scdf.scdftest.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.BinderHeaderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaderMapper;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableConfigurationProperties(ScdfTestTransformerProperties.class)
@Configuration
public class ScdfTestTransformer {
	public static void main(String args[]) {
		SpringApplication.run(ScdfTestTransformer.class, args);
	}

	@Autowired
	protected ScdfTestTransformerProperties config;

	@Bean
	public Function<Message<?>, Map<String, Object>> transform() {
		return message -> {
			Object payload = message.getPayload();
			Map<String, Object> result = new HashMap<>();
			Map<String, String> headersStr = new HashMap<>();

			message.getHeaders().forEach((k, v) -> headersStr.put(k, v != null ? v.toString() : null));

			result.put("headers", headersStr);
			result.put("payload", payload);
			result.put("configProp", "Config prop val: " + config.getSomeConfigProp());

			return result;
		};
	}

	// See https://stackoverflow.com/questions/59155689/could-not-decode-json-type-for-key-file-name-in-a-spring-cloud-data-flow-stream
	@Bean("kafkaBinderHeaderMapper")
	public KafkaHeaderMapper kafkaBinderHeaderMapper() {
		BinderHeaderMapper mapper = new BinderHeaderMapper();
		mapper.setEncodeStrings(true);
		return mapper;
	}
}
