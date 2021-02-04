package com.hesoyam.pharmacy.unit;

import com.hesoyam.pharmacy.util.DateTimeRange;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
class DateTimeRangeTests {
    /**
     * https://stackoverflow.com/a/5601502
     */

    @Test
    void testDateTimeRangesOverlap(){
        LocalDateTime testFrom = LocalDateTime.of(2021, Month.JANUARY, 10, 0, 0);
        LocalDateTime testTo = LocalDateTime.of(2021, Month.JANUARY, 15, 0, 0);

        DateTimeRange testing = new DateTimeRange(testFrom, testTo);

        DateTimeRange startInside = new DateTimeRange(testFrom.minusDays(2), testFrom.plusDays(1));
        DateTimeRange insideStartTouching = new DateTimeRange(testFrom, testTo.plusDays(2));
        DateTimeRange enclosingStartTouching = new DateTimeRange(testFrom, testTo.minusDays(2));
        DateTimeRange enclosing = new DateTimeRange(testFrom.plusDays(1), testTo.minusDays(1));
        DateTimeRange enclosingEndTouching = new DateTimeRange(testFrom.plusDays(2), testTo);
        DateTimeRange exactMatch = new DateTimeRange(testFrom, testTo);
        DateTimeRange inside = new DateTimeRange(testFrom.minusDays(2), testTo.plusDays(2));
        DateTimeRange insideEndTouching = new DateTimeRange(testFrom.minusDays(2), testTo);
        DateTimeRange endInside = new DateTimeRange(testTo.minusDays(2), testTo.plusDays(2));

        boolean result1 = testing.overlaps(startInside);
        boolean result2 = testing.overlaps(insideStartTouching);
        boolean result3 = testing.overlaps(enclosingStartTouching);
        boolean result4 = testing.overlaps(enclosing);
        boolean result5 = testing.overlaps(enclosingEndTouching);
        boolean result6 = testing.overlaps(exactMatch);
        boolean result7 = testing.overlaps(inside);
        boolean result8 = testing.overlaps(insideEndTouching);
        boolean result9 = testing.overlaps(endInside);

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
        assertThat(result4).isTrue();
        assertThat(result5).isTrue();
        assertThat(result6).isTrue();
        assertThat(result7).isTrue();
        assertThat(result8).isTrue();
        assertThat(result9).isTrue();
    }

    @Test
    void testDateTimeRangesDontOverlap(){
        LocalDateTime testFrom = LocalDateTime.of(2021, 1, 10, 0, 0);
        LocalDateTime testTo = LocalDateTime.of(2021, 1, 15, 0, 0);

        DateTimeRange testing = new DateTimeRange(testFrom, testTo);

        DateTimeRange after = new DateTimeRange(testFrom.minusDays(5), testFrom.minusDays(2));
        DateTimeRange startTouching = new DateTimeRange(testFrom.minusDays(2), testFrom);
        DateTimeRange endTouching = new DateTimeRange(testTo, testTo.plusDays(2));
        DateTimeRange before = new DateTimeRange(testTo.plusDays(2), testTo.plusDays(5));

        boolean result1 = testing.overlaps(after);
        boolean result2 = testing.overlaps(startTouching);
        boolean result3 = testing.overlaps(endTouching);
        boolean result4 = testing.overlaps(before);

        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result4).isFalse();
    }

    @Test
    void testDateTimeRangesInclude(){
        LocalDateTime testFrom = LocalDateTime.of(2021, Month.JANUARY, 10, 0, 0);
        LocalDateTime testTo = LocalDateTime.of(2021, Month.JANUARY, 15, 0, 0);

        DateTimeRange testing = new DateTimeRange(testFrom, testTo);

        DateTimeRange enclosingStartTouching = new DateTimeRange(testFrom, testTo.minusDays(2));
        DateTimeRange enclosing = new DateTimeRange(testFrom.plusDays(1), testTo.minusDays(1));
        DateTimeRange enclosingEndTouching = new DateTimeRange(testFrom.plusDays(2), testTo);
        DateTimeRange exactMatch = new DateTimeRange(testFrom, testTo);

        boolean result1 = testing.includes(enclosingStartTouching);
        boolean result2 = testing.includes(enclosing);
        boolean result3 = testing.includes(enclosingEndTouching);
        boolean result4 = testing.includes(exactMatch);

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
        assertThat(result4).isTrue();
    }

    @Test
    void testDateTimeRangesDontInclude(){
        LocalDateTime testFrom = LocalDateTime.of(2021, Month.JANUARY, 10, 0, 0);
        LocalDateTime testTo = LocalDateTime.of(2021, Month.JANUARY, 15, 0, 0);

        DateTimeRange testing = new DateTimeRange(testFrom, testTo);

        DateTimeRange startInside = new DateTimeRange(testFrom.minusDays(2), testFrom.plusDays(1));
        DateTimeRange insideStartTouching = new DateTimeRange(testFrom, testTo.plusDays(2));
        DateTimeRange inside = new DateTimeRange(testFrom.minusDays(2), testTo.plusDays(2));
        DateTimeRange insideEndTouching = new DateTimeRange(testFrom.minusDays(2), testTo);
        DateTimeRange endInside = new DateTimeRange(testTo.minusDays(2), testTo.plusDays(2));
        DateTimeRange after = new DateTimeRange(testFrom.minusDays(5), testFrom.minusDays(2));
        DateTimeRange startTouching = new DateTimeRange(testFrom.minusDays(2), testFrom);
        DateTimeRange endTouching = new DateTimeRange(testTo, testTo.plusDays(2));
        DateTimeRange before = new DateTimeRange(testTo.plusDays(2), testTo.plusDays(5));

        boolean result1 = testing.includes(startInside);
        boolean result2 = testing.includes(insideStartTouching);
        boolean result3 = testing.includes(inside);
        boolean result4 = testing.includes(insideEndTouching);
        boolean result5 = testing.includes(endInside);
        boolean result6 = testing.includes(after);
        boolean result7 = testing.includes(startTouching);
        boolean result8 = testing.includes(endTouching);
        boolean result9 = testing.includes(before);

        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result4).isFalse();
        assertThat(result5).isFalse();
        assertThat(result6).isFalse();
        assertThat(result7).isFalse();
        assertThat(result8).isFalse();
        assertThat(result9).isFalse();
    }
}
