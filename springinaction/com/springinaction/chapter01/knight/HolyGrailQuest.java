package com.springinaction.chapter01.knight;


public class HolyGrailQuest implements Quest {
    // public HolyGrail embark() throws GrailNotFoundException {
    // HolyGrail grail = null;
    // return grail;
    public Object embark() throws QuestFailedException {
	return new HolyGrail();
    }
}
