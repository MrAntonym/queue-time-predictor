import java.util.Random;

public class MatchScheduleSimulator {
    private int matchNumber;
    private int numberOfCourts;
    private double meanTime;
    private double standardDeviationTime;
    private double minimumTime;
    private Random randomNumberGenerator;
    public Court[] courts;
    
    public static void main(String args[]) {
        int matchNumber = 25; //input variable, counting number
        int numberOfCourts = 5; //input variable, counting number
        double meanTime = 30; //input variable, double minutes
        double standardDeviationTime = 5; //input variable, double
        double minimumTime = 0; //input variable, double minutes
        int iterations = 5;
        for (int i = 0; i < iterations; i++) {
            MatchScheduleSimulator sim = new MatchScheduleSimulator(matchNumber, numberOfCourts, meanTime, standardDeviationTime, minimumTime);
        System.out.println(sim.getResults());
        }
    }
    public MatchScheduleSimulator(int n, int c, double meanT, double sdT, double minT) {
        matchNumber = n;
        numberOfCourts = c;
        meanTime = meanT;
        standardDeviationTime = sdT;
        minimumTime = minT;
        randomNumberGenerator = new Random();
    }
    
    /**
    * @returns The number of minutes until the start of the match of number matchNumber.
    **/
    public double getResults() {
        int matchesLeft = getMatchNumber();
        double totalTime = 0;
        courts = new Court[getNumberOfCourts()];
        for (int i = 0; i < getNumberOfCourts(); i++) {
            courts[i] = new Court();
            courts[i].addMatch(matchTime());
        }
        matchesLeft -= getNumberOfCourts();
        while (matchesLeft > 0) {
            Court shortest = findShortestMatch();
            double t = shortest.getTimeLeft();
            for (Court c : courts) {
                c.removeTime(t);
            }
            totalTime += t;
            shortest.addMatch(matchTime());
            matchesLeft--;
        }
        return totalTime;
    }
    
    public Court findShortestMatch() {
        Court minimum = courts[0];
        for (Court c : courts) {
            if (c.getTimeLeft() < minimum.getTimeLeft()) {
                minimum = c;
            }
        }
        return minimum;
    }
    
    public double matchTime() {
        double t = getMeanTime();
        t += getStandardDeviationTime() * getRandomNormal();
        if (t > getMinimumTime()) {
            return t;
        } else {
            return getMinimumTime();
        }
    }
    
    public int getMatchNumber() {return matchNumber;}
    public int getNumberOfCourts() {return numberOfCourts;}
    public double getMeanTime() {return meanTime;}
    public double getStandardDeviationTime() {return standardDeviationTime;}
    public double getMinimumTime() {return minimumTime;}
    public double getRandomNormal() {return randomNumberGenerator.nextGaussian();}
    
}