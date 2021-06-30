package ecommerce.services;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class LogService {
	
public static void main(String[] args) {
		LogService log = new LogService();
		try(KafkaService service = new KafkaService(LogService.class.getSimpleName(),
				Pattern.compile("ECOMMERCE.*"),
				log::parse, String.class,
				Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))){
			service.run();
		}
	}

	private void parse(ConsumerRecord<String, String> record) {
		System.out.println("------------------------------------------------------");
		System.out.println("LOG " + record.topic());
		System.out.println("key: " + record.key());
		System.out.println("Value: " + record.value());
		System.out.println("Partition: " + record.partition());
		System.out.println("Offset: " + record.offset());
	}

}
