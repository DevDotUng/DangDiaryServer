package com.dangdiary.api.domain.users.repository.impl;


import com.dangdiary.api.domain.users.Dogs;
import com.dangdiary.api.domain.users.repository.DogsRepositoryCustom;
import java.util.Optional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class DogsRepositoryCustomImpl extends QuerydslRepositorySupport implements
    DogsRepositoryCustom {

    public DogsRepositoryCustomImpl() {
        super(Dogs.class);
    }

    @Override
    public Optional<Dogs> findByUserId(long userId) {
        return null;
    }
}
