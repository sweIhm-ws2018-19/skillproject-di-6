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

import braingain.handlers.AnswerHandler;
import braingain.handlers.AskQuestionHandler;
import braingain.handlers.BackPackingHandler;
import braingain.handlers.CancelandStopIntentHandler;
import braingain.handlers.FallbackIntentHandler;
import braingain.handlers.HelpIntentHandler;
import braingain.handlers.LaunchRequestHandler;
import braingain.handlers.SaveUsernameHandler;
import braingain.handlers.SessionEndedRequestHandler;
import braingain.handlers.SetCategoryHandler;
import braingain.handlers.SetLevelHandler;
import braingain.handlers.SetNumberOfPlayersHandler;
import braingain.modell.Gameround;

public class BraingainStreamhandler extends SkillStreamHandler {
	
	private static Gameround round;
	
private static Skill getSkill() {
	round = new Gameround();
	return Skills.standard()
		.addRequestHandlers(
			new CancelandStopIntentHandler(),
			new FallbackIntentHandler(),
			new HelpIntentHandler(),
			new SessionEndedRequestHandler(),
			new AnswerHandler(round),
			new AskQuestionHandler(round),
			new BackPackingHandler(round),
			new LaunchRequestHandler(round),
			new SaveUsernameHandler(round),
			new SetCategoryHandler(round),
			new SetLevelHandler(round),
			new SetNumberOfPlayersHandler(round))
			.withTableName("HighScore")
			.withAutoCreateTable(true)
			.withSkillId("amzn1.ask.skill.9a1dd27b-4aa6-4e19-a454-5e4525eab49b")
			.build();
}

	public BraingainStreamhandler() {
		super(getSkill());
	}
}

