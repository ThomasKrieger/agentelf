package dev.agentelf.meta;

/**
 * transformModel consists of the following steps
 *      iterate over all Actions
 *      add standard text to prompt
 *      call createClass for each Action
 *
 * The transformation depends on the model type.
 * So transformModel calls transformActivity or transformClass and so on.
 */
public class TransformModelFactory {
}
