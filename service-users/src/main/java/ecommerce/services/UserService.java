package ecommerce.services;

import ecommerce.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class UserService {
    private final Connection connection;

    UserService() throws SQLException {
        String url = "jdbc:sqlite:target/users_database.db";
        connection = DriverManager.getConnection(url);
        try {
            connection.createStatement().execute("create table Users (" +
                    "uuid varchar(200) primary key," +
                    "email varchar(200))");

        }catch (SQLException e){
            // be careful, the sql could be wrong
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        UserService frauDetectorService = new UserService();
        try(KafkaService service =
                    new KafkaService<Order>(UserService.class.getSimpleName(),
                            "ECOMMERCE_NEW_ORDER",
                            frauDetectorService::parse,
                            Order.class,
                            new HashMap<String, String>())){
            service.run();
        }

    }

    private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException, SQLException {
        System.out.println("Encontrei um Novo Registro");
        System.out.println("------------------------------------------------------");
        System.out.println("Processing new order, checking for new user");
        System.out.println("Value: " + record.value());
        Order order = record.value();
        System.out.println("Order processed");

        if(isNewUser(order.getEmail())) {
            insertNewUser(order.getUserId(), order.getEmail());
        }
    }


    private void insertNewUser(String uuid, String email) throws SQLException {
        var insert = connection.prepareStatement("insert into Users (uuid, email) " +
                "values (?,?)");
        insert.setString(1, "uuid");
        insert.setString(2, email);
        insert.execute();
        System.out.println("Usuário uuid e " + email + " adicionado");
    }

    private boolean isNewUser(String email) throws SQLException {
        var exists = connection.prepareStatement("select uuid from Users " +
                "where email = ? limit 1");
        exists.setString(1, email);
        var results = exists.executeQuery();
        return !results.next();
    }

}
