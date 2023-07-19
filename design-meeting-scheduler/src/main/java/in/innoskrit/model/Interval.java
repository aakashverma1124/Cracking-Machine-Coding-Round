package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Interval {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
