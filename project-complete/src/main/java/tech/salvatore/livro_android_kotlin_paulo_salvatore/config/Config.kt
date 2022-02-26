package tech.salvatore.livro_android_kotlin_paulo_salvatore.config

object Config {
    // Max Values
    const val maxHungry = 5
    const val maxStrength = 5
    const val maxHumor = 5

    // Wait for next action
    const val delayBeforeCanTrain = 2 // 2 seconds
    // const val delayBeforeCanTrain = 2 * 60 // 2 minutes
    const val delayBeforeCanPlay = 3 // 3 seconds
    // const val delayBeforeCanPlay = 3 * 60 // 3 minutes

    // Experience on action
    const val experienceOnFeed = 10L
    const val experienceOnTrain = 30L
    const val experienceOnPlay = 30L
}
