package com.ebay;

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


}