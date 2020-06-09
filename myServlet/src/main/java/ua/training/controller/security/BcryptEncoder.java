package ua.training.controller.security;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptEncoder {

    public  String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    boolean checkPass(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
