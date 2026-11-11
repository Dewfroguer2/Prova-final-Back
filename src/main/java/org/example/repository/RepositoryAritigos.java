package org.example.repository;

import org.example.entity.Artigos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAritigos extends JpaRepository<Artigos, Long> {
}
