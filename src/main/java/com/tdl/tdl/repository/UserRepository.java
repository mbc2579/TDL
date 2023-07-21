package com.tdl.tdl.repository;


import com.tdl.tdl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByUsernameContaining(String keyword);
    Optional<User> findByKakaoId(Long kakaoId);
    List<User> findAllByOrderByFollowersCountDesc();
}