import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class PersonalTimer {
    private LocalDateTime when;

    public PersonalTimer(){
        this.when = LocalDateTime.now();
    }

    public PersonalTimer(int year, int month, int day){
        this.when = LocalDateTime.of(LocalDate.of(year,month,day), LocalTime.now());
    }

    public void start(){
        var timer = new Timer(1000,(ActionEvent event) -> {
            System.out.println("Now, the time is " + when.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            when = when.plusSeconds(1);
        });
        timer.start();
    }
}
