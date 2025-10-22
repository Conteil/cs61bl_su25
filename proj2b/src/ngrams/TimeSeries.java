package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        if (ts != null) {
            List<Integer> yearList = ts.years();
            for (int year: yearList) {
                if (year >= startYear && year <= endYear) {
                    this.put(year, ts.get(year));
                }
            }
        }
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
        Set<Integer> years = this.keySet();
        List<Integer> yearList = new ArrayList<>(years);
        return yearList;
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        List<Integer> yearList = years();
        List<Double> dataList = new ArrayList<>();
        for (int year: yearList) {
            dataList.add(get(year));
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        List<Integer> thisYears = this.years();
        List<Integer> tsYears = ts.years();
        TimeSeries returnTs = new TimeSeries();
        for (int year: thisYears) {
            returnTs.put(year, get(year));
        }
        for (int year: tsYears) {
            if (returnTs.containsKey(year)) {
                returnTs.put(year, ts.get(year) + get(year));
            } else {
                returnTs.put(year, ts.get(year));
            }
        }
        return returnTs;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        List<Integer> years = this.years();
        TimeSeries returnTs = new TimeSeries();
        for (int year: years) {
            if (ts.containsKey(year) && year <= ts.MAX_YEAR && year >= ts.MIN_YEAR) {
                returnTs.put(year,  get(year) / ts.get(year));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return returnTs;
    }
}
