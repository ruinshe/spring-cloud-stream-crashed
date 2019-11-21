package cn.ruinshe.springcloudstreamcrashed;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@RemoteApplicationEventScan
@SpringBootApplication
public class SpringCloudStreamCrashedApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudStreamCrashedApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void schedule() {
        // to simply block the application.
    }
}
