package com.lucar01.aeroporto.table;

import java.util.EnumMap;
import java.util.HashMap;

/*public enum Table { //TODO: al posto delle colonne che posso ottenere, passare il nome della tabella, ovvero PERSONA("persona"), ecc..;
    PERSONA(19),
    AEREO(0),
    VOLO(0);

    private int numberOfColumns;
    private HashMap<String, String> nameOfColumnAndDataType;



    Table(int numberOfColumns){
        this.numberOfColumns = numberOfColumns;
    }

    public static void x(){
        EnumMap<Table, String> tableMap = new EnumMap<Table, String>(Table.class);
        HashMap<String, String> y = new HashMap<>();
        tableMap.put(Table.PERSONA, y.put("CodiceFiscale", "VARCHAR"));
        System.out.println("tableMap: " + tableMap);
    }
}*/

public enum Table {
    PERSONA("persona"),
    AEREO("aereo"),
    ASSICURAZIONE("assicurazione"),
    BAGAGLIO("bagaglio"),
    CARGO("cargo"),
    CENTRO_CONTROLLO_AEREA("centro_controllo_aerea"),
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
