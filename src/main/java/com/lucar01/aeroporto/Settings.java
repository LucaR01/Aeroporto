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

        public String getDefaultTheme(){ // forse è meglio usare un static final, perchè così anche la DARK avrebbe un suo defaultTheme che comunque è sempre il light.
            return LIGHT.getTheme();
        }

        public ObservableList<String> getThemes(){
            ObservableList<String> themes = FXCollections.observableArrayList();
            for(Theme t : Theme.values()){
                themes.add(t.getTheme());
            }
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

        public ObservableList<String> getLanguages(){
            ObservableList<String> languages = FXCollections.observableArrayList();
            for(Languages l : Languages.values()){
                languages.add(l.getLanguage());
            }
            return languages;
        }
    }
}
