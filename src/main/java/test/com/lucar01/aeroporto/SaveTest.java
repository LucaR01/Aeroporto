package test.com.lucar01.aeroporto;

import com.lucar01.aeroporto.save.Data;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SaveTest {

    @Test
    public void saveAndLoadSettingsTest(){
        Data.resetSettingsFile();

        final String theme = "Dark";
        final String language = "Italiano";

        Data.saveSettings(theme, language);

        final HashMap<String, String> map = new HashMap<>();

        map.put("Theme", theme);
        map.put("Language", language);

        final HashMap<String, String> result = Data.loadSettings();

        assertNotNull(result);
        assertEquals(map, result);

        final String theme2 = "Light";
        final String language2 = "English";

        Data.saveSettings(theme2, language2);

        final HashMap<String, String> map2 = new HashMap<>();

        map2.put("Theme", theme2);
        map2.put("Language", language2);

        final HashMap<String, String> result2 = Data.loadSettings();

        assertNotNull(result2);
        assertEquals(map2, result2);
    }

    @Test
    public void resetSettings(){
        Data.resetSettingsFile();

        HashMap<String, String> map = Data.loadSettings();

        assertTrue(map.isEmpty());
    }
}
