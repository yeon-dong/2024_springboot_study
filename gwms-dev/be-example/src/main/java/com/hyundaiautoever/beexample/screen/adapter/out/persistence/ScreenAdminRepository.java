package com.hyundaiautoever.beexample.screen.adapter.out.persistence;

import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenAdminRepository extends JpaRepository<ScreenAdmin, Long> {
}
