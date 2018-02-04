package com.oclye.config;
import com.oclye.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author ocly
 * @date 2018/2/4 13:27
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userService() {
        return new UserService();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .and().csrf().disable();

//                //设置默认登录成功跳转页面
//                .defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
//                .and()
//                //开启cookie保存用户数据
//                .rememberMe()
//                //设置cookie有效期
//                .tokenValiditySeconds(60 * 60 * 24 * 7)
//                //设置cookie的私钥
//                .key("")
    }
}
