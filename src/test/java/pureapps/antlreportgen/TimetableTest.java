package pureapps.antlreportgen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

class TimetableTest
{
    @Test
    void shouldGenerateTimetable()
    {
        Timetable timetable = new Timetable(1, 10, 5, 30);
        assertNotNull(timetable);
        for (Map.Entry<Integer, Double> entry : timetable.entrySet())
        {
            assertTrue(entry.getValue() >= 1);
            assertTrue(entry.getValue() <= 10);
        }
        assertEquals(30, timetable.getSumOfHours());

    }
}