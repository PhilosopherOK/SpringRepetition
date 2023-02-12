package ua.exercise.springRepetition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    AlternativeRockMusic alternativeRockMusic(){
        return new AlternativeRockMusic();
    }
    @Bean
    RockMusic rockMusic(){
        return new RockMusic();
    }
    @Bean
    ClassicalMusic classicalMusic(){
        return new ClassicalMusic();
    }
    @Bean
    MusicPlayer musicPlayer(){
        return new MusicPlayer(classicalMusic(), alternativeRockMusic(), rockMusic());
    }

}
