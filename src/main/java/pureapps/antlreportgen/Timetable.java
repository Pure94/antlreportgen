package pureapps.antlreportgen;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

class Timetable extends LinkedHashMap<Integer, Double>
{
    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_PRECISION = 2;
    // default delta = 3min/60 = 0.05
    private static final double MIN_INCREMENT = 0.05;
    private final double maximumHoursPerDay;
    private Double sumOfHours;

    public Double getSumOfHours()
    {
        if (sumOfHours == null)
        {
            sumOfHours = 0.0;
            for (Map.Entry<Integer, Double> entry : this.entrySet())
            {
                sumOfHours += entry.getValue();
            }
        }

        return new BigDecimal(sumOfHours).setScale(DEFAULT_PRECISION, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private double addHours(int day, double hours)
    {
        final double currentHours = this.getOrDefault(day, 0.0);
        final double newHours = Math.min(currentHours + hours, maximumHoursPerDay);
        this.put(day, roundValue(newHours));
        return newHours - currentHours;
    }

    Timetable(double minimumHoursPerDay, double maximumHoursPerDay, int numberOfDays, double numberOfHoursPerMonth)
    {
        this.maximumHoursPerDay = maximumHoursPerDay;

        if (numberOfHoursPerMonth / numberOfDays > maximumHoursPerDay)
        {
            throw new IllegalArgumentException(
                    "Number of hours per month cannot be greater than maximum hours per day multiplied by number of days");
        }

        while (numberOfHoursPerMonth > MIN_INCREMENT)
        {
            for (int currentDay = 0; currentDay < numberOfDays; currentDay++)
            {
                double hours = roundValue(Math.random() * (maximumHoursPerDay - minimumHoursPerDay) + minimumHoursPerDay);

                if (hours > numberOfHoursPerMonth)
                {
                    hours = numberOfHoursPerMonth;
                }
                numberOfHoursPerMonth -= addHours(currentDay, hours);

                if (numberOfHoursPerMonth <= MIN_INCREMENT)
                {
                    break;
                }
            }
        }
    }

    public static double roundValue(double value)
    {
        return Math.round((Math.round(value / MIN_INCREMENT) * MIN_INCREMENT) * Math.pow(10, DEFAULT_PRECISION)) / Math.pow(10,
                DEFAULT_PRECISION);
    }
}
