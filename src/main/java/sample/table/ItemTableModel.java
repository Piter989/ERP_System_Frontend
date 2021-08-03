package sample.table;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import sample.dto.ItemDto;

public class ItemTableModel {
    private final Long idItem;
    private final SimpleStringProperty name;
    private final SimpleStringProperty quantityType;
    private final SimpleDoubleProperty quantity;

    public ItemTableModel(Long idItem, String name, Double quantity, String quantityType){
        this.idItem = idItem;
        this.name = new SimpleStringProperty(name);
        this.quantityType = new SimpleStringProperty(quantityType);
        this.quantity = new SimpleDoubleProperty(quantity);

    }

    public static ItemTableModel of (ItemDto dto){
        return new ItemTableModel(dto.getIdItem(), dto.getName(), dto.getQuantity(), dto.getQuantityType());
    }

    public Long getIdItem() {
        return idItem;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getQuantityType() {
        return quantityType.get();
    }

    public SimpleStringProperty quantityTypeProperty() {
        return quantityType;
    }

    public double getQuantity() {
        return quantity.get();
    }

    public SimpleDoubleProperty quantityProperty() {
        return quantity;
    }
}
