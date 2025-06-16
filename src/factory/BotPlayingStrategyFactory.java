package factory;

import models.BotDifficultyLevel;
import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.EasyBotPlayingStrategy;
import strategies.botplayingstrategy.HardBotPlayingStrategy;
import strategies.botplayingstrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyFactory(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }else{
            return new HardBotPlayingStrategy();
        }
    }
}
