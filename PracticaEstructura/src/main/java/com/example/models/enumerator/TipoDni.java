package com.example.models.enumerator;


public enum TipoDni {
    CEDULA("CEDULA"), RUC("RUC"), PASAPORTE("PASAPORTE");
    private String name;

    TipoDni(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static TipoDni fromString(String tipo) {
        for (TipoDni t : TipoDni.values()) {
            if (t.getName().equalsIgnoreCase(tipo)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tipo de identificación no válido: " + tipo);
    }


}
