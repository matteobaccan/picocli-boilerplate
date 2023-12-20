package it.baccan.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;

import org.aeonbits.owner.ConfigFactory;

/**
 *
 * @author Matteo
 */
@Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Prints the checksum (MD5 by default) of a file to STDOUT.")
@Slf4j
public class BoilerPlate implements Callable<Integer> {

    @Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;

    @Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "MD5";

    private static AppConfig appConfig;

    /**
     *
     * @return @throws Exception
     */
    @Override
    public Integer call() throws Exception { // your business logic goes here...        
        log.info("Application [{}]", appConfig.info());

        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        log.info("Digest [{}]", String.format("%0" + (digest.length * 2) + "x", new BigInteger(1, digest)));
        return 0;
    }

    /**
     * Main. This example implements Callable, so parsing, error handling and
     * handling user requests for usage help or version help can be done with
     * one line of code.
     *
     * @param args
     */
    public static void main(String... args) {
        appConfig = ConfigFactory.create(AppConfig.class);

        int exitCode = new CommandLine(new BoilerPlate()).execute(args);
        System.exit(exitCode);
    }
}
