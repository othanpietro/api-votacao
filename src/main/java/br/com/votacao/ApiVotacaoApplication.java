package br.com.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ApiVotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiVotacaoApplication.class, args);
    }

}
