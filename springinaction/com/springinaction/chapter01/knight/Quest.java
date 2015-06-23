package com.springinaction.chapter01.knight;

public interface Quest {
    abstract Object embark() throws QuestFailedException;
}
