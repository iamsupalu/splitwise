package com.example.splitwise;

import com.example.splitwise.Commands.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {
	Scanner sc=new Scanner(System.in);
	public CommandExecutor commandExecutor;

	@Autowired
	public SplitwiseApplication(CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	@Override
	public void run(String... args) throws Exception {
		while(true){
			String input=sc.next();
			commandExecutor.execute(input);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}


}
