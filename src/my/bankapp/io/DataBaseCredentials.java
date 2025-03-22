package my.bankapp.io;

public enum DataBaseCredentials {
    URL("jdbc:postgresql://localhost:5432/SimpleBankApp"),
    USER("postgres"),
    PASSWORD("sa");

    private final String value;

    DataBaseCredentials(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
