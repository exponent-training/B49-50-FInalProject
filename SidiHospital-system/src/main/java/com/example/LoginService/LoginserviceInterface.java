package com.example.LoginService;

import com.example.Entity.Login;

public interface LoginserviceInterface {

	Login loginUser(String email, String password);

	Login findByEmail(String email);

	Login forgotpassword(Login login1);


}
