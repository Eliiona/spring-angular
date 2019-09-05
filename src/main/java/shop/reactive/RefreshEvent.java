package shop.reactive;

import org.springframework.context.ApplicationEvent;

public class RefreshEvent extends ApplicationEvent {
    private String message;
    /**
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public RefreshEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
