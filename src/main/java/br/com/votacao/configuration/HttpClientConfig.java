package br.com.votacao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.http.HttpClient;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;

@Configuration
public class HttpClientConfig {

    @Bean
    @Primary
    public HttpClient httpClient(){
        return HttpClient.newBuilder()
                .sslContext(getInsecureSSlContext())
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(60))
                .build();
    }

    private SSLContext getInsecureSSlContext(){
        try{
            TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            TrustManager[] trusManagers = {trustManager};

            var sslContext = SSLContext.getInstance("TSL");

            sslContext.init(null, trusManagers, new SecureRandom());

            return sslContext;
        }catch (Exception e){
            return null;
        }
    }
}
