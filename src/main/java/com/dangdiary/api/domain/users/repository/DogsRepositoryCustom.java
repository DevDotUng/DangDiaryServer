package com.dangdiary.api.domain.users.repository;

import com.dangdiary.api.domain.users.Dogs;
import java.util.Optional;

public interface DogsRepositoryCustom {

    Optional<Dogs> findByUserId(long userId);
}
