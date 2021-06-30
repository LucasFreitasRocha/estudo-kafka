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
			var emailCode = "Thank you for your order! We are processing her!";
			var email = Math.random() + "@email.com";
			for (int i = 0; i < 10; i++) {

				Order order = new Order( UUID.randomUUID().toString(),
						new BigDecimal(Math.random() * 5000 + 1 ), email);
				var value = "pedidoId: 01,usuarioId: "  +  ",500 reais ";
				orderDispatcher.send("ECOMMERCE_NEW_ORDER", email, order);
				emailDispatcher.send("ECOMMERCE_SEND_EMAIL", email, emailCode);
			}
		}
	}


	}
}
