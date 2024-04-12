package top.turingteam.budstudent.middleware.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.service.AdminUserService;

import java.util.Map;

/**
 * @author Raqtpie
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    private final AdminUserService adminUserService;

    @Override
    public boolean beforeHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response, @NotNull WebSocketHandler wsHandler, @NotNull Map<String, Object> attributes) throws Exception {
        long loginId = Long.parseLong(
                (String) StpUtil.getLoginIdByToken(
                        request.getURI().getQuery().replace("Authorization=", "")));
        AdminUser adminUser = adminUserService.getById(loginId);
        if (adminUser == null) {
            response.setStatusCode(HttpStatusCode.valueOf(ResultCode.UNAUTHORIZED.code));
            response.getBody().write("token 有误".getBytes());
            return false;
        }
        attributes.put("id", adminUser.getId());
        log.info("beforeHandshake: {}", adminUser);
        return true;
    }
}
