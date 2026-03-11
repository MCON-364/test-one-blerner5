package edu.touro.las.mcon364.test;

import javax.xml.namespace.QName;
import java.util.*;

public class StudyTracker {

    private final Map<String, List<Integer>> scoresByLearner = new HashMap<>();
    private final Deque<UndoStep> undoStack = new ArrayDeque<>();
    // Helper methods already provided for tests and local inspection.
    public Optional<List<Integer>> scoresFor(String name) {
        return Optional.ofNullable(scoresByLearner.get(name));
    }

    public Set<String> learnerNames() {
        return scoresByLearner.keySet();
    }
    /**
     * Problem 11
     * Add a learner with an empty score list.
     *
     * Return:
     * - true if the learner was added
     * - false if the learner already exists
     *
     * Throw IllegalArgumentException if name is null or blank.
     */
    public boolean addLearner(String name) {
        learnerNames().add("Leah");
        if (scoresByLearner.containsKey(name)) {
            return true;
        }
        if(scoresByLearner.containsKey(null)) {
            throw new UnsupportedOperationException();
        }
        else
            return false;

    }

    /**
     * Problem 12
     * Add a score to an existing learner.
     *
     * Return:
     * - true if the score was added
     * - false if the learner does not exist
     *
     * Valid scores are 0 through 100 inclusive.
     * Throw IllegalArgumentException for invalid scores.
     *
     * This operation should be undoable.
     */
    public boolean addScore(String name, int score) {
        if (score > 100 || score < 0) {
            throw new IllegalArgumentException();
        }
        scoresByLearner.get(name).remove(score);
        scoresByLearner.get(name).add(score);
        if (scoresByLearner.get(name).contains(score)) {
            return true;
        }
        if (scoresByLearner.get(name).isEmpty()) {
            return false;
        }
        return false;
    }
    /**
     * Problem 13
     * Return the average score for one learner.
     *
     * Return Optional.empty() if:
     * - the learner does not exist, or
     * - the learner has no scores
     */
    public Optional<Double> averageFor(String name) {
        double average = 0;
        int ctr = 0;
        if(!scoresByLearner.containsKey(name) || scoresByLearner.get(name).isEmpty()) {
            return Optional.empty();
        }
        for (String learner : learnerNames()) {
            for(scoresByLearner : scoresFor(learner)) {
                average += scoresByLearner.get(learner).stream().mapToDouble(s -> s).sum();
                ctr++;
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Problem 14
     * Convert a learner average into a letter band.
     *
     * A: 90+
     * B: 80-89.999...
     * C: 70-79.999...
     * D: 60-69.999...
     * F: below 60
     *
     * Return Optional.empty() when no average exists.
     */
    public Optional<String> letterBandFor(String name) {
        Optional<Double> average = averageFor(name);

        String letterGrade = switch (average) {
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> "A";
            case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> 'B';
            case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> "C";
            case 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 -> "D";
            default -> "F"
        };
        throw new UnsupportedOperationException();
    }

    /**
     * Problem 15
     * Undo the most recent state-changing operation.
     *
     * Return true if something was undone.
     * Return false if there is nothing to undo.
     */
    public boolean undoLastChange() {
        if (!undoStack.isEmpty()) {
            return true;
        }
        else
            return false;

    }


}
