package com.ebay;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import static com.ebay.AddressBookEntry.Gender.Female;
import static com.ebay.AddressBookEntry.Gender.Male;
import static org.junit.Assert.*;

public class AddressBookEntryTest {

    @Test
    public void shouldCreateAnEntryFromAline() {
        AddressBookEntry bill = AddressBookEntry.fromLine("Bill McKnight, Male, 16/03/77");
        assertEquals("Bill McKnight", bill.getName());
        assertEquals(Male, bill.getGender());
        assertEquals(new DateTime(1977, 3, 16, 0, 0), bill.getDateOfBirth());
    }

    @Test
    public void shouldBaseEqualityOfEntryOnNameGenderAndDOB() {
        AddressBookEntry original = new AddressBookEntry("Wes Jackson", Male, new DateTime(1974,8,14,0,0));
        AddressBookEntry copy = new AddressBookEntry("Wes Jackson", Male, new DateTime(1974,8,14,0,0));

        AddressBookEntry withDifferentName = new AddressBookEntry("Michael Jackson", Male, new DateTime(1974,8,14,0,0));
        AddressBookEntry withDifferentGender = new AddressBookEntry("Wes Jackson", Female, new DateTime(1974,8,14,0,0));
        AddressBookEntry withDifferentDOB = new AddressBookEntry("Wes Jackson", Male, new DateTime(1975,8,14,0,0));

        assertEquals(copy,original);
        assertNotEquals(withDifferentName, original);
        assertNotEquals(withDifferentGender, original);
        assertNotEquals(withDifferentDOB,original);
    }

    @Test
    public void shouldHaveSameHashCodeWhenEntryAreEqual() {
        AddressBookEntry original = new AddressBookEntry("Wes Jackson", Male, new DateTime(1974,8,14,0,0));
        AddressBookEntry copy = new AddressBookEntry("Wes Jackson", Male, new DateTime(1974,8,14,0,0));

        assertEquals(copy.hashCode(), original.hashCode());
    }

}