public class Court {
    private double timeLeft;
    
    public Court() {}
    
    public boolean addMatch(double t) {
        if (timeLeft == 0) {
            timeLeft = t;
            return true;
        } else {
            return false;
        }
    }
    
    public double getTimeLeft() {
        return timeLeft;
    }
    
    public void removeTime(double t) {
        timeLeft -= t;
    }
    
    public boolean isDone() {
        return timeLeft == 0;
    }
}