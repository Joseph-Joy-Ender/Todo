package com.semicolon.africa.Todo;

import com.semicolon.africa.Todo.controller.UserController;
import com.semicolon.africa.Todo.dtos.requests.AddTaskRequest;
import com.semicolon.africa.Todo.dtos.requests.LoginRequest;
import com.semicolon.africa.Todo.dtos.requests.RegisterRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TodoApplication {
private static final UserController controller = new UserController();
private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
		userMainMenu();
	}

	private static void userMainMenu(){
		String menu = """
				==============================
				1 -> Register
				2 -> Login
				3 -> Add Task
				4 -> Find Task
				5 -> Delete a task
				6 -> Delete all task
				7 -> Exit
				""";
		String response = input(menu);
		services(response);
	}

	private static String input(String menu) {
		System.out.println(menu);
		return scanner.nextLine();

	}

	private static void services(String response) {
		switch (response){
			case "1" -> register();
			case "2" -> login();
			case "3" -> addTask();
			case "4" -> findTask();
			case "5" -> deleteATask();
			case "6" -> deleteAllTask();
			case "7" -> exit();
		}
	}

	private static void exit() {
		System.exit(-1);
	}

	private static void deleteAllTask() {
		String username = input("Enter your username: ");
		System.out.println(controller.deleteAllTask(username));
		userMainMenu();

	}

	private static void deleteATask() {
		String title = input("Enter the name of the title you want to delete: ");
		System.out.println(controller.deleteATask(title));
		userMainMenu();
	}

	private static void findTask() {
		String username = input("Enter your username: ");
		System.out.println(controller.findTaskBelongingTo(username));
		userMainMenu();
	}

	private static void addTask() {
		AddTaskRequest taskRequest = new AddTaskRequest();
		String username =  input("Enter your name: ");
		String title = input("Enter title:");
		String message = input("Enter message: ");
		taskRequest.setUsername(username);
		taskRequest.setTitle(title);
		taskRequest.setMessage(message);
		System.out.println(controller.addTask(taskRequest));
		userMainMenu();
	}

	private static void login() {
		LoginRequest request = new LoginRequest();
		String username = input("Enter your username: ");
		String password = input("Enter your password: ");
		request.setUsername(username);
		request.setPassword(password);
		System.out.println(controller.login(request));
		userMainMenu();

	}

	private static void register() {
		RegisterRequest registerRequest = new RegisterRequest();
		String username = input("Enter your preferred username: ");
		String password = input("Enter your password: ");
		registerRequest.setUsername(username);
		registerRequest.setPassword(password);
		System.out.println(controller.register(registerRequest));
		userMainMenu();

	}

}
