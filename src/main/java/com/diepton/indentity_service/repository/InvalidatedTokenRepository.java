package com.diepton.indentity_service.repository;

import com.diepton.indentity_service.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author diepton
 * @version 1.0
 * @since 7/22/2025
 */
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
