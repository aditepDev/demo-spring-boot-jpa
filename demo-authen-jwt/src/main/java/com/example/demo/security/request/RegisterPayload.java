package com.example.demo.security.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegisterPayload{
	private String username;
	private String password;
	private String address;
	private String phone;
	private String lastName;
	private String firstName;
	private double salary;

	public String getUsername() {
		return username.trim();
	}

	public String getPassword() {
		return password.trim();
	}

	public String getAddress() {
		return address.trim();
	}

	public String getPhone() {
		return phone.trim();
	}

	public String getLastName() {
		return lastName.trim();
	}

	public String getFirstName() {
		return firstName.trim();
	}

	public double getSalary() {
		return salary;
	}
}