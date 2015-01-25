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

import org.apache.felix.ipojo.annotations.Requires;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.EventBus;
import org.wisdom.api.annotations.Service;
import org.wisdom.api.annotations.scheduler.Every;
import org.wisdom.api.scheduler.Scheduled;

import java.util.Random;

@Service
public class MyDataSender implements Scheduled {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyDataSender.class);

    @Requires
    EventBus bus;

    Random random = new Random();

    @Every("1s")
    public void generateAndSendData() {
        int value = random.nextInt(40);
        bus.publish("/data", value);
        LOGGER.info("Has published {} on {}", value, "/data");
    }
}
