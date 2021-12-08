package test.com.lucar01.aeroporto;

import com.lucar01.aeroporto.table.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseController {

    @Test
    public void DatabaseControllerTest(){
        final int numberOfColumns = 3;

        assertEquals(numberOfColumns, com.lucar01.aeroporto.controllers.DatabaseController.getNumberOfColumns(Table.BAGAGLIO));

        final ObservableList<String> namesOfColumnsList = FXCollections.observableArrayList();
        namesOfColumnsList.addAll("CodBagaglio", "Peso", "CodiceFiscale");

        assertEquals(namesOfColumnsList, com.lucar01.aeroporto.controllers.DatabaseController.getNamesOfColumns(Table.BAGAGLIO));


    }
}
