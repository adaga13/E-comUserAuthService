package org.scaler.ecomuser.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Token extends BaseModel {

    private String value;

    private Date expiryAt;

    @ManyToOne
    private EcomUser user;

}
