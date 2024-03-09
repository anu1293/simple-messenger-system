package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
@DiscriminatorValue("DIRECT")
public class DirectMesageEntity extends ChatEntity {

}
