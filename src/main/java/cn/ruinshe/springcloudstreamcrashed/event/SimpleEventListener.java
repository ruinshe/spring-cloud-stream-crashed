package cn.ruinshe.springcloudstreamcrashed.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEventListener.class);

    @EventListener
    public void onSimpleEvent(SimpleEvent event) {
        logger.info("message: {}", event.getMessage());
    }
}
