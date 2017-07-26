package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by dsalvatore on 21/06/17.
 */
public interface UserRepository extends CrudRepository<User, String>{

    User findByUsername(@Param("username") String username);
}
