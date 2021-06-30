package ecommerce.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class EmailService {
	
public static void main(String[] args) {

		EmailService emailService = new EmailService();

		try(KafkaService service =
				new KafkaService(EmailService.class.getSimpleName(),
						"ECOMMERCE_SEND_EMAIL", emailService::parse, String.class, new HashMap<String, String>())){
			service.run();
		}

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
