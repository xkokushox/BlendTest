package com.appsorama.blendtest.model;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class StockModel {
    public String received;
    public int quantity;
    public String notes;

    public String getReceived() {
        return received == null ? "" : received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes == null ? "" : notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
