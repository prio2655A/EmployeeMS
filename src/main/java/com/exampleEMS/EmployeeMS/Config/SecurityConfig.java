package com.exampleEMS.EmployeeMS.Config;

import com.exampleEMS.EmployeeMS.Service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeDetailsService employeeDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();

        http.csrf().disable().authorizeHttpRequests()
                        .antMatchers("/EMS/**/add").hasAnyAuthority("Admin")
                        .antMatchers("/EMS/**/add/**").hasAnyAuthority("Admin")
                        .antMatchers("/EMS/asset/getAll").authenticated()
                        .antMatchers("/EMS/**/getAll").hasAnyAuthority("Admin")
                        .antMatchers("/EMS/asset/get/**").authenticated()
                        .antMatchers("/EMS/employee/get/**").authenticated()
                        .antMatchers("/EMS/**/get/**").hasAnyAuthority("Admin")
                        //.antMatchers("/EMS/employee/update/**").authenticated()   //will change role
                        .antMatchers("/EMS/**/update/**").hasAnyAuthority("Admin")
                        .antMatchers("/EMS/**/delete/**").hasAnyAuthority("Admin")
                        .antMatchers("/**").authenticated()
                        .anyRequest().authenticated().and().httpBasic().and()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.employeeDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
