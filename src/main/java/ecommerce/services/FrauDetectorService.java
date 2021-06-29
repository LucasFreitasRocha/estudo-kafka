package ecommerce.services;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class FrauDetectorService {
	
public static void main(String[] args) {
		FrauDetectorService frauDetectorService = new FrauDetectorService();
		KafkaService service = new KafkaService(FrauDetectorService.class.getSimpleName(),"ECOMMERCE_NEW_ORDER", frauDetectorService::parse);
		service.run();
	}

	private void parse(ConsumerRecord<String, String> record) {
		System.out.println("Encontrei um Novo Registro");
			System.out.println("------------------------------------------------------");
			System.out.println("Processing new order, checking for fraud ");
			System.out.println("key: " + record.key());
			System.out.println("Value: " + record.value());
			System.out.println("Partition: " + record.partition());
			System.out.println("Offset: " + record.offset());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Order processed");
	}


}
