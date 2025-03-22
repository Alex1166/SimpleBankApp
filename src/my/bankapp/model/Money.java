package my.bankapp.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Money implements Comparable<Money> {
    BigDecimal value;

    public Money() {
        this("0");
    }

    public Money(String value) {
        this.setValue(value);
    }

    public Money(BigDecimal value) {
        this.setValue(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(String value) {
        BigDecimal bigValue;
        try {
            bigValue = new BigDecimal(value);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(String.format("Invalid amount of money was passed: %s", value), nfe);
        }

        this.setValue(bigValue);
    }

    public void setValue(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(String.format("Amount of money must not be less than zero. %s provided", value));
        }

        this.value = value;
    }

    public Money addValue(Money value) {
        return new Money(this.value.add(value.getValue()));
    }

    public Money subtractValue(Money value) {
        if (value.compareTo(this) > 0) {
            throw new IllegalArgumentException("Not enough money on the account");
        }
        return new Money(this.value.subtract(value.getValue()));
    }

    public boolean isZeroOrLess() {
        return this.getValue().compareTo(BigDecimal.ZERO) <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public int compareTo(Money o) {
        return this.getValue().compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
