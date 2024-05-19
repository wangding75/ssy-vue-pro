package cn.iocoder.ssy.framework.websocket.core.sender.local;

import cn.iocoder.ssy.framework.websocket.core.sender.AbstractWebSocketMessageSender;
import cn.iocoder.ssy.framework.websocket.core.sender.WebSocketMessageSender;
import cn.iocoder.ssy.framework.websocket.core.session.WebSocketSessionManager;

/**
 * 本地的 {@link WebSocketMessageSender} 实现类
 *
 * 注意：仅仅适合单机场景！！！
 *
 * @author  Ssy
 */
public class LocalWebSocketMessageSender extends AbstractWebSocketMessageSender {

    public LocalWebSocketMessageSender(WebSocketSessionManager sessionManager) {
        super(sessionManager);
    }

}
