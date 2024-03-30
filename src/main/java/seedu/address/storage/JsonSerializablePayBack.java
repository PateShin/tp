package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PayBack;
import seedu.address.model.ReadOnlyPayBack;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * An Immutable PayBack that is serializable to JSON format.
 */
@JsonRootName(value = "payback")
class JsonSerializablePayBack {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TRANSACTION = "Transactions list contains duplicate transaction(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePayBack} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePayBack(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("transactions") List<JsonAdaptedTransaction> transactions) {
        this.persons.addAll(persons);
        this.transactions.addAll(transactions);
    }

    /**
     * Converts a given {@code ReadOnlyPayBack} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePayBack}.
     */
    public JsonSerializablePayBack(ReadOnlyPayBack source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        transactions.addAll(source.getTransactionList().stream()
                .map(JsonAdaptedTransaction::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PayBack} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PayBack toModelType() throws IllegalValueException {
        PayBack payBack = new PayBack();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (payBack.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            payBack.addPerson(person);
        }
        for (JsonAdaptedTransaction jsonAdaptedTransaction : transactions) {
            Transaction transaction = jsonAdaptedTransaction.toModelType();
            if (payBack.hasTransaction(transaction)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRANSACTION);
            }
            payBack.addTransaction(transaction);
        }
        return payBack;
    }

}
