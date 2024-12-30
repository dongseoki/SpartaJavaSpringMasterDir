package com.sparta.bootcamp.week_01.repository;

import com.sparta.bootcamp.week_01.entity.User;
import com.sparta.bootcamp.week_01.entity.UserOldV1;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

}
