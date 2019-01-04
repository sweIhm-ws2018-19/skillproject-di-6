package main.java.braingain.Modell;

import main.java.braingain.handlers.AnzahlDerSpielerSetzenHandler;

public class Datenbank {

    private String player;
    private String highscore;



    public Datenbank() {



    }

    public void useDatenbank(boolean spielmodus) {
        if (AnzahlDerSpielerSetzenHandler.selectedPlayerSlot == 1) {
            spielmodus = true;
        } else {
            spielmodus = false;
        }

    }

    public Spieler searchPlayer(HanderInput input, String name) {
        Spieler player = null;
        if (spielmodus == true) {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            if (persitentAttrebutes.contains(name)){
            String oldH   = (String) persistentAttributes.get(name);
            int oldHighscore =Integer.parseInt(oldH);

                player = new Spieler(name, oldHighscore);
            }else{
                player = new Spieler(name);
                perstistentAttributes.put(name,"0");
                attributesManager.setPersitentAttributes(persitentAttributes);


        }

        return player;
    }

   // public void savePlayer(Spieler player){


        }



    }
}

