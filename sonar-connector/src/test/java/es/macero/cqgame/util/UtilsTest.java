package es.macero.cqgame.util;

import static org.junit.Assert.*;

import org.junit.Test;

import es.macero.cqgame.util.Utils;

public class UtilsTest
{

    @Test
    public void testDateWithDaysWorks()
    {
        String input = "1d2h50min";
        assertEquals("P1DT2H50M", Utils.durationTranslator(input));
    }
    
    @Test
    public void testDateWithoutDaysWorks()
    {
        String input = "2h50min";
        assertEquals("PT2H50M", Utils.durationTranslator(input));
    }
    
    @Test
    public void testDateOnlyWithMin()
    {
        String input = "50min";
        assertEquals("PT50M", Utils.durationTranslator(input));
    }
    
    @Test
    public void testDateOnlyWithHours()
    {
        String input = "3h";
        assertEquals("PT3H", Utils.durationTranslator(input));
    }
    
    @Test
    public void testDateOnlyWithDays()
    {
        String input = "3d";
        assertEquals("P3D", Utils.durationTranslator(input));
    }

}
