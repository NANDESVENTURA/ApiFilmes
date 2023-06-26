package Filmex.api.domain.film;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByName(String name);
    Optional<Film> findById(Long id);

    @Query(
            nativeQuery = true,value = "select * from films where user_id = :user_id "
    )
    List<Film> findByUserId(@Param("user_id")Long user_id);
    @Query(
            nativeQuery = true, value = "select * from films where id = :id and user_id = :user_id"
    )
    Film findFilmByIdAndUserid(Long id, Long user_id) ;
    @Query(
            nativeQuery = true,value = "select * from films where user_id = :user_id and watched = :watched"
    )
    List<Film> findByUserIdAndWatched(Long user_id, Boolean watched);

    @Query(
            nativeQuery = true, value = "select * from films where name = :name and user_id = :user_id "
    )
    Film findByNameAndUser(String name, Long user_id);
}
