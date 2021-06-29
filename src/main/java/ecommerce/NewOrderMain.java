package ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import ecommerce.services.KafkaDispatcher;

public class NewOrderMain {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	try(KafkaDispatcher dispatcher = new KafkaDispatcher()) {
		var email = "Thank you for your order! We are processing her!";
		for (int i = 0; i < 10; i++) {
			var key = UUID.randomUUID().toString();
			var value = "pedidoId: 01,usuarioId: " + key +  ",500 reais ";
			dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);
			dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
		}
	}


	}
}
