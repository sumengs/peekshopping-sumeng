package com.sumeng.peekshopping.system.filter;


import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.*;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;

/**
 * Gateway日志
 *
 * @date: 2020/6/10 14:16
 * @author: sumeng
 */
@Component
@Slf4j
public class LogGlobalFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_PREFIX = "Request Info [ ";
    private static final String REQUEST_TAIL = " ]";
    private static final String RESPONSE_PREFIX = "Response Info [ ";
    private static final String RESPONSE_TAIL = " ]";
    private StringBuilder responseMsg = new StringBuilder();
    private StringBuilder requestMsg = new StringBuilder();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequestDecorator requestDecorator = new ServerHttpRequestDecorator(request);

        //request header
        HttpHeaders headers = requestDecorator.getHeaders();
        log.info(REQUEST_PREFIX + "header=" + headers + REQUEST_TAIL);

        //远程地址
        InetSocketAddress remoteAddress = requestDecorator.getRemoteAddress();
        //传输方法
        HttpMethod method = requestDecorator.getMethod();
        //url
        URI url = requestDecorator.getURI();

        requestMsg.append(REQUEST_PREFIX).append("remoteAddress=").append(remoteAddress).
                append("; method=").append(method).
                append("; url=").append(url).
                append(REQUEST_TAIL);
        log.info(requestMsg.toString());


        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        responseMsg.append(RESPONSE_PREFIX);

        ServerHttpResponseDecorator serverHttpResponseDecorator = new ServerHttpResponseDecorator(response) {

            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

                //response日志信息
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        String responseResult = new String(content, Charset.forName("UTF-8"));
                        responseMsg.append("status=").append(this.getStatusCode());
                        responseMsg.append(";header=").append(this.getHeaders());
                        responseMsg.append(";responseResult=").append(responseResult);
                        responseMsg.append(RESPONSE_TAIL);
                        log.info(responseMsg.toString());
                        return bufferFactory.wrap(content);
                    }));
                }
                return super.writeWith(body);
            }
        };


        return chain.filter(exchange.mutate().response(serverHttpResponseDecorator).build());

    }


    @Override
    public int getOrder() {
        return -2;
    }
}
