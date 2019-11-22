package cn.ruinshe.springcloudstreamcrashed.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class SimpleEvent extends RemoteApplicationEvent {

    private String message;

    public SimpleEvent() { }

    public SimpleEvent(Object source, String originalService, String message) {
        super(source, originalService);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
