package utility;

import java.time.Duration;

public class TimeFormatter {


    public String formatDuration(Duration duration){
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive;
        if(greaterThanADay(absSeconds)){
            positive = String.format(
                    "%d:%02d:%02d",
                    absSeconds / 3600,
                    (absSeconds % 3600) / 60,
                    absSeconds % 60);
        } else {
            positive = String.format(
                    "%02d:%02d",
                    (absSeconds % 3600) / 60,
                    absSeconds % 60);
        }
        return seconds < 0 ? "-" + positive : positive;
    }

    private boolean greaterThanADay(long absSeconds) {
        return absSeconds > 3600;
    }
}
