package main.java.com.bancoeconomico.model.enums;

public enum TipoClienteEnum {

    PESSOA_JURIDICA(1),
    PESSOA_FISICA(2);

    private final int value;

    TipoClienteEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static TipoClienteEnum fromInt(int i) {
        for (TipoClienteEnum type : TipoClienteEnum.values()) {
            if (type.getValue() == i) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + i);
    }
}
