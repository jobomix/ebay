package com.ebay;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.text.DateFormat.SHORT;
import static java.util.Locale.UK;

/**
 * An entry in the addressBook
 */
public class AddressBookEntry {

    private static DateFormat dateFormat = DateFormat.getDateInstance(SHORT, UK);

    public enum Gender {
        Male, Female
    }

    private final String name;

    private final Gender gender;

    private final DateTime dateOfBirth;

    AddressBookEntry(String name, Gender gender, DateTime dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Build an entry from a text with a specific format
     * eg: Bill McKnight, Male, 16/03/77
     *
     * @param line
     * @return
     */
    public static AddressBookEntry fromLine(String line) {
        String[] entry = line.split(",");
        String name = entry[0];
        Gender gender = Gender.valueOf(entry[1].substring(1,entry[1].length()));
        DateTime dob;
        try {
            dob = new DateTime(dateFormat.parse(entry[2].substring(1,entry[2].length())));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date has not been parsed: ",e);
        }
        return new AddressBookEntry(name, gender, dob);
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressBookEntry entry = (AddressBookEntry) o;

        if (!dateOfBirth.equals(entry.dateOfBirth)) return false;
        if (gender != entry.gender) return false;
        if (!name.equals(entry.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        return result;
    }
}
