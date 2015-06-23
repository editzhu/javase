package com.springinaction.chapter01.knight;

import junit.framework.TestCase;

public class KnightOfTheRoundTableTest extends TestCase {
    public void testEmbardOnQuest() throws QuestFailedException {
	KnightOfTheRoundTable knight = new KnightOfTheRoundTable("jim");
	Object grail = knight.embarkOnQuest();
	assertNotNull(grail);
	// assertTrue(grail.isHoly());
    }
}
