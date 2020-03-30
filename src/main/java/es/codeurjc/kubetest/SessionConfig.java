package es.codeurjc.kubetest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import org.springframework.session.hazelcast.HazelcastIndexedSessionRepository;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@EnableHazelcastHttpSession
@Configuration
public class SessionConfig {

	@Bean
	public HazelcastInstance hazelcastInstance() {
		MapAttributeConfig attributeConfig = new MapAttributeConfig()
				.setName(HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
				.setExtractor(PrincipalNameExtractor.class.getName());

		Config config = new Config();

		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		joinConfig.getKubernetesConfig().setEnabled(true);

		config.getMapConfig(HazelcastIndexedSessionRepository.DEFAULT_SESSION_MAP_NAME)
				.addMapAttributeConfig(attributeConfig)
				.addMapIndexConfig(new MapIndexConfig(
					HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

		return Hazelcast.newHazelcastInstance(config);
	}

}