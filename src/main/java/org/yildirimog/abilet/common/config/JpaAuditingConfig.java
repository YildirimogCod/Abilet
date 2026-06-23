package org.yildirimog.abilet.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {

    /**
     * Oturum açan kullanıcıyı createdBy/updatedBy alanlarına yazar.
     * JWT entegrasyonundan sonra SecurityContextHolder'dan kullanıcı adı (email)
     * döndürülecek. Şu an kimlik bilgisi olmadığı için boş bırakılıyor.
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return Optional::empty;
    }
}
