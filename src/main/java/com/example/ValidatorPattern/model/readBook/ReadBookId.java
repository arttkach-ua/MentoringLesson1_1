package com.example.ValidatorPattern.model.readBook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReadBookId implements Serializable {
    private static final long serialVersionUID = 8542710544572976501L;
    @Column(name="book_id")
    private int bookId;
    @Column(name="user_id")
    private int userId;
}
