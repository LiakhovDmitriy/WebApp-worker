package com.example.test_task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }
        @Bean
        public CommandLineRunner demo (final WorkersService workersService){
            return new CommandLineRunner() {
                @Override
                public void run(String... strings) throws Exception {
                    Position position = new Position("Test");
                    Worker worker;

                    workersService.addPosition(position);

                    for (int i = 0; i < 13; i++) {
                        worker = new Worker(null, "Name" + i, "Surname" + i, "user" + i + "@test.com");
                        workersService.addWorker(worker);
                    }
                    for (int i = 0; i < 10; i++) {
                        worker = new Worker(position, "Other" + i, "OtherSurname" + i, "user" + i + "@other.com");
                        workersService.addWorker(worker);
                    }
                }
            };
        }
    }
