package com.ebay;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.ebay.AddressBookEntry.Gender.Male;

/**
 * Represents and address book
 */
public class AddressBook {

    private final int males;

    private final AddressBookEntry oldest;

    private final Map<String, AddressBookEntry> entries;

    private AddressBook(Builder builder) {
        this.males = builder.males;
        this.oldest = builder.oldest;
        this.entries = builder.entries;
    }

    public int getMales() {
        return males;
    }

    public AddressBookEntry getOldest() {
        return oldest;
    }

    /**
     * Retrieve the difference in days.
     * @param name1 The first entry name
     * @param name2 The second entry name
     * @return The difference in days between the 2 entries.
     */
    public int getDifferenceInDays(String name1, String name2) {
        AddressBookEntry entry1 = entries.get(name1);
        if (entry1 == null) {
            throw new IllegalArgumentException("The first argument must be an existing name");
        }

        AddressBookEntry entry2 = entries.get(name2);
        if (entry2 == null) {
            throw new IllegalArgumentException("The second argument must be an existing name");
        }
        return computeDifferenceInDays(entry1.getDateOfBirth(), entry2.getDateOfBirth());
    }

    /**
     * Compute the difference in days between 2 DateTime.
     * A positive number means dateTime1 is older than dateTime2
     * A negative number means dateTime1 is younger than dateTime2
     *
     * @param dateTime1
     * @param dateTime2
     * @return a positive or negative int representing the difference in days for to entries.
     */
    static int computeDifferenceInDays(DateTime dateTime1, DateTime dateTime2) {
        return Days.daysBetween(dateTime1, dateTime2).getDays();
    }


    /**
     * Build an address book from a file.
     * This class is not thread safe
     */
    public static class Builder {

        private int males = 0;

        private AddressBookEntry oldest;

        private Map<String, AddressBookEntry> entries = new HashMap<String, AddressBookEntry>();

        public AddressBook build() {
            BufferedReader reader = null;
            try {
                InputStream in = ClassLoader.getSystemResourceAsStream("AddressBook.txt");
                reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    AddressBookEntry entry = AddressBookEntry.fromLine(line);

                    if (entry.getGender() == Male)
                        males++;

                    if (oldest == null || entry.getDateOfBirth().isBefore(oldest.getDateOfBirth())) {
                        oldest = entry;
                    }

                    entries.put(entry.getName(), entry);

                }
            } catch (Exception e) {
                throw new RuntimeException("Problem occurred while reading input file: ", e);
            } finally {
                close(reader);
            }
            return new AddressBook(this);
        }


        private void close(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
