package com.mert.SpringMono.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.MappedSuperclass;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
    boolean state;
    Long createdate;
    Long updatedate;
}
