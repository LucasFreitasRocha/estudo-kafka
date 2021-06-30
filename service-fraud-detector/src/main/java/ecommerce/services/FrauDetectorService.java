package ecommerce.services;

import ecommerce.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class FrauDetectorService {
	
public static void main(String[] args) {
		FrauDetectorService frauDetectorService = new FrauDetectorService();
		try(KafkaService service =
				new KafkaService<Order>(FrauDetectorService.class.getSimpleName(),
						"ECOMMERCE_NEW_ORDER",
						frauDetectorService::parse,
						Order.class,
						new HashMap<String, String>())){
			service.run();
		}

	}

	private void parse(ConsumerRecord<String, Order> record) {
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
