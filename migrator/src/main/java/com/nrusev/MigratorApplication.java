package com.nrusev;

import com.nrusev.processor.Processor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class MigratorApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MigratorApplication.class, args);
		printBeans(ctx);

//		Processor italyProcessor = (Processor) ctx.getBean("italyProcessor");
//		italyProcessor.process();

//        Processor englandProcessor = (Processor) ctx.getBean("englandProcessor");
//		englandProcessor.process();

		Processor spainProcessor = (Processor) ctx.getBean("spainProcessor");
		spainProcessor.process();

	}

	private static void printBeans(ApplicationContext ctx){
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}
