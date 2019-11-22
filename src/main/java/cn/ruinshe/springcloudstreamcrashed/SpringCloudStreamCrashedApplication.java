package cn.ruinshe.springcloudstreamcrashed;

import cn.ruinshe.springcloudstreamcrashed.event.SimpleEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@RemoteApplicationEventScan
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudStreamCrashedApplication {

    @Autowired private ApplicationEventPublisher publisher;
    @Value("${spring.cloud.bus.id}") private String busId;
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudStreamCrashedApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Scheduled(fixedDelay = 5000L)
    public void schedule() {
        publisher.publishEvent(new SimpleEvent(this, busId, simpleDateFormat.format(new Date())));
    }
}
