/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package main.java.braingain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class WhatsMyColorIntentHandler implements RequestHandler {
    public static final String COLOR_KEY = "COLOR";
    public static final String COLOR_SLOT = "Color";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("WhatsMyColorIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        String favoriteColor = (String) input.getAttributesManager().getSessionAttributes().get(COLOR_KEY);

        if (favoriteColor != null && !favoriteColor.isEmpty()) {
            speechText = String.format("Deine Lieblingsfarbe ist %s. Auf Wiedersehen.", favoriteColor);
        } else {
            // Since the user's favorite color is not set render an error message.
            speechText = "Ich weiss nicht welches Deine Lieblingsfarbe ist. Sag mir Deine Lieblingsfarbe. Sage zum Beispiel: ich mag rot.";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("ColorSession", speechText)
                .build();
    }
}
