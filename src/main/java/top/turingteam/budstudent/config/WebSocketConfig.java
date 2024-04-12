package top.turingteam.budstudent.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.turingteam.budstudent.middleware.handler.DataScreenWebSocketHandler;
import top.turingteam.budstudent.middleware.interceptor.WebSocketInterceptor;

/**
 * @author Raqtpie
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final DataScreenWebSocketHandler dataScreenWebSocketHandler;

    private final WebSocketInterceptor webSocketInterceptor;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dataScreenWebSocketHandler, "/websocket/dataView")
                .addInterceptors(webSocketInterceptor)
                .setAllowedOrigins("*");
    }
}
