package com.ebay;

import java.io.*;

import static com.ebay.AddressBookEntry.Gender.Male;

/**
 * Represents and address book
 */
public class AddressBook {

    private final int males;

    private AddressBook(Builder builder) {
        this.males = builder.males;
    }

    public int getMales() {
        return males;
    }


    /**
     * Build an address book from a file.
     * This class is not thread safe
     */
    public static class Builder {

        private int males = 0;

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
