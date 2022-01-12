package com.bobo.demo.gateway.filter;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义过滤器
 *
 * @author bo
 */
@Component
@Slf4j
public class RequestRecorderGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long start = System.currentTimeMillis();
        UUID uuid = UUID.fastUUID();
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    log.info("--------{} start----------", uuid);
                    log.info("{} - {} ,耗时:{}ms", uuid, exchange.getRequest().getURI().getRawPath(),
                            System.currentTimeMillis() - start);
                    log.info("{}-状态:{}", uuid, exchange.getResponse().getStatusCode());
                    log.info("--------{} end----------", uuid);
                })
        );
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
