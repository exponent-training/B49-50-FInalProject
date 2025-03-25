package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Login;
import com.Entity.User;
import com.example.DTO.ResponseDTO;
import com.example.Service.UserService;

@RestController
@RequestMapping("/exponent/api/user")
public class UserController 
{
	@Autowired
	private UserService us;
	
	/*
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody User user)
	{
		System.out.println(user);
		
		ResponseDTO response = us.registerUserinService(user);
		
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	*/
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody Login login)
	{
		System.out.println(login);
		
		ResponseDTO response = us.registerUserinService(login);
		
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.NOT_ACCEPTABLE);
	}
	
	// Tasks

	/*
	 * @PutMapping("/update/{email}") public ResponseEntity<User>
	 * updateUser(@PathVariable String email, @RequestBody User user) { try { User
	 * updatedUser = us.updateUserByEmail(email, user); return
	 * ResponseEntity.ok(updatedUser); } catch (RuntimeException e) { return
	 * ResponseEntity.notFound().build(); } }
	 */
	/*
	 * @DeleteMapping("/delete/{email}") public ResponseEntity<String>
	 * deleteUser(@PathVariable String email) { try { us.deleteUserByEmail(email);
	 * return ResponseEntity.ok("User deleted successfully"); } catch
	 * (RuntimeException e) { return ResponseEntity.notFound().build(); } }
	 */

}
