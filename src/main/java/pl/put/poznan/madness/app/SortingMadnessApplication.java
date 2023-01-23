package pl.put.poznan.madness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.madness"})
public class SortingMadnessApplication {

  public static void main(String[] args) {
    SpringApplication.run(SortingMadnessApplication.class, args);
  }
}
