package dev.local.auth.config;

import dev.local.auth.service.JdbcUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Configure the {@link AuthenticationManagerBuilder} with initial
 * configuration to setup users.
 *
 * Higher priority since lesser ordered value indicate higher priority.
 * {@link Ordered#LOWEST_PRECEDENCE} has value as {@link Integer#MAX_VALUE}
 *
 * @author anilallewar
 *
 */
@Configuration
@Order(Ordered.LOWEST_PRECEDENCE - 30)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private final DataSource dataSource;

    private final JdbcUserDetailsService jdbcUserDetailsService;

    /**
     * Setup 2 users with different roles
     */
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withUser("dave").password("secret").roles("USER")
                .and().withUser("anil").password("password").roles("ADMIN", "USER")
                .and().getUserDetailsService();
        // @formatter:on

        // Add the default service
        jdbcUserDetailsService.addService(auth.getDefaultUserDetailsService());
    }
}
