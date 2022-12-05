package entity;

public enum AccountType {
    COR("Corporate"),
    COM("Commercial"),
    STU("Student"),
    IND("Individual"),
    SAV("Savings");

    private String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
