package org.yildirimog.abilet.user.exception;

import org.springframework.http.HttpStatus;
import org.yildirimog.abilet.common.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Long id) {
        super("User not found " + id, HttpStatus.NOT_FOUND);
    }
}
