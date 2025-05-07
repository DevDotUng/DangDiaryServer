package com.dangdiary.api.domain.users.repository.impl;

import static com.dangdiary.api.domain.users.QDogs.dogs;
import static com.dangdiary.api.domain.users.QUsers.users;

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
        return Optional.ofNullable(from(dogs)
            .join(dogs.users, users).fetchJoin()
            .where(users.id.eq(userId)).fetchOne());
    }
}
