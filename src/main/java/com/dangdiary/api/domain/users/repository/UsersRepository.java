package com.dangdiary.api.domain.users.repository;

import com.dangdiary.api.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    public Users findById(long id);
}