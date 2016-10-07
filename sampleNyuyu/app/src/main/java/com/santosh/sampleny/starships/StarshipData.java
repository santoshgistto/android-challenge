package com.santosh.sampleny.starships;

/**
 * Created by santoshrd on 6/10/16.
 */

public class StarshipData {
    private Results[] results;

    private String previous;

    private String count;

    private String next;

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    public String getPrevious ()
    {
        return previous;
    }

    public void setPrevious (String previous)
    {
        this.previous = previous;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getNext ()
    {
        return next;
    }

    public void setNext (String next)
    {
        this.next = next;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", previous = "+previous+", count = "+count+", next = "+next+"]";
    }
}
