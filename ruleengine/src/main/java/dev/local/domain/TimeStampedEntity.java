package dev.local.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class TimeStampedEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @Getter @Setter
    private Date createdAt;
    @Getter @Setter
    private Staff createdBy;
}
