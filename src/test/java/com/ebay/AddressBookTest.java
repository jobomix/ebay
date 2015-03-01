package com.ebay;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressBookTest {


    @Test
    public void shouldCountTheNumberOfMales() {
        AddressBook addressBook = new AddressBook.Builder().build();
        assertEquals(3, addressBook.getMales());
    }

    @Test
    public void shouldRetrieveTheOldestPerson() {
        AddressBook addressBook = new AddressBook.Builder().build();
        assertEquals(AddressBookEntry.fromLine("Wes Jackson, Male, 14/08/74"), addressBook.getOldest());
    }

    @Test
    public void shouldCalculateTheDifferenceInDaysForTwoEntries() {
        DateTime me = new DateTime(1971,4,30,0,0);
        DateTime someoneElse = new DateTime(1971,5,3,0,0);
        assertEquals(3, AddressBook.getDifferenceInDays(me, someoneElse));

    }

    @Test
    public void shouldCalculateDiffInDaysReturnANegativeNumberIfFirstEntryIsYounger() {
        DateTime me = new DateTime(1971,4,30,0,0);
        DateTime someoneElse = new DateTime(1971,5,3,0,0);
        assertEquals(-3, AddressBook.getDifferenceInDays(someoneElse, me));
    }


}