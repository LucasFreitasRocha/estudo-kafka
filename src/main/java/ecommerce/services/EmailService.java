package ecommerce.services;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailService {
	
public static void main(String[] args) {

		EmailService emailService = new EmailService();

		KafkaService service = new KafkaService(EmailService.class.getSimpleName(),"ECOMMERCE_SEND_EMAIL", emailService::parse);
		service.run();
		//consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));
		
	}

	private void parse(ConsumerRecord<String, String> poll) {
		System.out.println("------------------------------------------------------");
		System.out.println("Sending email ");
		System.out.println("key: " + poll.key());
		System.out.println("Value: " + poll.value());
		System.out.println("Partition: " + poll.partition());
		System.out.println("Offset: " + poll.offset());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("email send");
	}


}
