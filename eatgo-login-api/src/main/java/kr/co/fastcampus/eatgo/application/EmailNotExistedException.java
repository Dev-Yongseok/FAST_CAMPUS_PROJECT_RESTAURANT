package kr.co.fastcampus.eatgo.application;

public class EmailNotExistedException extends RuntimeException{

    EmailNotExistedException(String email){
        super("Email is not already registered : " + email);
    }
}
