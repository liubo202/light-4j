/*
 * Copyright 2010-2013 Coda Hale and Yammer, Inc., 2014-2017 Dropwizard Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.dropwizard.metrics;

import org.junit.Test;

import io.dropwizard.metrics.Clock;

import java.lang.management.ManagementFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class ClockTest {
    @Test
    public void cpuTimeClock() throws Exception {
        final Clock.CpuTimeClock clock = new Clock.CpuTimeClock();

        assertThat((double) clock.getTime())
                .isEqualTo(System.currentTimeMillis(),
                           offset(100.0));

        assertThat((double) clock.getTick())
                   .isEqualTo(ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime(),
                              offset(1000000.0));
    }

    @Test
    public void userTimeClock() throws Exception {
        final Clock.UserTimeClock clock = new Clock.UserTimeClock();

        assertThat((double) clock.getTime())
                .isEqualTo(System.currentTimeMillis(),
                           offset(100.0));

        assertThat((double) clock.getTick())
                .isEqualTo(System.nanoTime(),
                           offset(100000.0));
    }

    @Test
    public void defaultsToUserTime() throws Exception {
        assertThat(Clock.defaultClock())
                .isInstanceOf(Clock.UserTimeClock.class);
    }
}
