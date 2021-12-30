package tech.salvatore.livro_android_kotlin_paulo_salvatore.config

object Config {
    // Max Values
    const val maxHungry = 5
    const val maxStrength = 5
    const val maxHumor = 5

    // Wait for next action
    const val delayBeforeCanTrain = 2 * 1 // 2 minutes
//    const val delayBeforeCanTrain = 2 * 60 // 2 minutes
    const val delayBeforeCanPlay = 3 * 1 // 3 minutes
//    const val delayBeforeCanPlay = 3 * 60 // 3 minutes

    // Experience on action
    const val experienceOnFeed = 10L
    const val experienceOnTrain = 30L
    const val experienceOnPlay = 30L
}
