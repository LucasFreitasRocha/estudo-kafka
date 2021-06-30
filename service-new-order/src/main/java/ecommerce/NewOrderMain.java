package ecommerce;


import ecommerce.model.Order;
import ecommerce.services.KafkaDispatcher;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	try(KafkaDispatcher orderDispatcher = new KafkaDispatcher<Order>()) {
		try(KafkaDispatcher emailDispatcher = new KafkaDispatcher<String>()) {
			var email = "Thank you for your order! We are processing her!";
			for (int i = 0; i < 10; i++) {
				Order order = new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(),new BigDecimal(500.75 ));
				var value = "pedidoId: 01,usuarioId: " + order.getUserId() +  ",500 reais ";
				orderDispatcher.send("ECOMMERCE_NEW_ORDER", order.getOrderId(), order);
				emailDispatcher.send("ECOMMERCE_SEND_EMAIL", order.getOrderId(), email);
			}
		}
	}


	}
}
