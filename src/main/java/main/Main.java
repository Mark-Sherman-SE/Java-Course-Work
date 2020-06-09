package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {

   // private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

//    @Bean
//    public CommandLineRunner test(OperationRepository repository, ArticleRepository repositoryA, BalanceRepository repositoryB) {
//        return args -> {
//            /*
//            repository.save(new Application("FirstApp", "My first app"));
//            repository.save(new Application("SecondApp", "My second app"));
//            for (Application app : repository.findAll()) {
//                log.info("The application is: " + app.toString());
//            }*/
//           /* DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date date = dateFormat.parse("12/12/2012");
//            long time = date.getTime();
//            Article art = new Article("First Article");
//            repositoryA.save(art);
//            Balance balance = new Balance(new Timestamp(time), 500.0, 600.1, 900.6);
//            repositoryB.save(balance);
//            repository.save(new Operation(art, 500.67, 600.1, new Timestamp(time), balance));
//            date = dateFormat.parse("26/05/2013");
//            time = date.getTime();
//            art = new Article("Second Article");
//            repositoryA.save(art);
//            balance = new Balance(new Timestamp(time), 5144.5, 564.5, 900.6);
//            repositoryB.save(balance);
//            repository.save(new Operation(art, 5144.5, 7680.85, new Timestamp(time), balance));
//            for (Operation opp : repository.findAll()) {
//                log.info("The Opperation is " + opp.toString());
//            }*/
//
//        };
//    }
}
