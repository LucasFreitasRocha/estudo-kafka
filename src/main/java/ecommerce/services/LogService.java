package ecommerce.services;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class LogService {
	
public static void main(String[] args) {
	
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties());

		consumer.subscribe(Pattern.compile("ECOMMERCE.*"));
		//consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));
		while (true) {
						
			ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(100));
			if(!poll.isEmpty()) {
				System.out.println("Encontrei um Novo Registro");
				poll.forEach(p -> {
					System.out.println("------------------------------------------------------");
					System.out.println("LOG " + p.topic());
					System.out.println("key: " + p.key());
					System.out.println("Value: " + p.value());
					System.out.println("Partition: " + p.partition());
					System.out.println("Offset: " + p.offset());
				});
				continue;
			}
		}
		
	}

	private static Properties properties() {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getSimpleName());
		return properties;
	}
}
