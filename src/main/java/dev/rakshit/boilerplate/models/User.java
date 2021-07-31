package dev.rakshit.boilerplate.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "users")
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "user", description = "All details about the user")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Column(name = "userId", updatable = false, nullable = false)
    private UUID userId;

    @ApiModelProperty(notes = "Email")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "createdAt")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
