package com.javaschool;

import lombok.Getter;
import lombok.Setter;

import javax.ejb.Singleton;
import java.io.Serializable;

@Getter
@Setter
@Singleton
public class EventDto implements Serializable {
    private String date;
    private String patientName;
    private String promedName;
    private String promedKind;
    private String nurseName;
    private String comment;
    private String dose;
    private String building;
    private int ward;
    private String status;

}
