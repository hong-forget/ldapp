package com.ld.ldapp.domain;


import lombok.Data;

import java.util.Date;
@Data
public class Job {

  private Integer id;
  private Integer userId;
  private Date monitorData;
  private long workId;
}
