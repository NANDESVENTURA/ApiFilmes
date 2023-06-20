package Filmex.api.domain.film;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static Filmex.api.domain.film.Note.UM;
import static Filmex.api.domain.film.Note.ZERO;

@Table(name = "films")
@Entity(name = "Films")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean watched;


    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    private Note note;

    public Film(DataRecordFilm data) {
        this.name = data.name();
        this.platform = data.platform();
        this.gender = data.gender();
        this.watched = false;
        this.note = ZERO;
    }

    public void updateData(DataUpdateFilm data) {
        if(data.name() != null) {this.name = data.name();}
        if(data.platform() != null) {this.platform = data.platform();}
        if(data.gender() != null) {this.gender = data.gender();}
    }
    public void updateWatchingData(DataUpdateWatchingFilm data) {
        {this.watched =true;}
        if(data.note() != null) {this.note = data.note();}

    }


}
