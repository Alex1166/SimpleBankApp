package my.bankapp.helpers;

import my.bankapp.model.Money;

@FunctionalInterface
public interface MoneyOperationFunction<T, U>  {
    Money applyAsMoney(T t, U u);
}
