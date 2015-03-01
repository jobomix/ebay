package com.ebay;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

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
}