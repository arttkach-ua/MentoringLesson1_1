package com.example.ValidatorPattern.model.readBook;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Table(name = "user_books")
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ReadBook {
    @EmbeddedId
    ReadBookId id;

    @Column(name = "is_read")
    Boolean isRead;

    @Column(name = "date")
    Date date;
}
