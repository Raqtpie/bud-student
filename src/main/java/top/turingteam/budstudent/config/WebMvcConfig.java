package top.turingteam.budstudent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Raqtpie
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 允许所有来源跨域访问
                .allowedOriginPatterns("*")
                // 允许所有HTTP方法
                .allowedMethods("*")
                // 允许所有请求头
                .allowedHeaders("*")
                // 允许携带跨域凭据
                .allowCredentials(true);
    }
}
