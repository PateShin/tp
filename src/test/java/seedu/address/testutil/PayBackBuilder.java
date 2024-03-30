package seedu.address.testutil;

import seedu.address.model.PayBack;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class to help with building PayBack objects.
 * Example usage: <br>
 *     {@code PayBack ab = new PayBackBuilder().withPerson("John", "Doe").build();}
 */
public class PayBackBuilder {

    private PayBack payBack;

    public PayBackBuilder() {
        payBack = new PayBack();
    }

    public PayBackBuilder(PayBack payBack) {
        this.payBack = payBack;
    }

    /**
     * Adds a new {@code Person} to the {@code PayBack} that we are building.
     */
    public PayBackBuilder withPerson(Person person) {
        payBack.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Transaction} to the {@code PayBack} that we are building.
     */
    public PayBackBuilder withTransaction(Transaction transaction) {
        payBack.addTransaction(transaction);
        return this;
    }

    public PayBack build() {
        return payBack;
    }
}
