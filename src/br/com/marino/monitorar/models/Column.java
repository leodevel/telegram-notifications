package br.com.marino.monitorar.models;

public class Column {

    private String column;
    private String field;
    private Class type;

    public Column(String column, String field, Class type) {
        this.column = column;
        this.field = field;
        this.type = type;
    }

    public Column() {
    }
    
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

}
