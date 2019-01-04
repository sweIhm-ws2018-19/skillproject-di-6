package braingain.modell;

import java.util.Map;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class Datenbank {

	private boolean spielmodus;
	private int highscore;
	private Player player;
	private Gameround round;

	public Datenbank(Gameround round) {
		this.round = round;

	}

	public void useDatenbank(boolean spielmodus) {
		spielmodus = round.getNumberOfPlayers() == 1;

	}

	public Player searchPlayer(HandlerInput input, String name) {
		player = null;
		if (spielmodus) {
			AttributesManager attributesManager = input.getAttributesManager();
			Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
			if (persistentAttributes.containsKey(name)) {
				String highscoreString = (String) persistentAttributes.get(name);
				highscore = Integer.parseInt(highscoreString);
				player = new Player(name, highscore);
			} else {
				player = new Player(name);
				persistentAttributes.put(name, "0");
				attributesManager.setPersistentAttributes(persistentAttributes);
			}
		}
		return player;
	}
}
