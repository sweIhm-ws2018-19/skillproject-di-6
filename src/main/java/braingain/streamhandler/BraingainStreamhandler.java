/*
Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
except in compliance with the License. A copy of the License is located at

    http://aws.amazon.com/apache2.0/

or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
the specific language governing permissions and limitations under the License.
*/
package braingain.streamhandler;


import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import braingain.modell.Spielrunde;
import braingain.handlers.AnzahlDerSpielerSetzenHandler;
import braingain.handlers.CancelandStopIntentHandler;
import braingain.handlers.FallbackIntentHandler;
import braingain.handlers.HelpIntentHandler;
import braingain.handlers.KategorieEinstellenHandler;
import braingain.handlers.LaunchRequestHandler;
import braingain.handlers.LevelEinstellenHandler;
import braingain.handlers.SessionEndedRequestHandler;
import braingain.handlers.UsernamenSpeichernHandler;

public class BraingainStreamhandler extends SkillStreamHandler {
	
	public static Spielrunde sr;
	
private static Skill getSkill() {
	sr = new Spielrunde();
	return Skills.standard()
		.addRequestHandlers(
				new AnzahlDerSpielerSetzenHandler(sr),
				new CancelandStopIntentHandler(),
				new FallbackIntentHandler(),
				new HelpIntentHandler(),
				new KategorieEinstellenHandler(sr),
				new LaunchRequestHandler(sr),
				new LevelEinstellenHandler(sr),
				new SessionEndedRequestHandler(sr),
				new UsernamenSpeichernHandler(sr))
				.withSkillId("amzn1.ask.skill.9a1dd27b-4aa6-4e19-a454-5e4525eab49b")
				.build();
}

	public BraingainStreamhandler() {
		super(getSkill());
	}
}

