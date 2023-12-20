package it.baccan.picocli;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

/**
 *
 * @author Matteo Baccan <matteo@baccan.it>
 */
@Sources({"file:application.properties","classpath:application.properties"})
public interface AppConfig extends Config {

    /**
     * Application info
     * @return
     */
    @DefaultValue("-default-")
    String info();
}
