class Solution {
    public double angleClock(int hour, int minutes) {
        double minuteHandDegree = minutes * 6;
        double hourHandDegree = hour*30 + (minutes/2.0);

        double diff =  Math.abs(minuteHandDegree - hourHandDegree);

        return Math.min(diff, 360-diff);
    }
}