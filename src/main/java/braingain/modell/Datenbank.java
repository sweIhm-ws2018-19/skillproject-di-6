package braingain.modell;

import java.util.Map;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class Datenbank {

	private Gameround round;
	
    public Datenbank(Gameround round) {
    	this.round = round;
    }

    public void searchPlayer(HandlerInput input, String name) {
        Player player = null;
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            if (persistentAttributes.containsKey(name)){
            String oldH   = (String) persistentAttributes.get(name);
            int oldHighscore =Integer.parseInt(oldH);

                player = new Player(name, oldHighscore);
            }else{
                player = new Player(name);
                persistentAttributes.put(name,"0");
                attributesManager.setPersistentAttributes(persistentAttributes);


        }

            round.addPlayer(player);
    }
}
