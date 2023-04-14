package com.morris.stunes.model;

import java.math.BigDecimal;

public class InvoiceItem {
    private int invoiceLineId;
    private Invoice invoice;
    private Track track;
    private BigDecimal unitPrice;
    private int quantity;

    public InvoiceItem() {}

    public InvoiceItem(int invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public int getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(int invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
