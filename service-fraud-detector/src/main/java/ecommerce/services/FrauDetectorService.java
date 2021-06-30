package ecommerce.services;

import ecommerce.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class FrauDetectorService {
	private final KafkaDispatcher<Order> orderDispatcher = new KafkaDispatcher<>();
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

	private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
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
			Order order = record.value();
			if(isaFraud(record)){
				System.out.println("Order is a fraud!");
				orderDispatcher.send("ECOMMERCE_ORDER_REJECTED", order.getEmail(), order);
			}else{
				System.out.println("Approved " + order);
				orderDispatcher.send("ECOMMERCE_ORDER_APPROVED", order.getEmail(), order);
			}
			System.out.println("Order processed");
	}

	private boolean isaFraud(ConsumerRecord<String, Order> record) {
		return record.value().getAmount().compareTo(new BigDecimal("3000")) >= 0;
	}


}
