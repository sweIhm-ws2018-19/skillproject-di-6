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
import braingain.handlers.AntwortHandler;
import braingain.handlers.AnzahlDerSpielerSetzenHandler;
import braingain.handlers.CancelandStopIntentHandler;
import braingain.handlers.FallbackIntentHandler;
import braingain.handlers.FrageStellenHandler;
import braingain.handlers.HelpIntentHandler;
import braingain.handlers.KategorieEinstellenHandler;
import braingain.handlers.LaunchRequestHandler;
import braingain.handlers.LevelEinstellenHandler;
import braingain.handlers.SessionEndedRequestHandler;
import braingain.handlers.UsernamenSpeichernHandler;

public class BraingainStreamhandler extends SkillStreamHandler {
	
	
private static Skill getSkill() {
	LaunchRequestHandler lrh = new LaunchRequestHandler();
	return Skills.standard()
		.addRequestHandlers(
				new AnzahlDerSpielerSetzenHandler(lrh),
				new AntwortHandler(lrh),
				new CancelandStopIntentHandler(),
				new FallbackIntentHandler(),
				new FrageStellenHandler(lrh),
				new HelpIntentHandler(),
				new KategorieEinstellenHandler(lrh),
				lrh,
				new LevelEinstellenHandler(lrh),
				new SessionEndedRequestHandler(),
				new UsernamenSpeichernHandler(lrh))
				.withSkillId("amzn1.ask.skill.9a1dd27b-4aa6-4e19-a454-5e4525eab49b")
				.build();
}

	public BraingainStreamhandler() {
		super(getSkill());
	}
}

