package org.yildirimog.abilet.user.exception;

import org.springframework.http.HttpStatus;
import org.yildirimog.abilet.common.exception.BusinessException;

public class UserAlreadyExistException extends BusinessException {


    public UserAlreadyExistException(String email) {
        super("User already exists: " + email, HttpStatus.CONFLICT);
    }
}
