package org.yildirimog.abilet.category.exception;

import org.springframework.http.HttpStatus;
import org.yildirimog.abilet.common.exception.BusinessException;

public class CategoryAlreadyExistsException extends BusinessException {

    public CategoryAlreadyExistsException(String name) {
        super("Category already exists: " + name, HttpStatus.CONFLICT);
    }
}
