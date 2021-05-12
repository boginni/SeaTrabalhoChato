package body.mainScreenPack.Ults;

public class TableModelCell {
    String value;
    TableModelColumProp prop;

    public TableModelCell(String value, TableModelColumProp prop) {
        this.value = value;
        this.prop = prop;
    }

    @Override
    public String toString() {
        return prop.getValue(value);
    }
}
