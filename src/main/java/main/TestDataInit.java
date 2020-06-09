package main;

import main.entity.Article;
import main.entity.Balance;
import main.entity.Operation;
import main.entity.User;
import main.repository.ArticleRepository;
import main.repository.BalanceRepository;
import main.repository.OperationRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = dateFormat.parse("12/12/2012");
//        long time = date.getTime();
//        Article art = new Article("First Article");
//        articleRepository.save(art);
//        Balance balance = new Balance(new Timestamp(time), 500.0, 600.1, 900.6);
//        balanceRepository.save(balance);
//        operationRepository.save(new Operation(art, 500.67, 600.1, new Timestamp(time), balance));
//        date = dateFormat.parse("26/05/2013");
//        time = date.getTime();
//        art = new Article("Second Article");
//        articleRepository.save(art);
//        balance = new Balance(new Timestamp(time), 5144.5, 564.5, 900.6);
//        balanceRepository.save(balance);
//        operationRepository.save(new Operation(art, 5144.5, 7680.85, new Timestamp(time), balance));

        //userRepository.save(new User("user", pwdEncoder.encode("pwd"), Collections.singletonList("ROLE_USER")));
        //userRepository.save(new User("admin", pwdEncoder.encode("apwd"), Collections.singletonList("ROLE_ADMIN")));

    }
}
