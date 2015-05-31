package org.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.domain.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
