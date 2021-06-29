package ecommerce.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FrauDetectorService {
	
public static void main(String[] args) {
		FrauDetectorService frauDetectorService = new FrauDetectorService();
		try(KafkaService service =
				new KafkaService(FrauDetectorService.class.getSimpleName(),
						"ECOMMERCE_NEW_ORDER", frauDetectorService::parse)){
			service.run();
		}

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
