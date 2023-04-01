//package top.joker.wechat.config;
//
//import com.netflix.client.AbstractLoadBalancerAwareClient;
//import com.netflix.client.config.IClientConfig;
//import okhttp3.ConnectionPool;
//import okhttp3.OkHttpClient;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
//import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
//import org.springframework.cloud.netflix.ribbon.RibbonClientName;
//import org.springframework.cloud.netflix.ribbon.okhttp.OkHttpLoadBalancingClient;
//import org.springframework.cloud.netflix.ribbon.okhttp.RetryableOkHttpLoadBalancingClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnProperty("ribbon.okhttp.enabled")
//@ConditionalOnClass(name = "okhttp3.OkHttpClient")
//public class OkHttpConfig {
//
//
//    @RibbonClientName
//    private String name = "client";
//
//    @Bean
//    @ConditionalOnMissingBean(AbstractLoadBalancerAwareClient.class)
//    @ConditionalOnClass(name = "org.springframework.retry.support.RetryTemplate")
//    public RetryableOkHttpLoadBalancingClient retryableOkHttpLoadBalancingClient(...) {
//        RetryableOkHttpLoadBalancingClient client = new RetryableOkHttpLoadBalancingClient(
//                delegate, config, serverIntrospector, loadBalancedRetryFactory);
//        return client;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(AbstractLoadBalancerAwareClient.class)
//    @ConditionalOnMissingClass("org.springframework.retry.support.RetryTemplate")
//    public OkHttpLoadBalancingClient okHttpLoadBalancingClient(....) {
//        OkHttpLoadBalancingClient client = new OkHttpLoadBalancingClient(delegate, config, serverIntrospector);
//        return client;
//    }
//
//    @Configuration(proxyBeanMethods = false)
//    protected static class OkHttpClientConfiguration {
//        private OkHttpClient httpClient;
//
//        @Bean
//        @ConditionalOnMissingBean(ConnectionPool.class)
//        public ConnectionPool httpClientConnectionPool(IClientConfig config, OkHttpClientConnectionPoolFactory connectionPoolFactory) {
//            //...
//            return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
//        }
//
//        @Bean
//        @ConditionalOnMissingBean(OkHttpClient.class)
//        public OkHttpClient client(OkHttpClientFactory httpClientFactory, ConnectionPool connectionPool, IClientConfig config) {
//            this.httpClient = httpClientFactory.createBuilder(false)
//                    .connectTimeout(ribbon.connectTimeout(), TimeUnit.MILLISECONDS)
//                    .readTimeout(ribbon.readTimeout(), TimeUnit.MILLISECONDS)
//                    .followRedirects(ribbon.isFollowRedirects())
//                    .connectionPool(connectionPool).build();
//            return this.httpClient;
//        }
//    }
//}
