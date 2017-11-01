package dev.local.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class TimeStampedEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private Date createdAt;
    private Staff createdBy;
}
