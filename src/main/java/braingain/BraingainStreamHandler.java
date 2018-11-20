/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package main.java.braingain;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import main.java.braingain.handlers.CancelandStopIntentHandler;
import main.java.braingain.handlers.FallbackIntentHandler;
import main.java.braingain.handlers.HelpIntentHandler;
import main.java.braingain.handlers.LaunchRequestHandler;
import main.java.braingain.handlers.MyColorIsIntentHandler;
import main.java.braingain.handlers.SessionEndedRequestHandler;
import main.java.braingain.handlers.WhatsMyColorIntentHandler;

public class BraingainStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler(),
                		new SaveUsername())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public BraingainStreamHandler() {
        super(getSkill());
    }

}
