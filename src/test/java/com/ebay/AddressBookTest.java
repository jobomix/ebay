package com.ebay;

import org.joda.time.DateTime;
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
        DateTime me = new DateTime(1971, 4, 30, 0, 0);
        DateTime someoneElse = new DateTime(1971, 5, 3, 0, 0);
        assertEquals(3, AddressBook.computeDifferenceInDays(me, someoneElse));
    }

    @Test
    public void shouldCalculateDiffInDaysReturnANegativeNumberIfFirstEntryIsYounger() {
        DateTime me = new DateTime(1971, 4, 30, 0, 0);
        DateTime someoneElse = new DateTime(1971, 5, 3, 0, 0);
        assertEquals(-3, AddressBook.computeDifferenceInDays(someoneElse, me));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRetrieveTheFirstEntryOrThrowAnException() {
        AddressBook addressBook = new AddressBook.Builder().build();
        addressBook.getDifferenceInDays("Not exist", "Sarah Stone");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRetrieveTheSecondEntryOrThrowAnException() {
        AddressBook addressBook = new AddressBook.Builder().build();
        addressBook.getDifferenceInDays("Bill McKnight", "Not exist");
    }

    @Test
    public void shouldRetrieveTheDifferenceInDaysBetweenTwoEntries() {
        AddressBook addressBook = new AddressBook.Builder().build();
        assertEquals(2862, addressBook.getDifferenceInDays("Bill McKnight", "Paul Robinson"));
    }

}