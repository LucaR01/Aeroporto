package com.lucar01.aeroporto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Settings {

    SETTINGS;

    public enum Theme {
        LIGHT("Light"),
        DARK("Dark");

        String theme;

        Theme(String theme){
            this.theme = theme;
        }

        public String getTheme(){
            return this.theme;
        }

        public String getDefaultTheme(){ //TODO: forse è meglio usare un static final, perchè così anche la DARK avrebbe un suo defaultTheme che comunque è sempre il light.
            return LIGHT.getTheme();
        }

        public ObservableList<Theme> getThemes(){
            ObservableList<Theme> themes = FXCollections.observableArrayList();
            themes.addAll(Theme.values()); //TODO: in String
            return themes;
        }
    }

    public enum Languages {
        ENGLISH("English"),
        ITALIANO("Italiano");

        String language;

        Languages(String language){
            this.language = language;
        }

        public String getLanguage(){
            return this.language;
        }

        public String getDefaultLanguage(){
            return ENGLISH.getLanguage();
        }

        public ObservableList<Languages> getLanguages(){
            ObservableList<Languages> languages = FXCollections.observableArrayList();
            languages.addAll(Languages.values()); //TODO: in String
            return languages;
        }
    }
}
