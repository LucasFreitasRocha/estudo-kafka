package ecommerce;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class NewOrderMain {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		KafkaProducer<String, String> producer = new  KafkaProducer<String, String>(properties());
		var value = "pedidoId: 01,usuarioId: 01,500 reais ";
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", value, value);
		producer.send(record, (data, ex) -> {
			if(!Objects.isNull(ex)) {
				ex.printStackTrace();
				return;
			}
			System.out.println("sucesso enviando: " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + 
					"/timestamp" + data.timestamp());
		}).get();
		}
	

	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

}
