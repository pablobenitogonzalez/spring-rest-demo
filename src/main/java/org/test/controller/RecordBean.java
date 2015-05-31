package org.test.controller;

import org.test.domain.Record;

import java.util.Date;

@SuppressWarnings(RestPaths.UNUSED)
public class RecordBean {
    public Date created;
    public Date last_updated;

    public RecordBean() {}

    public RecordBean(Record record) {
        this.created = record.getCreated();
        this.last_updated = record.getLastUpdate();
    }
}
