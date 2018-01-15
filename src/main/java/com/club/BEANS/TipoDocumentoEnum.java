
package com.club.BEANS;


public enum TipoDocumentoEnum {

    CONTADO("A vista"),CREDITO("Conta");
    private String description;
    
    TipoDocumentoEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
    
    
}
