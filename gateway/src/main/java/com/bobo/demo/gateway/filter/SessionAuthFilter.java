package com.bobo.demo.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 *
 */
@Component
@Slf4j
public class SessionAuthFilter implements GlobalFilter, Ordered {

    private final RedissonClient redissonClient;

    @Resource
    private DiscoveryClient discoveryClient;

    public SessionAuthFilter(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        if (route == null) {
            return chain.filter(exchange);
        }
        System.out.println(route);
        RMap<Object, Object> session_token = redissonClient.getMap("session_id_user");
        exchange.getRequest().getCookies().toSingleValueMap().forEach((k, v) -> System.out.println(k + "-->" + v));
        //TODO 从注册中心获取服务真实列表
        HttpCookie bobo_session_id = exchange.getRequest().getCookies().getFirst("bobo_session_id");
        String cookie = bobo_session_id == null ? "" : bobo_session_id.getValue();

        System.out.println(route.getUri().getAuthority());
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("demo-auth");
        serviceInstanceList.forEach(serviceInstance -> {
            //获取host
            serviceInstance.getHost();
            //获取端口号
            serviceInstance.getPort();
            serviceInstance.getServiceId();
        });

        Object o = session_token.get(cookie); // 通过用户判断需要转发的地址


        //重定义路由
        Route newRoute = Route.async().asyncPredicate(route.getPredicate()).filters(route.getFilters()).id(route.getId()).order(route.getOrder()).
                uri("http://192.168.50.116:10011").build();
        exchange.getAttributes().put(GATEWAY_ROUTE_ATTR, newRoute);

        return chain.filter(exchange);

    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
