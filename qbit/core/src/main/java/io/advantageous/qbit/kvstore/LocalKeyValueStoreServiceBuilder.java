package io.advantageous.qbit.kvstore;

import io.advantageous.qbit.reactive.Reactor;
import io.advantageous.qbit.reactive.ReactorBuilder;
import io.advantageous.qbit.service.stats.StatsCollector;
import io.advantageous.qbit.time.Duration;
import io.advantageous.qbit.util.Timer;

import java.util.Optional;

public class LocalKeyValueStoreServiceBuilder {
    private Timer timer;
    private Reactor reactor;
    private int localCacheSize = 1_000;
    private StatsCollector statsCollector;
    private Duration flushCacheDuration;
    private boolean debug;

    public Timer getTimer() {
        if (timer == null) {
            timer = Timer.timer();
        }
        return timer;
    }

    public LocalKeyValueStoreServiceBuilder setTimer(Timer timer) {
        this.timer = timer;
        return this;
    }

    public Reactor getReactor() {
        if (reactor == null) {
            reactor = ReactorBuilder.reactorBuilder().build();
        }
        return reactor;
    }

    public LocalKeyValueStoreServiceBuilder setReactor(Reactor reactor) {
        this.reactor = reactor;
        return this;
    }

    public int getLocalCacheSize() {
        return localCacheSize;
    }

    public LocalKeyValueStoreServiceBuilder setLocalCacheSize(int localCacheSize) {
        this.localCacheSize = localCacheSize;
        return this;
    }

    public StatsCollector getStatsCollector() {
        if (statsCollector == null) {
            statsCollector = new StatsCollector() {
                @Override
                public void increment(String name) {

                }

                @Override
                public void recordCount(String name, long count) {

                }

                @Override
                public void recordLevel(String name, long level) {

                }

                @Override
                public void recordTiming(String name, long duration) {

                }
            };
        }
        return statsCollector;
    }

    public LocalKeyValueStoreServiceBuilder setStatsCollector(StatsCollector statsCollector) {
        this.statsCollector = statsCollector;
        return this;
    }

    public Duration getFlushCacheDuration() {
        if (flushCacheDuration == null) {
            flushCacheDuration = Duration.hours(1);
        }
        return flushCacheDuration;
    }

    public LocalKeyValueStoreServiceBuilder useDefaultFlushCacheDuration() {
        getFlushCacheDuration();
        return this;
    }

    public LocalKeyValueStoreServiceBuilder setFlushCacheDuration(Duration flushCacheDuration) {
        this.flushCacheDuration = flushCacheDuration;
        return this;
    }

    public boolean isDebug() {
        return debug;
    }

    public LocalKeyValueStoreServiceBuilder setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public LocalKeyValueStoreService build() {
        return new LocalKeyValueStoreService(
                getTimer(),
                getReactor(),
                getLocalCacheSize(),
                getStatsCollector(),
                (flushCacheDuration==null) ? Optional.<Duration>empty() :
                        Optional.of(getFlushCacheDuration()),
                isDebug());
    }

    public static LocalKeyValueStoreServiceBuilder localKeyValueStoreBuilder() {
        return new LocalKeyValueStoreServiceBuilder();
    }
}