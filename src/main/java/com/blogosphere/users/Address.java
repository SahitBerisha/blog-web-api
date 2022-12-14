package com.blogosphere.users;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public final class Address implements Serializable {

  @Column(name = "country")
  private String country;

  @Column(name = "city")
  private String city;

  @Column(name = "zip_code")
  private String zipCode;
}
