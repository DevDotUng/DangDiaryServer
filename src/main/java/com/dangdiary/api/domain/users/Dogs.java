package com.dangdiary.api.domain.users;

import com.dangdiary.api.util.DateUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "dogs", schema = "dang_diary")
public class Dogs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id", nullable = false)
    private Long dogId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users users;

    @Column
    private String dogName;

    @Column
    private String profileImage;

    @Column
    private String breed;

    @Column
    private String gender;

    @Column
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birth;

    public static Dogs of(Users users, String dogName, String profileImage, String breed,
        String gender, String birth) {

        Dogs dogs = new Dogs();
        dogs.dogName = dogName;
        dogs.breed = breed;
        dogs.gender = gender;
        dogs.profileImage = profileImage;
        dogs.users = users;
        dogs.birth = DateUtils.toLocalDate(birth);
        return dogs;

    }


}
