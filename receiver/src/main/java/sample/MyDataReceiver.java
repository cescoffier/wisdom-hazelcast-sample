/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package sample;

import org.apache.felix.ipojo.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.*;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.wisdom.api.http.websockets.Publisher;


@Component
@Instantiate
public class MyDataReceiver {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyDataReceiver.class);

    @Requires
    EventBus bus;

    @Requires
    Publisher publisher;

    private Handler<Message> handler;

    @Validate
    public void start() {
        handler = new Handler<Message>() {
            @Override
            public void handle(Message message) {
                LOGGER.info("Has received " + message.body());
                publisher.publish("/ws/data", message.body().toString());
            }
        };
        bus.registerHandler("/data", handler);
    }


    @Invalidate
    public void stop() {
        bus.unregisterHandler("/data", handler);
    }
}
