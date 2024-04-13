package com.singh.backend.mdtohtml.config;

public class Async {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Integer queueCapacity;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(final Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(final Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(final Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

}
