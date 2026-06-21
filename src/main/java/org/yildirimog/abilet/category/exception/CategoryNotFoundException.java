package org.yildirimog.abilet.category.exception;

import org.springframework.http.HttpStatus;
import org.yildirimog.abilet.common.exception.BusinessException;

public class CategoryNotFoundException extends BusinessException {

    public CategoryNotFoundException(Long id) {
        super("Category not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
