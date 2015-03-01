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

}