package com.lucar01.aeroporto.table;

public enum Table {
    PERSONA("persona"),
    AEREO("aereo"),
    ASSICURAZIONE("assicurazione"),
    BAGAGLIO("bagaglio"),
    CARGO("cargo"),
    CENTRO_CONTROLLO_AREA("centro_controllo_area"),
    COMPAGNIA_AEREA("compagnia_aerea"),
    COMPONENTE_AEREO("componente_aereo"),
    GATE("gate"),
    GROUND_SUPPORT_EQUIPMENT("ground_support_equipment"),
    HANGAR("hangar"),
    LOGISTICA("logistica"),
    MANTENIMENTO("mantenimento"),
    NEGOZIO("negozio"),
    PISTA("pista"),
    RADAR("radar"),
    SERVIZIO_CLIENTI("servizio_clienti"),
    SOCCORSI("soccorsi"),
    TERMINAL("terminal"),
    TORRE_CONTROLLO("torre_di_controllo"),
    TRATTA("tratta"),
    VIA_RULLAGGIO("via_di_rullaggio"),
    VOLO("volo");

    private final String tableName;

    Table(final String tableName){
        this.tableName = tableName;
    }

    public final String getTableName() {
        return this.tableName;
    }
}
