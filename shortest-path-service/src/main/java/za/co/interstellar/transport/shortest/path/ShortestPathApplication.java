package za.co.interstellar.transport.shortest.path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import za.co.interstellar.transport.shortest.path.config.RibbonConfiguration;

@EnableJpaRepositories
@SpringBootApplication
@ComponentScan
@RibbonClient(name="shortest-path-service", configuration = RibbonConfiguration.class)
public class ShortestPathApplication {
    
    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(ShortestPathApplication.class, args);
    }
    
    
    @EventListener(ApplicationReadyEvent.class)
    public void appReady(ApplicationReadyEvent event) {
        dataLoader.loadDataFromXlsFile();
    }

}
