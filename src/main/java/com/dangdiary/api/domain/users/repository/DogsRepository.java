package com.dangdiary.api.domain.users.repository;

import com.dangdiary.api.domain.users.Dogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogsRepository extends JpaRepository<Dogs, Long>, DogsRepositoryCustom {

}
