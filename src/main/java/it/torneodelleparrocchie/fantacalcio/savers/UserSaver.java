package it.torneodelleparrocchie.fantacalcio.savers;
/**
 * Created by dsalvatore on 24/06/17.
 * <p>
 * L'idea della classe è quella di poter inserire dei controlli sui dati
 * Per la creazione di un nuovo utente devo solo controllare che i dati siano coerenti:
 * username non nullo, non già utilizzato e senza caratteri particolari
 * password con almeno 8 caratteri
 * email non nullo, non già utilizzato e coerente con le convenzioni
 * Per l'update, oltre a fare i controlli sull'input devo anche aggiornare (in caso di cambio di username)
 * la FantaTeam associata (passando per il FantaTeamSaver). L'intero update su tutte le tabelle deve
 * essere fatto tutto nello stesso metodo (marcandolo magari come @Transactional anche se potrebbe essere
 * inutile, dovrebbe occuparsene hibernate
 */

import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class UserSaver {

    private Logger logger = LoggerFactory.getLogger(UserSaver.class);

    private boolean update = false;

    private String username;
    private String password;
    private String email;

    private UserRepository userRepository;

    private UserSaver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserSaver(User user, UserRepository userRepository) {
        this.userRepository = userRepository;

        update = true;
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
    }

    public UserSaver username(String username) {
        if (update) {
            User oldUser = userRepository.findByUsername(username);
            if (oldUser == null) {
                throw new IllegalArgumentException(String.format("Can't find a user with username %s", username));
            }
        }
        this.username = username;
        logger.info(String.format("Username: %s", username));
        return this;
    }

    public UserSaver password(String password) {
        this.password = password;
        logger.info(String.format("Password: %s", password));
        return this;
    }

    public UserSaver email(String email) {
        this.email = email;
        logger.info(String.format("Email: %s", email));
        return this;
    }

    public Long save() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userRepository.save(user).getId();
    }

    public static UserSaver create(User user, UserRepository userRepository) {
        if (user == null) {
            return new UserSaver(userRepository);
        } else {
            return new UserSaver(user, userRepository);
        }
    }
}
