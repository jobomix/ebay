package com.ebay;

import java.io.*;

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
                    String entry[] = line.split(",");
                    if (entry[1].equals(" Male"))
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
