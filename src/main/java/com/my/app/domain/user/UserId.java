package com.my.app.domain.user;

import com.my.app.core.domain.DomainEntity;
import com.my.app.core.domain.EntityId;

public class UserId extends EntityId.IdAsInt {

    public static UserId empty = new UserId(emptyValue);

    private UserId(Object src) {
        super(src);
    }

    public static UserId of(Object src) {
        return new UserId(src);
    }

}