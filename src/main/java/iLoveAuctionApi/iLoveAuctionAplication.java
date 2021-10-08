package iLoveAuctionApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"iLoveAuction"})
@EnableMongoRepositories("iLoveAuction.persistencia")
public class iLoveAuctionAplication {

    public static void main(String[] args) {
        SpringApplication.run(iLoveAuctionAplication.class, args);
    }
}
